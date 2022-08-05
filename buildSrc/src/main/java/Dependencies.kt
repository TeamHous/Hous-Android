object KotlinDependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coroutine =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutineVersion}"
    const val inject = "javax.inject:javax.inject:1"
}

object AndroidXDependencies {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompatVersion}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerviewVersion}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.composeActivityVersion}"
    const val composeAnimation = "androidx.compose.animation:animation:${Versions.composeVersion}"
    const val composeUi = "androidx.compose.ui:ui-tooling:${Versions.composeVersion}"
    const val composeViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.composeViewModelVersion}"
    const val composeTheme =
        "com.google.accompanist:accompanist-appcompat-theme:${Versions.composeThemeVersion}"
}

object KTXDependencies {
    const val coreKTX = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val activityKTX = "androidx.activity:activity-ktx:${Versions.activityKTXVersion}"
    const val lifecycleKTX =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKTXVersion}"
    const val fragmentKTX = "androidx.fragment:fragment-ktx:${Versions.fragmentKTXVersion}"
}

object TestDependencies {
    const val jUnit = "junit:junit:${Versions.jUnitVersion}"
    const val androidTest = "androidx.test.ext:junit:${Versions.androidTestVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val composeTest = "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}"
}

object MaterialDependencies {
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.composeVersion}"
    const val composeAdapter =
        "com.google.android.material:compose-theme-adapter:${Versions.composeAdapterVersion}"
}

object KaptDependencies {
    const val hiltKapt = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    const val glideKapt = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
}

object ThirdPartyDependencies {
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
    const val interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptorVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val retrofit2Converter =
        "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottieVersion}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
}

object ClassPathPlugins {
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
}
