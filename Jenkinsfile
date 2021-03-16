pipeline {
  agent any 
  stages {
    stage("Build Axelor") {
      steps {
        echo 'executing command'
        withGradle() {
          sh './gradlew -x test build'
        }
      }
    }
  }
}
