pipeline {
    agent any

    tools {
        maven 'maven'
        jdk 'jdk21'
    }

    parameters {
        choice(
            name: 'TEST_TYPE',
            choices: ['api', 'web', 'all'],
            description: 'Какие тесты запускать'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Run tests') {
            steps {
                script {
                    if (params.TEST_TYPE == 'api') {
                        bat 'mvn clean test -pl api-test -am'
                    } else if (params.TEST_TYPE == 'web') {
                        bat 'mvn clean test -pl web-test -am'
                    } else { // all
                        echo 'Running API tests'
                        bat 'mvn clean test -pl api-test -am'
                        echo 'Running Web tests'
                        bat 'mvn clean test -pl web-test -am'
                    }
                }
            }
        }

        stage('Allure report') {
            steps {
                script {
                    def results = []

                    if (params.TEST_TYPE == 'api') {
                        results += [[path: 'api-test/target/allure-results']]
                    } else if (params.TEST_TYPE == 'web') {
                        results += [[path: 'web-test/target/allure-results']]
                    } else { // all
                        results += [[path: 'api-test/target/allure-results']]
                        results += [[path: 'web-test/target/allure-results']]
                    }

                    allure includeProperties: false,
                           jdk: '',
                           results: results
                }
            }
        }
    }

    post {
        always {
           junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
        }
    }
}
