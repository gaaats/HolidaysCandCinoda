package com.holpackapooolka.holidayartponddos.nevs


import com.google.gson.annotations.SerializedName

data class NevsApiResponse(
    @SerializedName("articles")
    val articles: List<ArticleApiModel?>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)