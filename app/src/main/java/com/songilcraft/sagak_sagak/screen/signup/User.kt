package com.songilcraft.sagak_sagak.screen.signup

import com.google.gson.annotations.SerializedName

data class User (
    @SerializedName(value= "name") var name : String,
    @SerializedName(value= "id") var id : String,
    @SerializedName(value= "password") var password: String
)