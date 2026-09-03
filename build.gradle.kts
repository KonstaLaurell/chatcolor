plugins {
    java
}

// Update this if you want a different group or version name
group = "com.tyhjyys.customchat"
version = "1.0"

// Matches the Java 25 requirement from your previous file
java.toolchain.languageVersion = JavaLanguageVersion.of(25)

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    // We use compileOnly here so the Paper API is NOT built into your jar.
    // The server already has it! This is what keeps your file size so tiny.
    compileOnly("io.papermc.paper:paper-api:26.2.build.121-stable")
}

// This automatically updates your plugin.yml with the version and name above
tasks.processResources {
    val props = mapOf(
        "NAME" to rootProject.name, 
        "VERSION" to project.version, 
        "PACKAGE" to project.group
    )
    inputs.properties(props)
    filesMatching("**/plugin.yml") {
        expand(props)
    }
}