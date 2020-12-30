package me.codeenzyme.covid.data.remote

import me.codeenzyme.covid.models.GlobalStatResponse
import retrofit2.http.GET

interface CoronaService {

    @GET("all")
    suspend fun getGlobalStat(): GlobalStatResponse

}