pipeline {
    agent any

    tools {
        maven 'maven3'
        jdk 'JDK17'
    }

    environment {
        APP_NAME = 'menu-service-app'
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning source code...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                echo 'Packaging application...'
                sh 'mvn package -DskipTests'
            }
        }

        stage('Deploy (Optional)') {
            when {
                branch 'main'
            }
            steps {
                echo 'Deploying ${env.APP_NAME}...'
                // Add deployment steps here, e.g., to a server or cloud service
            }
        }
    }

    post {
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}