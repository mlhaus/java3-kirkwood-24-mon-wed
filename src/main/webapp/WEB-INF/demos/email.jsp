<html>
<head>
    <title>Send an email</title>
</head>
<body>
<c:choose>
    <c:when test="${not empty success and success eq true}">
        <p>Email sent</p>
    </c:when>
    <c:when test="${not empty success and success eq false}">
        <p>Email was not sent</p>
    </c:when>
    <c:otherwise>
        <p>Use the form below to send an email</p>
    </c:otherwise>
</c:choose>
<form action="email" method="post">
    <!-- Email Address -->
    <label for="email">Email Address:</label>
    <input type="text" id="email" name="email" required>
    <br>

    <!-- Subject -->
    <label for="subject">Subject:</label>
    <input type="text" id="subject" name="subject" required>
    <br>

    <!-- Message -->
    <label for="message">Message:</label>
    <textarea id="message" name="message" rows="4" required></textarea>
    <br>

    <!-- Submit Button -->
    <input type="submit" value="Submit">
</form>
</body>
</html>
