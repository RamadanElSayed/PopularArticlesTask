package com.task.populararticles.data.error
import android.text.TextUtils
import com.task.populararticles.R
import com.task.populararticles.presentation.di.providers.ResourceProvider
import com.task.populararticles.utile.STATUS_CODE_NETWORK_ERROR
import com.task.populararticles.utile.STATUS_CODE_SERVER_ERROR
import com.task.populararticles.utile.STATUS_CODE_TIMEOUT_ERROR
import com.task.populararticles.utile.Utilities
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandling @Inject constructor(
    throwable: Throwable,
    val resourceProvider: ResourceProvider
) {
    var errorMsg: String? = null
        private set
    var statusCode = 0
        private set


    private fun formatThrowable(throwable: Throwable) {
        if (throwable is HttpException) {
            errorMsg = Utilities.getErrorMessage(throwable)
            statusCode = throwable.code()

        } else if (throwable is ConnectException || throwable is UnknownHostException) {
            errorMsg = resourceProvider.getString(R.string.check_connection)
            statusCode = STATUS_CODE_NETWORK_ERROR
        } else if (throwable is SocketTimeoutException || throwable is SocketException) {
            errorMsg = resourceProvider.getString(R.string.something_went_wrong)
            statusCode = STATUS_CODE_TIMEOUT_ERROR
        } else {
            errorMsg = resourceProvider.getString(R.string.something_went_wrong)
            statusCode = STATUS_CODE_SERVER_ERROR
        }
        if (TextUtils.isEmpty(errorMsg) || errorMsg!!.length > 300) errorMsg =
            resourceProvider.getString(R.string.something_went_wrong)
    }

    init {
        formatThrowable(throwable)
    }
}