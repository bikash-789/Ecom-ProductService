@Library('jenkins-shared-library')
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
        stage("build image"){
            steps{
                script{
                    buildImage('bikash789/product-service-private', 'psa-2.1')
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