plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("org.jlleitschuh.gradle.ktlint") version "10.3.0"
}

android {
    compileSdk = Constants.compileSdk

    defaultConfig {
        minSdk = Constants.minSdk
        targetSdk = Constants.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(project(":domain"))

    KotlinDependencies.run {
        implementation(kotlin)
    }

    KTXDependencies.run {
        implementation(coreKTX)
    }

    AndroidXDependencies.run {
        implementation(appCompat)
        implementation(constraintLayout)
    }

    TestDependencies.run {
        implementation(jUnit)
        implementation(androidTest)
        implementation(espresso)
    }

    ThirdPartyDependencies.run {
        implementation(interceptor)
        implementation(gson)
        implementation(retrofit2)
        implementation(retrofit2Converter)
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
