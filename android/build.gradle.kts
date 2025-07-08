buildscript {
  repositories {
    google()
    mavenCentral()
  }
  dependencies {
    // używamy AGP 8.8.x, która współpracuje z Gradle 8.12 i JDK 23
    classpath("com.android.tools.build:gradle:8.8.2")
  }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
