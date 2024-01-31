<%@include file="/WEB-INF/shared/top.jsp"%>
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
<main>
    <div class="container py-4">
        <div class="row">
            <div class="col-12 text-center">
                <h2>Oh no!</h2>
                <p>Either something went wrong or this page doesn't exist.</p>
                <a href="${appURL}" class="btn btn-primary">Back to home page</a>
            </div>
        </div>
    </div>
</main>
 <%@include file="/WEB-INF/shared/bottom.jsp"%>

