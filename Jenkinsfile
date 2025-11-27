pipeline {
    agent any

    tools {
        maven 'Maven_4.0.0'
        jdk 'JDK_21'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
                    steps {
                        sh 'java -jar target/stsr3-1.0-SNAPSHOT.jar &'
                    }
                }

    }
}
