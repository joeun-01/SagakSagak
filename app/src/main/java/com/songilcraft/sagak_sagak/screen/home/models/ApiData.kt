package com.songilcraft.sagak_sagak.screen.home.models

import com.songilcraft.sagak_sagak.config.BaseResponse
import com.songilcraft.sagak_sagak.data.Ticket

data class ResponseGetAllPost(val result : ArrayList<ArrayList<Ticket>>) : BaseResponse()