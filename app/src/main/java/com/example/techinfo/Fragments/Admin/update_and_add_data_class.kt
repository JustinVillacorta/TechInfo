package com.example.techinfo.Fragments.Admin

data class update_and_add_data_class(
    val modelName: String,
    val specs: String,
    val category: String,
    val brand: String,
    val socketType: String,
    val baseClockSpeed: String,
    val maxTurboBoostClockSpeed: String,
    val compatibleChipsets: String,
    val link: String,
    val cacheSizeMb: String,
    val integratedGraphics: String?,  // Make nullable (add ?)
    val tdp: String,
    val cores: String,
    val threads: String,
)
