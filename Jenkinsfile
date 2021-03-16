pipeline {
  agent any 
  stages {
    stage("Build Axelor") {
      step {
        echo 'executing command'
        withGradle() {
          sh './gradlew -x test build'
        }
      }
    }
  }
}
