package org.s1s.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform