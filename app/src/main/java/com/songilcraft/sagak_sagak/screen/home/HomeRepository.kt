package com.songilcraft.sagak_sagak.screen.home

import com.songilcraft.sagak_sagak.config.GlobalApplication
import com.songilcraft.sagak_sagak.screen.home.models.ResponseGetAllPost

class HomeRepository {
    private val retrofit = GlobalApplication.sRetrofit.create(HomeApi::class.java)

    suspend fun getPost(userIdx : Int) : ResponseGetAllPost {
        val result = retrofit.getPost(userIdx)
        if (result.isSuccessful) return result.body()!!
        else throw UnknownError()
    }
}