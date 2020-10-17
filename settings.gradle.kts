pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
    plugins { 
        val quarkusVersion: String by settings
        id("io.quarkus") version "${quarkusVersion}"
    }
}
rootProject.name = "quarkus-sample"