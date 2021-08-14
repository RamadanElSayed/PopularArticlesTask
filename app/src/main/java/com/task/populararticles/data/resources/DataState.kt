package com.task.populararticles.data.resources

import java.lang.Exception

sealed class DataState<out R>{
    data class Success<out T>(val data : T) : DataState<T>()
    data class Error(val error: Exception?) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}
