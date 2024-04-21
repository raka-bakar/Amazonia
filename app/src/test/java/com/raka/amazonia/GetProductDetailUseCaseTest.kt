package com.raka.amazonia

import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.repository.ProductRepository
import com.raka.amazonia.usecase.GetProductDetailUseCase
import com.raka.amazonia.usecase.GetProductDetailUseCaseImpl
import com.raka.amazonia.utils.RatingManager
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.verify

class GetProductDetailUseCaseTest {

    private val repository = Mockito.mock(ProductRepository::class.java)
    private val ratingManager = Mockito.mock(RatingManager::class.java)

    private lateinit var sut: GetProductDetailUseCase

    @Before
    fun setup() {
        sut = GetProductDetailUseCaseImpl(repository = repository, ratingManager = ratingManager)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitIoSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `verify using the correct parameters when getting products by category`() {
        Mockito.`when`(repository.getProductsByCategory(2)).thenReturn(
            Single.just(DataTest.listProductCompact)
        )
        Mockito.`when`(ratingManager.getProductRank(2, DataTest.listProductCompact)).thenReturn(
            DataTest.expectedProduct
        )
        val observer = sut.getProductDetail(2)
        val testObserver = TestObserver.create<ProductCompact>()

        observer.subscribe(testObserver)

        verify(repository).getProductsByCategory(2)
        verify(ratingManager).getProductRank(2, DataTest.listProductCompact)
    }

    @Test
    fun `verify using the correct parameters when getting a product's rank`() {
        Mockito.`when`(repository.getProductsByCategory(2)).thenReturn(
            Single.just(DataTest.listProductCompact)
        )
        Mockito.`when`(ratingManager.getProductRank(2, DataTest.listProductCompact)).thenReturn(
            DataTest.expectedProduct
        )
        val observer = sut.getProductDetail(2)
        val testObserver = TestObserver.create<ProductCompact>()

        observer.subscribe(testObserver)

        verify(ratingManager).getProductRank(2, DataTest.listProductCompact)
    }

    @Test
    fun `return the correct response when getting a product's detail`() {
        Mockito.`when`(repository.getProductsByCategory(2)).thenReturn(
            Single.just(DataTest.listProductCompact)
        )
        Mockito.`when`(ratingManager.getProductRank(2, DataTest.listProductCompact)).thenReturn(
            DataTest.expectedProduct
        )

        val observer = sut.getProductDetail(2)
        val testObserver = TestObserver.create<ProductCompact>()

        observer.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertResult(DataTest.expectedProduct)
    }
}