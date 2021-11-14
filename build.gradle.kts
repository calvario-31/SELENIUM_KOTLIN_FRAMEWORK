import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    id("io.qameta.allure") version "2.6.0"
}

group = "me.calvario31"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    // https://mvnrepository.com/artifact/org.testng/testng
    implementation("org.testng:testng:7.4.0")
    // https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager
    implementation("io.github.bonigarcia:webdrivermanager:5.0.1")
    // https://mvnrepository.com/artifact/io.qameta.allure/allure-testng
    implementation("io.qameta.allure:allure-testng:2.14.0")
    // https://mvnrepository.com/artifact/com.github.javafaker/javafaker
    implementation("com.github.javafaker:javafaker:1.0.2")
    // https://mvnrepository.com/artifact/com.github.ozlerhakan/poiji
    implementation("com.github.ozlerhakan:poiji:3.1.1")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.14.1")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.14.1")
}

allure {
    version = "2.14.0"
    aspectjweaver = true
    clean = true
}

tasks.test {
    useTestNG {
        if (project.hasProperty("suiteName")) {
            val suiteName = project.property("suiteName").toString()
            suites("src/test/resources/suites/$suiteName.xml")
        }
    }

    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
        exceptionFormat = FULL
    }
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}