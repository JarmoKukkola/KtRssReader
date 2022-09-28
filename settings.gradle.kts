pluginManagement {
    plugins {
        id("com.google.devtools.ksp") version "1.6.10-1.0.2"
        kotlin("jvm") version "1.6.10"
        id("com.android.application") version "7.2.1"
        id("com.android.library") version "7.2.1"
    }
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

include(
    ":kotlin",
    ":testCommon",
    ":processorTest",
    ":processor",
    ":annotation",
    ":android",
    ":app",
    ":rssUtils"
)
rootProject.name = "KtRssReader"