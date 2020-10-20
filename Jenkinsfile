//Jenkins build pipeline

    pipeline {
        environment {
            PROD_URL  = "https://www.google.com" // replace with correct url, maybe even set variable in K8S block
        }
        agent any
        stages {
            stage('Build and Test Charades App') {
                steps {
                    echo '***** Building charades app and running all tests.... *****'
                    sh 'mvn -f pom.xml clean compile package'
                }
                post {
                    success {
                        echo "Build succeeded and all tests passed!"
                        echo "Archive the application war file"
                        sh 'ls'
                        archiveArtifacts artifacts: 'target/ROOT.war'
                    }
                }
            }


            stage('Build and Push Docker Image') {
                steps {
                    echo '***** Building Docker image and pushing to Docker Hub *****'
                    sh 'chmod u+x scripts/buildAndPushDockerImage.sh'
                    sh 'scripts/buildAndPushDockerImage.sh \"corona-charades-app\" \"zmarbach22\" \"Buggywhip22!!\" '
                }
                post {
                    success {
                        echo "Docker image successfully built and pushed!"
                    }
                }
            }

            stage('Deploy app to K8S Cluster') {
                steps {
                    timeout(time: 15, unit: 'MINUTES') {
                        input(message: "Should we deploy app to AWS?")
                    }
                    echo '***** Deploying app to K8S cluster on AWS*****'
                    sh 'kubectl apply -f k8s-manifest.yaml'
                }
                post {
                    success {
                        echo "Deployment successful. App is now running on K8S cluster in AWS"
                        echo "Go here to see the site live in production ---> $PROD_URL"
                    }
                }
            }
        }
    }
