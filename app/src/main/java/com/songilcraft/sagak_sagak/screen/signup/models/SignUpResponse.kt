package com.songilcraft.sagak_sagak.screen.signup.models

import com.google.gson.annotations.SerializedName

data class SignUpResponse(
    @SerializedName(value= "isSuccess") var isSuccess : Boolean,
    @SerializedName(value= "code") var code : Int,
    @SerializedName(value= "message") var message : String
)