package com.example.template.ui.common.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val job = Job()
    // Specifying all view models to run on main thread
    // forcing view models to be explicit about going off the main thread
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private fun clean() {
        job.cancel()
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clean()
        super.onCleared()
    }
}