# jenkins-shared-libraries
----------------------------------------------------------------------------------

1. Create a GitHub Repository
   
       * Go to GitHub and create a new repository.
       * Example Repo Name: jenkins-shared-libraries

2. Define Library Structure

        * Inside your GitHub repo:
        * Create a folder named: vars
        * Inside vars, create a Groovy script file.

3. Example File: (Hello.groovy)
         
	 	def call() {
   		echo "Hello dosto" }
          

4. Configure Jenkins
   
        * Go to Jenkins Dashboard
        * Click on Manage Jenkins
        * Select Configure System
        * Scroll to Global Pipeline Libraries


5. Click Add and fill in:

        * Name: Shared (or any name)
        * Default Version: e.g., main
        * Retrieval Method: Modern SCM
        * Source Code Management: Git
        * Project Repository: Your GitHub repo URL
        * Add credentials only if it’s a private repo


6. Use Shared Library in Pipeline
   
        * In your Jenkins pipeline (Jenkinsfile), reference the shared library using the @Library annotation

           @Library('Shared') _
            pipeline {
            agent any
              stages {
                 stage('Hello') {
                    steps {
                       script {
                          hello()
                } } } } }


