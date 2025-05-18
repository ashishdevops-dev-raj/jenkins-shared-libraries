def call(String projectName, String emailSender, String emailRecipients) {
    def status = currentBuild.result ?: 'SUCCESS'

    echo "Pipeline completed. Status: ${status}"

    if (status == 'SUCCESS') {
        emailext(
            attachLog: true,
            subject: "[SUCCESS] Build #${env.BUILD_NUMBER} - ${projectName}",
            from: emailSender,
            to: emailRecipients,
            mimeType: 'text/html',
            body: """
            <html>
                <body style="font-family: Arial, sans-serif;">
                    <h2 style="color: #28a745;">Build Successful</h2>
                    <hr>
                    <p><strong>Project:</strong> ${projectName}</p>
                    <p><strong>Job:</strong> ${env.JOB_NAME}</p>
                    <p><strong>Build Number:</strong> ${env.BUILD_NUMBER}</p>
                    <p><strong>Build URL:</strong> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>
                </body>
            </html>
            """
        )
    } else if (status == 'FAILURE') {
        emailext(
            attachLog: true,
            subject: "[FAILURE] Build #${env.BUILD_NUMBER} - ${projectName}",
            from: emailSender,
            to: emailRecipients,
            mimeType: 'text/html',
            body: """
            <html>
                <body style="font-family: Arial, sans-serif;">
                    <h2 style="color: #dc3545;">Build Failed</h2>
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
}
