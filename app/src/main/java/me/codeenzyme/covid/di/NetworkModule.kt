package me.codeenzyme.covid.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import me.codeenzyme.covid.data.remote.CoronaService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            client(
                OkHttpClient.Builder().apply {
                    connectTimeout(10_000L, TimeUnit.MILLISECONDS)
                    readTimeout(10_000, TimeUnit.MILLISECONDS)
                    writeTimeout(10_000, TimeUnit.MILLISECONDS)
                }.build()
            )
            addConverterFactory(GsonConverterFactory.create())
            baseUrl("https://corona.lmao.ninja/v2/")
        }.build()
    }

    @Singleton
    @Provides
    @Inject
    fun providesCoronaService(retrofit: Retrofit): CoronaService {
        return retrofit.create(CoronaService::class.java)
    }

}