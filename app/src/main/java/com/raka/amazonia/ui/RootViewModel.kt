package com.raka.amazonia.ui

import androidx.lifecycle.ViewModel
import com.raka.amazonia.usecase.GetInitialDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getInitialDataUseCase: GetInitialDataUseCase
) : ViewModel() {

    private val _isLoading:
        MutableStateFlow<Boolean> = MutableStateFlow(true)
    val isLoading: MutableStateFlow<Boolean> = _isLoading

    private val compositeDisposable = CompositeDisposable()

    init {
        val disposable = getInitialDataUseCase.getInitialData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { _isLoading.value = false }
            .subscribe({}, {
                Timber.e(it.message)
            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}