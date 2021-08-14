package com.task.populararticles.utile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View


inline fun <reified T : Activity> Context.openActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}

// to handle click Listener for Views
fun View.click(block: () -> Unit) {
    setOnClickListener {
        block()
    }
}