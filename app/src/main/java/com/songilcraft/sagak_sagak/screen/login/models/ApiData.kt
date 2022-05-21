package com.songilcraft.sagak_sagak.screen.login.models

import com.songilcraft.sagak_sagak.config.BaseResponse

data class LoginRequestBody(val id : String, val password: String)


data class LoginResponseResultBody(val userId : String, val jwt : String, val userIdx : Int)

data class LoginResponse(val result : LoginResponseResultBody) : BaseResponse()