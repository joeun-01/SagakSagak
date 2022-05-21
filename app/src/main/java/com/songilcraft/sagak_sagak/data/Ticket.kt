package com.songilcraft.sagak_sagak.data

data class Ticket(val imgUrl: String = "https://songil-prod.s3.ap-northeast-2.amazonaws.com/craft/%EC%9E%91%EC%95%BD%ED%94%8C%EB%A0%88%EC%9D%B4%ED%8A%B8+%EC%86%8C+%EB%A9%94%EC%9D%B8%EC%82%AC%EC%A7%84.jpg",
                  val title : String = "타이틀", val imgUrlIdx : Int = 0,
                  val date : String = "2022.05.21",
                  val categoryName : String = "뮤지컬", val categoryIdx : Int = 0)