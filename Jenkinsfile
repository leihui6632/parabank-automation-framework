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

    }
    
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}