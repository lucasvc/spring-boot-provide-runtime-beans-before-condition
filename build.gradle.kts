plugins {
	id("org.springframework.boot") version "2.2.6.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
    java
}

repositories {
    mavenCentral()
}

dependencies {
	implementation("org.springframework.boot", "spring-boot-starter")
	implementation("org.springframework.boot", "spring-boot-autoconfigure")
	implementation("org.springframework.boot", "spring-boot-actuator-autoconfigure")
	implementation("javax.jms", "javax.jms-api")
	implementation("org.springframework", "spring-jms")

    testImplementation("org.junit.jupiter", "junit-jupiter", "5.6.2")
	testImplementation("org.springframework.boot", "spring-boot-starter-test") {
		exclude("org.junit.vintage", "junit-vintage-engine")
	}
}

tasks {
	test {
		useJUnitPlatform()
		testLogging {
			events("passed", "skipped", "failed")
		}
	}
}
