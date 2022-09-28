@Suppress(
  "DSL_SCOPE_VIOLATION",
)
plugins {
  id("com.android.library")
  id("kotlin-android")
  id("kotlin-android-extensions")
  alias(libs.plugins.ksp)
}

android {
  namespace = "tw.ktrssreader.utils"
  compileSdk = 33

  defaultConfig {
    minSdk = 16
    targetSdk = 33

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    release {
      isMinifyEnabled = false
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
}

dependencies {
  implementation(project(":android"))
  implementation(project(":annotation"))
  ksp(project(":processor"))
  implementation(libs.kotlinStdlib)
  implementation(libs.bundles.coroutines)
  implementation(libs.coreKtx)
  implementation(libs.appCompat)
  implementation(libs.bundles.coroutines)
  implementation(libs.constraintLayout)
  implementation(libs.lifecycleRuntimeKtx)
  testImplementation("junit:junit:4.13.2")
  testImplementation("junit:junit:4.13.2")
  androidTestImplementation("androidx.test.ext:junit:1.1.3")
  androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}