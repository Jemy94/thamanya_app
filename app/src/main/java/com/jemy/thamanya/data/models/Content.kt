package com.jemy.thamanya.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Content(
    @SerializedName("article_id")
    @SerialName("article_id")
    val articleId: String? = "",
    @SerializedName("audio_url")
    @SerialName("audio_url")
    val audioUrl: String? = "",
    @SerializedName("audiobook_id")
    @SerialName("audiobook_id")
    val audiobookId: String? = "",
    @SerializedName("author_name")
    @SerialName("author_name")
    val authorName: String? = "",
    @SerializedName("avatar_url")
    @SerialName("avatar_url")
    val avatarUrl: String? = "",
    @SerializedName("chapters")
    @SerialName("chapters")
    val chapters: List<String>? = listOf(),
    @SerializedName("cover_url")
    @SerialName("cover_url")
    val coverUrl: String? = "",
    @SerializedName("description")
    @SerialName("description")
    val description: String? = "",
    @SerializedName("duration")
    @SerialName("duration")
    val duration: String? = "0.0",
    @SerializedName("episode_count")
    @SerialName("episode_count")
    val episodeCount: String? = "0",
    @SerializedName("episode_id")
    @SerialName("episode_id")
    val episodeId: String? = "",
    @SerializedName("episode_type")
    @SerialName("episode_type")
    val episodeType: String? = "",
    @SerializedName("free_transcript_url")
    @SerialName("free_transcript_url")
    val freeTranscriptUrl: String? = "",
    @SerializedName("image_url")
    @SerialName("image_url")
    val imageUrl: String? = "",
    @SerializedName("image")
    @SerialName("image")
    val image: String? = "",
    @SerializedName("language")
    @SerialName("language")
    val language: String? = "",
    @SerializedName("name")
    @SerialName("name")
    val name: String? = "",
    @SerializedName("number")
    @SerialName("number")
    val number: String? = "",
    @SerializedName("paid_early_access_audio_url")
    @SerialName("paid_early_access_audio_url")
    val paidEarlyAccessAudioUrl: String? = "",
    @SerializedName("paid_early_access_date")
    @SerialName("paid_early_access_date")
    val paidEarlyAccessDate: String? = "",
    @SerializedName("paid_exclusive_start_time")
    @SerialName("paid_exclusive_start_time")
    val paidExclusiveStartTime: String? = "0.0",
    @SerializedName("paid_exclusivity_type")
    @SerialName("paid_exclusivity_type")
    val paidExclusivityType: String? = "",
    @SerializedName("paid_is_early_access")
    @SerialName("paid_is_early_access")
    val paidIsEarlyAccess: Boolean? = false,
    @SerializedName("paid_is_exclusive")
    @SerialName("paid_is_exclusive")
    val paidIsExclusive: Boolean? = false,
    @SerializedName("paid_is_exclusive_partially")
    @SerialName("paid_is_exclusive_partially")
    val paidIsExclusivePartially: Boolean? = false,
    @SerializedName("paid_is_now_early_access")
    @SerialName("paid_is_now_early_access")
    val paidIsNowEarlyAccess: Boolean? = false,
    @SerializedName("paid_transcript_url")
    @SerialName("paid_transcript_url")
    val paidTranscriptUrl: String? = "",
    @SerializedName("podcast_id")
    val podcastId: String? = "",
    @SerializedName("podcast_name")
    @SerialName("podcast_name")
    val podcastName: String? = "",
    @SerializedName("podcastPopularityScore")
    @SerialName("podcastPopularityScore")
    val podcastPopularityScore: String? = "0",
    @SerializedName("podcastPriority")
    @SerialName("podcastPriority")
    val podcastPriority: String? = "0",
    @SerializedName("popularityScore")
    @SerialName("popularityScore")
    val popularityScore: String? = "0",
    @SerializedName("priority")
    @SerialName("priority")
    val priority: String? = "0",
    @SerializedName("release_date")
    @SerialName("release_date")
    val releaseDate: String? = "",
    @SerializedName("score")
    @SerialName("score")
    val score: String? = "0.0",
    @SerializedName("season_number")
    @SerialName("season_number")
    val seasonNumber: String? = "",
    @SerializedName("separated_audio_url")
    @SerialName("separated_audio_url")
    val separatedAudioUrl: String? = ""
)