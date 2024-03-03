pipeline{
    agent any
    tools{
        maven 'maven-3.9.6'
    }
    stages{
        stage("build jar"){
            steps{
                script{
                    echo 'Building the application...'
                    sh 'mvn package'
                }
            }
        }
        stage("build image"){
            steps{
                script{
                    echo 'Building the docker images...'
                    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASS', usernameVariable: 'USERNAME')])
                    {
                        sh 'docker build -t bikash789/product-service-private:psa-2.0 .'
                        sh "docker login -u $USERNAME -p $PASS"
                        sh "docker push bikash789/product-service-private:psa-2.0"
                    }

                }
            }
        }
        stage("deploy"){
            steps{
                script{
                    echo "Deploying the application..."
                }
            }
        }
    }
}