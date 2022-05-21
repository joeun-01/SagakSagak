package com.songilcraft.sagak_sagak.screen.signup.models

import com.songilcraft.sagak_sagak.screen.signup.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterface {
    @POST("/app/users")
    fun signUp(@Body user : User) : Call<SignUpResponse>
}