pipeline {
    agent any
    
    tools {
        jdk 'jdk11'  
        maven 'maven3.6.0'  
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
                sh 'mvn clean install'
            }
        }
        
        stage('Publish Report') {
            steps {
                publishHTML target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: true,
                    reportDir: 'target/surefire-reports',
                    reportFiles: 'emailable-report.html',
                    reportName: 'HTML Report'
                ]
            }
        }
    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}