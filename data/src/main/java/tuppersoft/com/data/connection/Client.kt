package tuppersoft.com.data.connection

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tuppersoft.com.data.BuildConfig

class Client {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    fun getRetrofitCLient(): Retrofit {

        val okHttpClient = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            okHttpClient.addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    getLogLevel()
                )
            )
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getLogLevel(): HttpLoggingInterceptor.Level {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}