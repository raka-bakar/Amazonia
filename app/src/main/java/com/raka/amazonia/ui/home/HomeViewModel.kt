package com.raka.amazonia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.usecase.GetAllProductsUseCase
import com.raka.amazonia.usecase.UpdateFavoriteStatusUseCase
import com.raka.amazonia.utils.CallResult
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val updateFavoriteStatusUseCase: UpdateFavoriteStatusUseCase
) :
    ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _productsLiveData:
        MutableLiveData<CallResult<List<ProductCompact>>> = MutableLiveData()
    val productsLiveData: LiveData<CallResult<List<ProductCompact>>> = _productsLiveData

    fun getAllProducts() {
        _productsLiveData.value = CallResult.loading()
        val disposable = getAllProductsUseCase.getProducts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listProducts ->
                if (listProducts.isEmpty()) {
                    _productsLiveData.postValue(CallResult.error("Data is empty"))
                } else {
                    _productsLiveData.postValue(CallResult.success(listProducts))
                }
            }, { onError ->
                _productsLiveData.postValue(CallResult.error(onError.message))
                Timber.e(onError)
            })
        compositeDisposable.add(disposable)
    }

    fun onFavoriteClicked(product: ProductCompact) {
        val disposable = updateFavoriteStatusUseCase
            .updateFavoriteStatus(id = product.id, status = product.isFavorite)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                getAllProducts()
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}