package com.songilcraft.sagak_sagak.screen.home

import com.songilcraft.sagak_sagak.screen.home.models.ResponseGetAllPost
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("/app/posts/{userIdx}")
    suspend fun getPost(@Path("userIdx") userIdx : Int) : Response<ResponseGetAllPost>
}