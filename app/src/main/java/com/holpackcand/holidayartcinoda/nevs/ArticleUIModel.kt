package com.holpackcand.holidayartcinoda.nevs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

@Parcelize
data class ArticleUIModel(
    val id: Int = Random.nextInt(from = 10, until = 50000),
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String,
):Parcelable
