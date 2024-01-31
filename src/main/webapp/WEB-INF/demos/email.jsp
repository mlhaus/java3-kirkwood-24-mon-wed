<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${success}">
    <p>Email sent</p>
</c:if>
<form action="${appUrl}/email" method="post">
    <input type="text" name="email">
    <input type="text" name="message">
</form>
</body>
</html>
