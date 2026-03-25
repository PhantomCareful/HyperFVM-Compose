plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.careful.hyperfvm.compose"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.careful.hyperfvm.compose"
        minSdk = 31
        targetSdk = 36
        versionCode = 1
        versionName = "4.0.0.20260324"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true // 启用代码混淆
            isShrinkResources = true // 启用资源缩减（移除无用资源）
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // 启用Compose编译器优化
    composeOptions {
        kotlinCompilerExtensionVersion = "1.6.1"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.compose.animation.core)
    implementation(libs.androidx.compose.ui.unit)
    implementation(libs.androidx.navigationevent.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

    // miuix主题
    implementation(libs.miuix)
    implementation(libs.miuix.icons)
    implementation(libs.miuix.navigation3.ui)
    implementation(libs.miuix.navigation3.adaptive)

    // 异步加载图片
    implementation(libs.coil.compose)

    // Haze
    implementation(libs.haze)
    // Liquid Glass
    implementation(libs.backdrop)
    implementation(libs.capsule)

    // SQLite
    implementation(libs.androidx.sqlite.ktx)
    implementation(libs.androidx.sqlite.framework)

    // 协程（异步数据库操作）
    implementation(libs.kotlinx.coroutines.android)

    // ViewModel 核心依赖
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    // ViewModel 与 Compose 集成的关键依赖
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // LiveData 依赖（可选，若后续用到）
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Markdown
    implementation(libs.multiplatform.markdown.renderer)
    implementation(libs.multiplatform.markdown.renderer.m3)
}