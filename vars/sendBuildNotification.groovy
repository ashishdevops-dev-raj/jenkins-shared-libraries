def call(Map config = [:]) {
    String projectName = config.projectName ?: 'Unknown Project'
    String emailSender = config.emailSender ?: 'noreply@example.com'
    String emailRecipients = config.emailRecipients ?: 'team@example.com'
    String status = currentBuild.result ?: 'SUCCESS'

    echo "Pipeline completed. Status: ${status}"

    def subjectPrefix = status == 'SUCCESS' ? '[SUCCESS]' : '[FAILURE]'
    def statusColor = status == 'SUCCESS' ? '#28a745' : '#dc3545'
    def statusMessage = status == 'SUCCESS' ? 'Build Successful' : 'Build Failed'

    emailext(
        attachLog: true,
        subject: "${subjectPrefix} Build #${env.BUILD_NUMBER} - ${projectName}",
        from: emailSender,
        to: emailRecipients,
        mimeType: 'text/html',
        body: """
        <html>
            <body style="font-family: Arial, sans-serif;">
                <h2 style="color: ${statusColor};">${statusMessage}</h2>
                <hr>
                <p><strong>Project:</strong> ${projectName}</p>
                <p><strong>Job:</strong> ${env.JOB_NAME}</p>
                <p><strong>Build Number:</strong> ${env.BUILD_NUMBER}</p>
                <p><strong>Build URL:</strong> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
            </body>
        </html>
        """
    )
}
