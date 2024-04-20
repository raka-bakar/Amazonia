package com.raka.amazonia.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.usecase.GetProductDetailUseCase
import com.raka.amazonia.usecase.UpdateFavoriteStatusUseCase
import com.raka.amazonia.utils.CallResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailProductViewModel @Inject constructor(
    private val getProductDetail: GetProductDetailUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _productLiveData: MutableLiveData<CallResult<ProductCompact>> = MutableLiveData()
    val productLiveData: LiveData<CallResult<ProductCompact>> = _productLiveData

    fun getRatingProduct(id: Int) {
        val disposable = getProductDetail.loadProductDetail(id)
            .doOnSubscribe { _productLiveData.postValue(CallResult.loading()) }
            .subscribe({ productCompact ->
                _productLiveData.postValue(CallResult.success(productCompact))
            }, { throwable ->
                _productLiveData.postValue(CallResult.error(throwable.message))
                Timber.e(throwable)
            })
        compositeDisposable.add(disposable)
    }

    fun onFavoriteClicked(id: Int, isFavorite: Boolean) {
        val disposable = updateFavoriteStatusUseCase
            .updateFavoriteStatus(id = id, status = isFavorite)
            .subscribe {
                getRatingProduct(id)
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}