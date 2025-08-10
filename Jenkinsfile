pipeline {
    agent any
    
    tools {
        jdk 'jdk24'  
        maven 'maven'  
    }
    
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                url: 'https://github.com/leihui6632/parabank-automation-framework.git'
            }
        }
        
        stage('Build & Test') {
            steps {  
                dir('parabank-automation-framework') { 
                    sh 'mvn clean install'
                }
            }
        }
        
        stage('Publish Report') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target',
                    reportFiles: 'emailable-report.html',
                    reportName: 'HTML Report'
                ])
            }
        }

    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}