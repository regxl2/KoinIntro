package com.example.koinintro

import org.koin.core.qualifier.named
import org.koin.dsl.module

// This module is created inject dependencies to the class, as long as the classes are alive.
// It means dependencies should be alive as long as class are alive.
val activityModule = module{
    scope<MainActivity>{
        scoped { Print() }
        scoped (qualifier = named("hello")) { "hello" }
        scoped (qualifier = named("bye")) {"bye"}
    }
}