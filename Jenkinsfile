pipeline {
    agent any  // использовать любой доступный агент Jenkins

    tools {
        // Здесь указываешь инструменты, которые настроил в Jenkins:
        maven 'Maven_4.0.0'  // имя Maven в Jenkins (Manage Jenkins → Global Tool Configuration)
        jdk 'JDK_21'         // имя JDK 21 в Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                // Клонирует код из репозитория, где лежит Jenkinsfile
                checkout scm
            }
        }

        stage('Build') {
            steps {
                // Сборка Maven проекта
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Запуск тестов
                sh 'mvn test'
            }
        }

        stage('Archive') {
            steps {
                // Сохраняем артефакты сборки (.jar)
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
