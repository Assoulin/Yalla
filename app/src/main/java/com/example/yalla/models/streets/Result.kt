package com.example.yalla.models.streets


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("fields")
    val fields: List<Any>,
    @SerializedName("include_total")
    val includeTotal: Boolean,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("q")
    val q: String,
    @SerializedName("records")
    val records: List<Any>,
    @SerializedName("records_format")
    val recordsFormat: String,
    @SerializedName("resource_id")
    val resourceId: String,
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_estimation_threshold")
    val totalEstimationThreshold: Any,
    @SerializedName("total_was_estimated")
    val totalWasEstimated: Boolean
)