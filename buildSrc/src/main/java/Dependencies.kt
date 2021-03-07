import org.gradle.api.JavaVersion

object Releases {
    const val versionCode = 1
    const val javaVersionName = "1.0"
}

object Config {
    const val compileSdkVersion = 30
    const val buildToolsVersion = "30.0.2"

    const val applicationId = "ru.gressor.skyengdictionary"
    const val minSdkVersion = 21
    const val targetSdkVersion = 30

    val javaVersion = JavaVersion.VERSION_1_8
}

object Modules {
    const val app = ":app"

    const val featureHistoryScreen = ":historyScreen"
}

object Tools {
    const val multidex = "com.android.support:multidex:1.0.3"
}

object Kotlin {
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:1.4.31"
    const val coreKtx = "androidx.core:core-ktx:1.3.2"
    const val kotlinxCoroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2"
    const val kotlinxCoroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"
}

object Koin {
    const val koinAndroid = "org.koin:koin-android:2.2.2"
    const val koinAndroidxViewmodel = "org.koin:koin-androidx-viewmodel:2.2.2"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:1.2.0"
}

object Design {
    const val material = "com.google.android.material:material:1.3.0"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
}

object Room {
    const val roomRuntime = "androidx.room:room-runtime:2.2.6"
    const val roomKtx = "androidx.room:room-ktx:2.2.6"
    const val roomCompiler = "androidx.room:room-compiler:2.2.6"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val converterGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val adapterRxjava3 = "com.squareup.retrofit2:adapter-rxjava3:2.9.0"
    const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.0"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:4.11.0"
    const val compiler = "com.github.bumptech.glide:compiler:4.11.0"
}

object Testing {
    const val junit = "junit:junit:4.13.1"
    const val androidxJunit = "androidx.test.ext:junit:1.1.2"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
}
