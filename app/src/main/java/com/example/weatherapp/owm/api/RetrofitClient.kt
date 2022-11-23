package com.example.weatherapp.owm.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClient {
    companion object {
        private const val REQUEST_BASE: String = "https://api.openweathermap.org/data/2.5/"
        // TODO: Load key from env
        const val KEY: String = "46ab78f050d1e914c01347ef3c8fe225"

        private val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

//        @OptIn(ExperimentalStdlibApi::class)
        fun getClient(): Retrofit =
            Retrofit.Builder()
                .baseUrl(REQUEST_BASE)
                .addConverterFactory(
                    MoshiConverterFactory.create(moshi)
                )
                .build()
    }


}