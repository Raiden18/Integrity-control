package com.raiden.karpukhinomgupsdiplom.application

import com.livetyping.permission.PermissionBinder
import org.koin.dsl.module.module

val main = module {
    single{PermissionBinder()}
}