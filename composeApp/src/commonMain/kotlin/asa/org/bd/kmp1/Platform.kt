package asa.org.bd.kmp1

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform