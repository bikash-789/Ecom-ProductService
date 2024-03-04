def buildJar()
{
    echo 'Building the application...'
    sh 'mvn package'
}

def buildImage()
{
    echo 'Building the docker images...'
    withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'PASS', usernameVariable: 'USERNAME')]) {
        sh('docker build -t bikash789/product-service-private:psa-2.0 .')
        sh('docker login -u $USERNAME -p $PASS')
        sh('docker push bikash789/product-service-private:psa-2.0')
    }
}

def deployApp()
{
    echo "Deploying the application..."
}

return this
