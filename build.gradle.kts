plugins {
    java
    id("io.quarkus")
    id("org.seasar.doma.compile") version "1.1.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    val quarkusVersion: String by project
    val quarkusDomaVersion: String by project
    val domaVersion: String by project
    implementation(enforcedPlatform("io.quarkus:quarkus-bom:${quarkusVersion}"))
    implementation("io.quarkus:quarkus-jdbc-postgresql:${quarkusVersion}")
    implementation("io.quarkus:quarkus-resteasy:${quarkusVersion}")
    implementation("io.quarkus:quarkus-resteasy-jsonb:${quarkusVersion}")
    implementation("io.quarkiverse.doma:quarkus-doma:$quarkusDomaVersion")
    implementation("org.seasar.doma:doma-core:$domaVersion")
    annotationProcessor("org.seasar.doma:doma-processor:$domaVersion")
    testImplementation("io.quarkus:quarkus-junit5:${quarkusVersion}")
    testImplementation("io.rest-assured:rest-assured:5.5.6")
}

tasks {
    val encoding = "UTF-8"
    
    compileJava {
        options.encoding = encoding
        options.compilerArgs.add("-parameters")
    }

    compileTestJava {
        options.encoding = encoding
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
