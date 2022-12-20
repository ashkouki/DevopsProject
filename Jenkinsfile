pipeline {

    agent any
    tools {
        maven 'maven'
    }
    stages {
		stage('Checkout GIT'){
             steps{
                 echo 'Pulling...';
                 git branch: 'master',
                 url : 'https://github.com/ashkouki/DevopsProjectGOMYCODE.git';
             }
        } 
        stage('Build') {
            steps {
                   bat 'mvn clean install '
            }
        }
        stage('Tests JUnit / Mockito'){
            steps{
                bat 'mvn test'
            }
        }
	}
}