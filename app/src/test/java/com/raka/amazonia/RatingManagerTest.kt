package com.raka.amazonia

import com.raka.amazonia.data.model.ProductCompact
import com.raka.amazonia.utils.Constants
import com.raka.amazonia.utils.RatingManager
import com.raka.amazonia.utils.RatingManagerImpl
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class RatingManagerTest {
    private lateinit var sut: RatingManager

    @Before
    fun setup() {
        sut = RatingManagerImpl()
    }

    @Test
    fun `return the correct rank of the product`() {
        val result = sut.getProductRank(1, DataTest.listProductCompact)

        Assert.assertEquals(DataTest.expectedProduct.rank, result.rank)
    }

    @Test
    fun `return the correct total products in the inputted list`() {
        val result = sut.getProductRank(1, DataTest.listProductCompact)

        Assert.assertEquals(DataTest.expectedProduct.totalProduct, result.totalProduct)
    }

    @Test
    fun `return the not found id when the product is not found in the list`() {
        val expected = ProductCompact(
            id = Constants.PRODUCT_NOT_FOUND_ID
        )
        val result = sut.getProductRank(5, DataTest.listProductCompact)

        Assert.assertEquals(expected.id, result.id)
    }

    @Test
    fun `return the not found id when the inputted list is empty`() {
        val expected = ProductCompact(
            id = Constants.PRODUCT_NOT_FOUND_ID
        )
        val result = sut.getProductRank(1, emptyList())

        Assert.assertEquals(expected.id, result.id)
    }
}