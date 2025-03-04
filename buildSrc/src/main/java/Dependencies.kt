object Versions {
    const val kotlin = "1.8.21"
    const val ktor = "2.3.1"
    const val koinCore = "3.4.0"
    const val koinAndroid = "3.4.0"
    const val koinAndroidCompose = "3.4.4"
    const val coroutines = "1.6.4"
    const val serialization = "1.5.0"
    const val kspPlugin = "1.8.21-1.0.11"
    const val kmpNativeCoroutinesVersion = "1.0.0-ALPHA-10"

    const val activityCompose = "1.7.1"
    const val lifecycleRuntimeKtx = "2.6.1"
    const val lifecycleViewmodelKtx = "2.6.1"

    const val compose = "1.4.3"
    const val composeCompiler = "1.4.7"
    const val navCompose = "2.5.3"
    const val composeMaterial3 = "1.1.0"
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

    object AndroidX {
        const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleRuntimeKtx}"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
        const val lifecycleViewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewmodelKtx}"
        const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    }

    object Compose {
        const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
        const val coilCompose = "io.coil-kt:coil-compose:2.0.0"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val pagingRuntime = "androidx.paging:paging-runtime:3.1.1"
        const val pagingCompose = "androidx.paging:paging-compose:1.0.0-alpha17"
    }
}
