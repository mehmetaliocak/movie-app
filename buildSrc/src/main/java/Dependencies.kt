object Versions {
    const val kotlin = "1.8.21"
    const val ktor = "2.3.1"
    const val koinCore = "3.4.0"
    const val koinAndroid = "3.4.0"
    const val koinAndroidCompose = "3.4.4"
    const val coroutines = "1.6.4"
    const val serialization = "1.5.0"
    const val kspPlugin = "1.9.0-Beta-1.0.11"
    const val kmpNativeCoroutinesVersion = "1.0.0-ALPHA-10-kotlin-1.9.0-Beta"
}

object AndroidSdk {
    const val min = 26
    const val compile = 33
    const val target = compile
}

object Deps {

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koinCore}"
        const val android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koinAndroidCompose}"
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val darwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
    }

    object Kotlinx {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val serializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.serialization}"
    }

}
