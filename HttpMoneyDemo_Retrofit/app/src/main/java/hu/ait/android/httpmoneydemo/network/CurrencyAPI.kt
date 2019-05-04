package hu.ait.android.httpmoneydemo.network

import hu.ait.android.httpmoneydemo.data.MoneyResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


// URL: https://api.exchangeratesapi.io/latest?base=USD
// HOST: https://api.exchangeratesapi.io
//
// PATH: /latest
//
// QUERY param separator: ?
// QUERY params: base

interface CurrencyAPI {
    @GET("/latest")
    fun getRates(@Query("base") base: String) : Call<MoneyResult>
}