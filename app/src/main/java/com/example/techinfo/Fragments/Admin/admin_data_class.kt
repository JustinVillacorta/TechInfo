package com.example.techinfo.Fragments.Admin

data class admin_data_class(
    val processorId: String,
    val ModelName: String,
    val Specs: String,
    val Category: String,
    val brand: String,
    val socket_type: String,
    val base_clock_speed: String,
    val max_turbo_boost_clock_speed: String?,  // Nullable type to allow null values
    val compatible_chipsets: String,
    val link: String,
    val cache_size_mb: String,
    val performance_score: String,
    val integrated_graphics: String?,  // Nullable type
    val tdp: String,
    val cores: String,
    val threads: String,
    val created_at: String,
    val updated_at: String
)
