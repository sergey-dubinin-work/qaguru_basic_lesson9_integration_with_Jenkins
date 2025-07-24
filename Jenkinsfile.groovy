pipeline {
    agent any

    tools {
        gradle 'gradle 8.14.2'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout changelog: false, poll: false, scm: scmGit(
                        branches: [[name: '*/main']],
                        extensions: [],
                        userRemoteConfigs: [[
                                                    credentialsId: 'fb9e6044-4b16-4851-829e-f39b7b4b4f3c',
                                                    url          : 'https://github.com/sergey-dubinin-work/qaguru_basic_lesson23_Allure_TestOps.git'
                                            ]]
                )
            }
        }
        stage('Run tests') {
            steps {
                withAllureUpload(
                        credentialsId: 'AllureTestOpsTokenForJenkins',
                        name: "${JOB_NAME} - #${BUILD_NUMBER}",
                        projectId: '1',
                        results: [[
                                          path: 'build/allure-results'
                                  ]],
                        serverId: 'Allure TestOps trial (sergeydubininwork)',
                        tags: ''
                ) {
                    sh 'gradle clean test'
                }

            }
        }
        stage('Generate allure report') {
            steps {
                allure(
                        includeProperties: false,
                        jdk: '',
                        results: [[
                                          path: 'build/allure-results'
                                  ]]
                )
            }
        }
    }
}
