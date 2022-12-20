pipeline {
	environment
           {    registry = "ashkouki/devops_project"
                registryCredential = 'dockerhub_id'
                NEXUS_VERSION="nexus3"
                NEXUS_PROTOCOL="http"
                NEXUS_URL="http://localhost:8085"
                NEXUS_REPOSITORY="maven-snapshots"
                NEXUS_CREDENTIAL_ID="nexus-user-credentials"
            }
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
		
		stage("SONAR Code-Inspection"){
             steps {
                bat 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=ash'

            }
        }
		 stage("Deploy Artifacts to Nexus"){
             steps {
                bat 'mvn deploy'

                     }
                 }
				 
		stage('Building our image') {
             steps {
                script {
                    dockerImage = docker.build registry + ":$BUILD_NUMBER"

                         }
                    }
                }
         stage('Push the image to Docker Hub') {

             steps {

                script {
                    docker.withRegistry( '', registryCredential ) {

                        dockerImage.push()

                           }

                        }

                      }

                 }           

		stage('Cleaning up') {
             steps {
                 bat "docker rmi $registry:$BUILD_NUMBER"
                   }
               }
			   
		stage("Create Docker-compose"){
              steps {
                 bat 'mvn clean package'
                                              
                 bat 'docker-compose up -d --build'


                }

         }		 
	}
}