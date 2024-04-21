package com.raka.amazonia

import com.raka.amazonia.model.ProductCompact
import com.raka.amazonia.repository.ProductRepository
import com.raka.amazonia.repository.ProductRepositoryImpl
import com.raka.data.DataProvider
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.verify

class ProductRepositoryTest {

    private val dataProvider = Mockito.mock(DataProvider::class.java)

    private lateinit var sut: ProductRepository

    @Before
    fun setup() {
        sut = ProductRepositoryImpl(dataProvider = dataProvider)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `verify using the correct parameters when getting a product`() {
        Mockito.`when`(dataProvider.loadProduct(1)).thenReturn(
            Single.just(
                DataTest.dbProduct
            )
        )
        val observer = sut.getProduct(1)
        val testObserver = TestObserver.create<ProductCompact>()
        observer.subscribe(testObserver)

        verify(dataProvider).loadProduct(1)
    }

    @Test
    fun `return the correct response when getting a product`() {
        val expectedProduct = ProductCompact(
            id = 1,
            thumbnail = "https:img.url",
            price = 299,
            rating = 2.33,
            description = "description of product",
            title = "iphone 9",
            category = "smartphone",
            isFavorite = false
        )
        Mockito.`when`(dataProvider.loadProduct(1)).thenReturn(
            Single.just(
                DataTest.dbProduct
            )
        )
        val observer = sut.getProduct(1)
        val testObserver = TestObserver.create<ProductCompact>()
        observer.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertResult(expectedProduct)
    }

    @Test
    fun `verify using the correct parameters when updating favorite status`() {
        Mockito.`when`(dataProvider.updateFavoriteStatus(1, true)).thenReturn(
            Completable.complete()
        )
        val observer = sut.updateFavoriteStatus(1, true)
        val testObserver = TestObserver.create<ProductCompact>()
        observer.subscribe(testObserver)

        verify(dataProvider).updateFavoriteStatus(1, true)
    }

    @Test
    fun `return the completable response when updating favorite status is succeed`() {
        Mockito.`when`(dataProvider.updateFavoriteStatus(1, true)).thenReturn(
            Completable.complete()
        )
        val observer = sut.updateFavoriteStatus(1, true)
        val testObserver = TestObserver.create<Completable>()
        observer.subscribe(testObserver)

        testObserver.assertComplete()
    }

    @Test
    fun `return not complete type response when  updating favorite status is failed`() {
        Mockito.`when`(dataProvider.updateFavoriteStatus(1, true)).thenReturn(
            Completable.error(Throwable())
        )
        val observer = sut.updateFavoriteStatus(1, true)
        val testObserver = TestObserver.create<Completable>()
        observer.subscribe(testObserver)

        testObserver.assertNotComplete()
    }

    @Test
    fun `return the correct list of products`() {
        Mockito.`when`(dataProvider.loadProducts()).thenReturn(
            Single.just(DataTest.listDBProduct)
        )
        val observer = sut.getProducts()
        val testObserver = TestObserver.create<List<ProductCompact>>()
        observer.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertResult(DataTest.listProductCompact)
    }

    @Test
    fun `return the completable response when get initial data is succeed`() {
        Mockito.`when`(dataProvider.loadInitialData()).thenReturn(
            Completable.complete()
        )
        val observer = sut.getInitialData()
        val testObserver = TestObserver.create<Completable>()
        observer.subscribe(testObserver)

        testObserver.assertComplete()
    }

    @Test
    fun `return not complete type response when get initial data is failed`() {
        Mockito.`when`(dataProvider.loadInitialData()).thenReturn(
            Completable.error(Throwable())
        )
        val observer = sut.getInitialData()
        val testObserver = TestObserver.create<Completable>()
        observer.subscribe(testObserver)

        testObserver.assertNotComplete()
    }

    @Test
    fun `verify using the correct parameters when loading products by category`() {
        Mockito.`when`(dataProvider.loadProductsByCategory(1)).thenReturn(
            Single.just(
                DataTest.listDBProduct
            )
        )
        val observer = sut.getProductsByCategory(1)
        val testObserver = TestObserver.create<List<ProductCompact>>()
        observer.subscribe(testObserver)

        verify(dataProvider).loadProductsByCategory(1)
    }

    @Test
    fun `return the correct response when loading products by category`() {
        Mockito.`when`(dataProvider.loadProductsByCategory(1)).thenReturn(
            Single.just(
                DataTest.listDBProduct
            )
        )
        val observer = sut.getProductsByCategory(1)
        val testObserver = TestObserver.create<List<ProductCompact>>()
        observer.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertResult(DataTest.listProductCompact)
    }
}