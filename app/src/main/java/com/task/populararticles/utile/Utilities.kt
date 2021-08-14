package com.task.populararticles.utile


import android.content.Context
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.task.populararticles.R
import com.task.populararticles.data.error.ErrorHandling
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.io.Reader
import java.util.*
import javax.inject.Singleton

@Singleton
object Utilities {

    fun getErrorMessage(throwable: Throwable): String? {
        val responseBody: ResponseBody? =
            Objects.requireNonNull((throwable as HttpException).response())?.errorBody()
        val errorBody: Reader? = Objects.requireNonNull(responseBody)?.charStream()
        try {
            val errorStr = responseBody?.string()
            try {
                val jsonObject = JSONObject(errorStr)
                return jsonObject.optString("message")
            } catch (e: JSONException) {
                e.printStackTrace()
                try {
                    val gson = Gson()
                    val errorMessage: ErrorHandling =
                        gson.fromJson(errorBody, ErrorHandling::class.java)
                    return errorMessage.errorMsg
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun isNullString(str: String?): Boolean {
        return TextUtils.isEmpty(str) || str?.compareTo("null", ignoreCase = true) == 0
    }
    fun isNullList(list: List<*>?): Boolean {
        return !(list != null && list.isNotEmpty())
    }
    fun setSwipeRefreshLayoutColor(
        context: Context,
        swipeRefreshLayout: androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    ) {
        val colorPrimaryDark: Int = ContextCompat.getColor(context, R.color.purple_500)
        swipeRefreshLayout.setColorSchemeColors(
            colorPrimaryDark,
            colorPrimaryDark,
            colorPrimaryDark
        )
    }
}