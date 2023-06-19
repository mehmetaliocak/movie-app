plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization")
}

android {
    namespace = "com.mehmetaliocak.movie_app.android"
    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = "com.mehmetaliocak.movie_app.android"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    with(Deps.AndroidX) {
        implementation(lifecycleRuntimeCompose)
        implementation(lifecycleRuntimeKtx)
        implementation(lifecycleViewmodelKtx)
        implementation(activityCompose)
    }

    with(Deps.Compose) {
        implementation(compiler)
        implementation(ui)
        implementation(uiGraphics)
        implementation(navigation)
        implementation(coilCompose)
        implementation(material)
        implementation(uiTooling)
        implementation(pagingRuntime)
        implementation(pagingCompose)
    }

    with(Deps.Koin) {
        implementation(core)
        implementation(android)
        implementation(compose)
    }

    implementation("io.github.raamcosta.compose-destinations:core:1.8.42-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.8.42-beta")

    implementation(project(":shared"))
}