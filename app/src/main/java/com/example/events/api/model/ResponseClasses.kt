package com.example.events.api.model

import com.google.gson.annotations.SerializedName

data class EventItem(@SerializedName("id") val id: String,
                     @SerializedName("type") val type: String,
                     @SerializedName("actor") val actor: ActorItem,
                     @SerializedName("repo") val repo: RepoItem,
                     @SerializedName("created_at") val createdAt: String)

data class ActorItem(@SerializedName("id") val id: Long,
                     @SerializedName("login") val login: String,
                     @SerializedName("avatar_url") val avatarUrl: String)

data class RepoItem(@SerializedName("id") val id: Long,
                    @SerializedName("url") val url: String)

data class RepoUrl(@SerializedName("id") val id: Long,
                   @SerializedName("html_url") val htmlUrl: String)