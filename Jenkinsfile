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
        stage("increment version")
        {
            steps{
                script{
                    echo 'incrementing app version...'
                    sh 'mvn build-helper:parse-version versions:set -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[1][1]
                    env.TAG_NAME = "$version-$BUILD_NUMBER"
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
                    buildImage('bikash789/product-service-private', TAG_NAME)
                    dockerLogin()
                    dockerPush('bikash789/product-service-private', TAG_NAME)
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
        stage("commit version update")
        {
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'github-bikash', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]) {
                        sh("git config --global user.email 'jenkins@example.com'")
                        sh("git config --global user.name 'Jenkins'")
                        sh('git status')
                        sh('git branch')
                        sh('git config --list')

                        sh("git remote set-url origin https://github.com/bikash-789/Ecom-ProductService")
                        sh('git add .')
                        sh('git commit -m "CI: Version bump"')
                        sh('git push origin HEAD:jenkins-shared-lib')
                    }
                }
            }
        }
    }
}