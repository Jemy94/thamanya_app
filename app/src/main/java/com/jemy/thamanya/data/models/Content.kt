package com.jemy.thamanya.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Content(
    @SerialName("article_id")
    val articleId: String? = "",
    @SerialName("audio_url")
    val audioUrl: String? = "",
    @SerialName("audiobook_id")
    val audiobookId: String? = "",
    @SerialName("author_name")
    val authorName: String? = "",
    @SerialName("avatar_url")
    val avatarUrl: String? = "",
    @SerialName("chapters")
    val chapters: List<Nothing>? = listOf(),
    @SerialName("description")
    val description: String? = "",
    @SerialName("duration")
    val duration: Int? = 0,
    @SerialName("episode_count")
    val episodeCount: Int? = 0,
    @SerialName("episode_id")
    val episodeId: String? = "",
    @SerialName("episode_type")
    val episodeType: String? = "",
    @SerialName("free_transcript_url")
    val freeTranscriptUrl: String? = "",
    @SerialName("language")
    val language: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("number")
    val number: String? = "",
    @SerialName("paid_early_access_audio_url")
    val paidEarlyAccessAudioUrl: String? = "",
    @SerialName("paid_early_access_date")
    val paidEarlyAccessDate: String? = "",
    @SerialName("paid_exclusive_start_time")
    val paidExclusiveStartTime: Int? = 0,
    @SerialName("paid_exclusivity_type")
    val paidExclusivityType: String? = "",
    @SerialName("paid_is_early_access")
    val paidIsEarlyAccess: Boolean? = false,
    @SerialName("paid_is_exclusive")
    val paidIsExclusive: Boolean? = false,
    @SerialName("paid_is_exclusive_partially")
    val paidIsExclusivePartially: Boolean? = false,
    @SerialName("paid_is_now_early_access")
    val paidIsNowEarlyAccess: Boolean? = false,
    @SerialName("paid_transcript_url")
    val paidTranscriptUrl: String? = "",
    @SerialName("podcast_id")
    val podcastId: String? = "",
    @SerialName("podcast_name")
    val podcastName: String? = "",
    @SerialName("podcastPopularityScore")
    val podcastPopularityScore: Int? = 0,
    @SerialName("podcastPriority")
    val podcastPriority: Int? = 0,
    @SerialName("popularityScore")
    val popularityScore: Int? = 0,
    @SerialName("priority")
    val priority: Int? = 0,
    @SerialName("release_date")
    val releaseDate: String? = "",
    @SerialName("score")
    val score: Double? = 0.0,
    @SerialName("season_number")
    val seasonNumber: String? = "",
    @SerialName("separated_audio_url")
    val separatedAudioUrl: String? = ""
)