// @Library('jenkins-shared-library')

library identifier: 'jenkins-shared-library@main', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://github.com/bikash-789/jenkins-shared-library',
     credentialsId: 'github-bikash'
    ]
)
// Testing the github-webhook
def gv
pipeline{
    agent any
    tools{
        maven 'maven-3.9.6'
    }
    stages{
        stage("init")
        {
            steps{
                script{
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar"){
            steps{
                script{
                    buildJar()
                }
            }
        }
        stage("build and push image"){
            steps{
                script{
                    buildImage('bikash789/product-service-private', 'psa-2.1')
                    dockerLogin()
                    dockerPush('bikash789/product-service-private', 'psa-2.1')
                }
            }
        }
        stage("deploy"){
            steps{
                script{
                    gv.deployApp()
                }
            }
        }
    }
}