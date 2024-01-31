<%@include file="/WEB-INF/shared/top.jsp"%>
    <title>Artist Json</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css" rel="stylesheet"/>
    <link rel="stylesheet" href="${appURL}/css/loading.css">
</head>
<body>
<h2>${artist.name}</h2>
<img src="${artist.picture}">
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.js"></script>
<script src="${appURL}/js/loading.js"></script>
<%@include file="/WEB-INF/shared/bottom.jsp"%>
