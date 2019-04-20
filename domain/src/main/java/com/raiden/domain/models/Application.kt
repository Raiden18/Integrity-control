package com.raiden.domain.models

data class Application(
    val name: String,
    val versionNameMd5: String,
    val packageName: String
)