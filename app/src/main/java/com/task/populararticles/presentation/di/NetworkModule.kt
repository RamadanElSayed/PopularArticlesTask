package com.task.populararticles.presentation.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.task.populararticles.BuildConfig
import com.task.populararticles.data.remote.ApiClient
import com.task.populararticles.data.remote.EndPoints.APIKEY
import com.task.populararticles.data.repository.PopularRemoteRepositoryImpl
import com.task.populararticles.domain.repository.PopularRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.*


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesJson(): Gson {
        val builder = GsonBuilder()
        val booleanDeserializer = BooleanDeserializer()
        builder.registerTypeAdapter(Boolean::class.javaPrimitiveType, booleanDeserializer)
        builder.registerTypeAdapter(Boolean::class.java, booleanDeserializer)
        return builder.setLenient().create()
    }

    private fun provideOkHttpClientBuilder(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.sslSocketFactory(
            provideSSLSocketFactory()!!,
            (provideTrustManager()[0] as X509TrustManager?)!!
        )
        builder.hostnameVerifier(HostnameVerifier { _: String?, _: SSLSession? -> true })
        builder.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
        builder.addInterceptor { chain ->
            val originalHttpUrl: HttpUrl = chain.request().url
            val url: HttpUrl = originalHttpUrl.newBuilder()
                .addQueryParameter("api-key", APIKEY).build();
            val requestBuilder: Request.Builder = chain.request().newBuilder()
                .url(url)
            val newRequest = requestBuilder
                .addHeader("Accept", "application/json")
                .addHeader("Content_Type", "multipart/form-data")
                .build()
            chain.proceed(newRequest)
        }
        builder.addInterceptor(provideHttpLoggingInterceptor())
        return builder.build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(provideOkHttpClientBuilder())
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Provides
    @Singleton
    fun providesApiClient(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }


    @Singleton
    @Provides
    fun provideSSLSocketFactory(): SSLSocketFactory? {
        var sslContext: SSLContext? = null
        try {
            sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, provideTrustManager(), SecureRandom())
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        return sslContext!!.socketFactory
    }

    @Provides
    fun provideTrustManager(): Array<TrustManager?> {
        return arrayOf(
            object : X509TrustManager {
                @Suppress("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                @Suppress("TrustAllX509TrustManager")
                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            }
        )
    }

    @Provides
    @Singleton
    fun providePopularRemoteRepository(apiClient: ApiClient) =
        PopularRemoteRepositoryImpl(apiClient) as PopularRemoteRepository


}