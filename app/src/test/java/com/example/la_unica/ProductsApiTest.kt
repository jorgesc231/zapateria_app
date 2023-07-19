package com.example.la_unica

import com.example.la_unica.data.network.ProductApiClient
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductsApiTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var productsAPI : ProductApiClient

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        productsAPI = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ProductApiClient::class.java)
    }

    @Test
    fun theDetailEnpointReturnProductDetail() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/detail_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = productsAPI.getProductDetail(1)
        mockWebServer.takeRequest()

        Assert.assertEquals(5, response.body()!!.id)
    }

    @Test
    fun theListEnpointReturnProductList() = runTest {
        val mockResponse = MockResponse()
        val content = Helper.readFileResource("/list_response.json")
        mockResponse.setResponseCode(200)
        mockResponse.setBody(content)
        mockWebServer.enqueue(mockResponse)

        val response = productsAPI.getAllProducts()
        mockWebServer.takeRequest()

        Assert.assertEquals(false, response.body()!!.isEmpty())
    }

    @Test
    fun theListEnpointReturnEmptyProductList() = runTest {
        val mockResponse = MockResponse()
        mockResponse.setBody("[]")
        mockWebServer.enqueue(mockResponse)

        val response = productsAPI.getAllProducts()
        mockWebServer.takeRequest()

        Assert.assertEquals(true, response.body()!!.isEmpty())
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}