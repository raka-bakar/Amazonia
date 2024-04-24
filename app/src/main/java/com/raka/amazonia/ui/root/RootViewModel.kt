package com.raka.amazonia.ui.root

import androidx.lifecycle.ViewModel
import com.raka.amazonia.domain.usecase.GetInitialDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
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