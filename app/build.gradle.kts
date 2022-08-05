import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}
val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    compileSdk = Constants.compileSdk

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeVersion
    }

    lint {
        baseline = file("lint-baseline.xml")
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
        compose = true
    }

    defaultConfig {
        applicationId = Constants.packageName
        minSdk = Constants.minSdk
        targetSdk = Constants.targetSdk
        versionCode = Constants.versionCode
        versionName = Constants.versionName
        buildConfigField("String", "HOUS_URL", properties.getProperty("HOUS_URL"))
        buildConfigField(
            "String",
            "DUMMY_ACCESS_TOKEN",
            properties.getProperty("DUMMY_ACCESS_TOKEN")
        )
        buildConfigField("String", "ROOM_ID", properties.getProperty("ROOM_ID"))
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":data"))

    KotlinDependencies.run {
        implementation(kotlin)
        implementation(coroutine)
    }

    KTXDependencies.run {
        implementation(coreKTX)
        implementation(activityKTX)
        implementation(fragmentKTX)
        implementation(lifecycleKTX)
    }

    AndroidXDependencies.run {
        implementation(appCompat)
        implementation(constraintLayout)
        implementation(hilt)
        implementation(recyclerview)
        implementation(composeActivity)
        implementation(composeAnimation)
        implementation(composeUi)
        implementation(composeViewModel)
        implementation(composeTheme)
    }

    KaptDependencies.run {
        kapt(hiltKapt)
        kapt(glideKapt)
    }

    ThirdPartyDependencies.run {
        implementation(coil)
        implementation(timber)
        implementation(interceptor)
        implementation(gson)
        implementation(retrofit2)
        implementation(retrofit2Converter)
        implementation(glide)
        implementation(lottie)
    }

    MaterialDependencies.run {
        implementation(material)
        implementation(composeMaterial)
        implementation(composeAdapter)
    }

    TestDependencies.run {
        testImplementation(jUnit)
        androidTestImplementation(androidTest)
        androidTestImplementation(espresso)
        androidTestImplementation(composeTest)
    }
}

ktlint {
    android.set(true)
    coloredOutput.set(true)
    verbose.set(true)
    outputToConsole.set(true)
    disabledRules.set(setOf("max-line-length", "no-wildcard-imports", "import-ordering"))
    reporters {
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
        reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
    }
}
