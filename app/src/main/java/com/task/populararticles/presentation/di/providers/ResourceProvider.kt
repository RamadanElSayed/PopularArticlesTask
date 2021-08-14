package com.task.populararticles.presentation.di.providers

import android.content.Context
import androidx.annotation.StringRes
import dagger.internal.Preconditions
import javax.inject.Inject


class ResourceProvider @Inject constructor(context: Context) : BaseResourceProvider {


    private val mContext: Context = Preconditions.checkNotNull(
        context,
        "context cannot be null"
    )

    override fun getString(@StringRes id: Int): String {
        return mContext.resources.getString(id)
    }


    override fun getString(@StringRes resId: Int, vararg formatArgs: String?): String {
        return mContext.resources.getString(resId, *formatArgs)
    }

}