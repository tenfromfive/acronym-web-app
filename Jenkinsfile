library identifier: "pipeline-library@master",
retriever: modernSCM(
  [
    $class: "GitSCMSource",
    remote: "https://github.com/redhat-cop/pipeline-library.git"
  ]
)

openshift.withCluster() {

  env.NAMESPACE = openshift.project()
  env.POM_FILE = env.BUILD_CONTEXT_DIR ? "${env.BUILD_CONTEXT_DIR}/pom.xml" : "pom.xml"
  env.BUILD_OUTPUT_DIR = env.PIPELINE_CONTEXT_DIR ? "${env.PIPELINE_CONTEXT_DIR}/target" : "target"
  env.APP_NAME = "${env.JOB_NAME}".replaceAll(/-?${env.PROJECT_NAME}-?/, '').replaceAll(/-?pipeline-?/, '').replaceAll('/','')
  env.BUILD = "${env.NAMESPACE}"
  env.DEV = env.BUILD.replace('ci-cd', 'dev')
  env.TEST = env.BUILD.replace('ci-cd', 'test')

  echo "Starting Pipeline for ${APP_NAME}..."

}

pipeline {

  // Use Jenkins Maven slave
  // Jenkins will dynamically provision this as OpenShift Pod
  // All the stages and steps of this Pipeline will be executed on this Pod
  // After Pipeline completes the Pod is killed so every run will have clean
  // workspace

  
  agent {
    label 'jenkins-slave-mvn'
  }

  // Pipeline Stages start here
  // Requeres at least one stage

  stages {

    // Run Maven build, skipping tests
    stage('Build'){
      steps {
        sh "mvn -B clean install -DskipTests=true -f ${POM_FILE}"
      }
    }
/*
    // Run Maven unit tests
    stage('Unit Test'){
      steps {
        sh mvn -B test -f ${POM_FILE}"
      }
    }
*/

    // Build Container Image using the artifacts produced in previous stages
    stage('Build Container Image'){
      steps {
        binaryBuild(projectName: env.BUILD, buildConfigName: env.APP_NAME, artifactsDirectoryName: env.BUILD_OUTPUT_DIR)
      }
    }
    
    stage('Promote from Build to Dev') {
      steps {
        tagImage(sourceImageName: env.APP_NAME, sourceImagePath: env.BUILD, toImagePath: env.DEV)
      }
    }

/*
    stage ('Verify Deployment to Dev') {
      steps {
        verifyDeployment(projectName: env.DEV, targetApp: env.APP_NAME)
      }
    }

    stage('Promotion gate') {
      steps {
        script {
          input message: 'Promote application to Test?'
        }
      }
    }

    stage('Promote from Dev to Test') {
      steps {
        tagImage(sourceImageName: env.APP_NAME, sourceImagePath: env.DEV, toImagePath: env.TEST)
      }
    }

    stage ('Verify Deployment to Test') {
      steps {
        verifyDeployment(projectName: env.TEST, targetApp: env.APP_NAME)
      }
    }

    */

  }
}