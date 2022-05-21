package com.songilcraft.sagak_sagak.screen.login

import com.songilcraft.sagak_sagak.config.GlobalApplication
import com.songilcraft.sagak_sagak.screen.login.models.LoginRequestBody
import com.songilcraft.sagak_sagak.screen.login.models.LoginResponse

class LoginRepository {
    private val retrofit = GlobalApplication.sRetrofit.create(LoginApi::class.java)

    suspend fun doLogin(id : String, password : String) : LoginResponse {
        val result = retrofit.postLogin(LoginRequestBody(id, password))
        if (result.isSuccessful) return result.body()!!
        else throw UnknownError()
    }

}