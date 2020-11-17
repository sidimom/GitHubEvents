package com.example.events.api

import com.example.events.api.model.EventItem
import com.example.events.api.model.RepoUrl
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface Api {
    @GET("/events")
    fun getEvents(): Single<List<EventItem>>

    @GET
    fun getRepo(@Url url: String): Single<RepoUrl>
}