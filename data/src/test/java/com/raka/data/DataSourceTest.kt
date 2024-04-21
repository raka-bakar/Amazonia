package com.raka.data

import com.raka.data.api.ApiService
import com.raka.data.database.DBProduct
import com.raka.data.database.ProductDao
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

class DataSourceTest {
    private val apiService = Mockito.mock(ApiService::class.java)
    private val productDao = Mockito.mock(ProductDao::class.java)

    private lateinit var sut: DataSource

    @Before
    fun setup() {
        sut = DataSourceImpl(apiService = apiService, productDao = productDao)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `verify using the correct parameters when loading products by category`() {
        Mockito.`when`(productDao.loadProduct(1)).thenReturn(
            Single.just(
                DataTest.dbProduct
            )
        )
        Mockito.`when`(productDao.loadProductsByCategory(DataTest.dbProduct.category)).thenReturn(
            Single.just(
                DataTest.listDBProduct
            )
        )
        val observer = sut.loadProductsByCategory(1)
        val testObserver = TestObserver.create<List<DBProduct>>()
        observer.subscribe(testObserver)

        verify(productDao).loadProduct(1)
        verify(productDao).loadProductsByCategory(DataTest.dbProduct.category)
    }

    @Test
    fun `return the correct data when loading products by category is succeed`() {
        Mockito.`when`(productDao.loadProduct(1)).thenReturn(
            Single.just(
                DataTest.dbProduct
            )
        )
        Mockito.`when`(productDao.loadProductsByCategory(DataTest.dbProduct.category)).thenReturn(
            Single.just(
                DataTest.listDBProduct
            )
        )
        val observer = sut.loadProductsByCategory(1)
        val testObserver = TestObserver.create<List<DBProduct>>()
        observer.subscribe(testObserver)

        testObserver.assertComplete()
        testObserver.assertResult(DataTest.listDBProduct)
    }

    @Test
    fun `return not complete type when loading products by category is failed`() {
        Mockito.`when`(productDao.loadProduct(1)).thenReturn(
            Single.just(
                DataTest.dbProduct
            )
        )
        Mockito.`when`(productDao.loadProductsByCategory(DataTest.dbProduct.category)).thenReturn(
            Single.error(Throwable())
        )
        val observer = sut.loadProductsByCategory(1)
        val testObserver = TestObserver.create<List<DBProduct>>()
        observer.subscribe(testObserver)

        testObserver.assertNotComplete()
    }

    @Test
    fun `verify the correct data from remote server is inserted into the database`() {
        Mockito.`when`(apiService.loadProducts()).thenReturn(
            Single.just(
                DataTest.apiResponse
            )
        )
        Mockito.`when`(productDao.insertProducts(DataTest.listDBProduct)).thenReturn(
            Completable.complete()
        )
        val observer = sut.loadInitialData()
        val testObserver = TestObserver.create<Completable>()
        observer.subscribe(testObserver)

        verify(productDao).insertProducts(DataTest.listDBProduct)
    }
}