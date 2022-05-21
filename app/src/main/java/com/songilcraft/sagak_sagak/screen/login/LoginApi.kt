package com.songilcraft.sagak_sagak.screen.login

import com.songilcraft.sagak_sagak.screen.login.models.LoginRequestBody
import com.songilcraft.sagak_sagak.screen.login.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {

    @POST("app/login")
    suspend fun postLogin(@Body params : LoginRequestBody) : Response<LoginResponse>
}