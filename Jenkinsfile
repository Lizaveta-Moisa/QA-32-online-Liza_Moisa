pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk21'
    }




    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run tests') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Allure report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                      results: [
                      [path: 'web-test/target/allure-results'],
                      [path: 'api/target/allure-results'],
                      [path: 'api-test/target/allure-results'],
                      [path: 'common/target/allure-results'],
                      [path: 'database/target/allure-results']
                      ]
            }
        }
    }

    post {
        always {
           junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
        }
    }
}