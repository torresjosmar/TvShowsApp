package com.example.tvshowsapp.core.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import com.example.tvshowsapp.BuildConfig


//---- AMBIENT
const val DEBUG = "DEBUG"

val networkModule: Module = module {

    single { providerHttpLoggingInterceptor() }
    single { getRetrofitBase() }

    single (named("public")) {
        providerRetrofit(
            retrofitBase = get(),
            context = get(),
            httpLoggingInterceptor = get()
        )
    }

}

private fun getRetrofitBase(): Retrofit.Builder {
    return Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BuildConfig.BASE_URL)
}

private fun getBasicHeaders(context: Context, chain: Interceptor.Chain): Request.Builder {
    val builder = chain.request().newBuilder()
    return builder
}

fun providerRetrofit(retrofitBase: Retrofit.Builder, context: Context, httpLoggingInterceptor:HttpLoggingInterceptor): Retrofit {
    return retrofitBase
        .client(providerHttpClient(httpLoggingInterceptor, context))
        .build()
}


private fun providerHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor, context: Context): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.addInterceptor { chain ->
        val builder = getBasicHeaders(context, chain)
        chain.proceed(builder.build())
    }
    setTimeOuts(httpClient)
    httpClient.addInterceptor(httpLoggingInterceptor)
    return httpClient.build()
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.AMBIENT == DEBUG) {
        HttpLoggingInterceptor.Level.BODY
    } else {
        HttpLoggingInterceptor.Level.NONE
    }
    return logging
}

private fun setTimeOuts(httpClient: OkHttpClient.Builder) {
    httpClient.connectTimeout(30, TimeUnit.SECONDS)
    httpClient.readTimeout(30, TimeUnit.SECONDS)
    httpClient.writeTimeout(30, TimeUnit.SECONDS)
}



