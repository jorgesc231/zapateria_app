package com.example.la_unica

import com.example.la_unica.data.ProductRepository
import com.example.la_unica.domain.GetProductsUseCase
import com.example.la_unica.domain.model.Product
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetProductsUseCaseTest {

    @RelaxedMockK
    private lateinit var productRepository: ProductRepository

    lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)

        getProductsUseCase = GetProductsUseCase(productRepository)
    }

    @Test
    fun whenTheDatabaseDoesntReturnAnythingThenGetProductsFromApi() = runBlocking {
        // Given
        coEvery { productRepository.getAllProductsFromDatabase() } returns emptyList()

        // When
        getProductsUseCase()

        // Then
        // Verifica que se haya llamado 1 vez
        coVerify(exactly = 1) { productRepository.getAllProductsFromApi() }
    }


    @Test
    fun whenTheDatabaseReturnSomethingThenGetProductsFromDatabase() = runBlocking {
        // Given
        val products = listOf(Product(1, "Prueba", "", "https:", "", 0, null, null))
        coEvery { productRepository.getAllProductsFromDatabase() } returns products

        // When
        val response = getProductsUseCase()

        // Then
        // Nos aseguramos que no ha sido llamada la api
        coVerify(exactly = 0) { productRepository.getAllProductsFromApi() }

        assert(products == response)
    }
}