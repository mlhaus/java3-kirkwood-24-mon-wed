<%@include file="/WEB-INF/learnx/top.jsp"%>
<main>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-lg-6 col-md-8 col-sm-10 col-xs-12">
            <h2>${pageTitle}</h2>
            <c:choose>
                <c:when test="${not empty results.codeFail}">
                    <p class="alert alert-danger">
                        ${results.codeFail}
                    </p>
                </c:when>
                <c:when test="${not empty results.codeFail}">
                    <p class="alert alert-danger">
                            ${results.codeFail}
                    </p>
                </c:when>
                <c:otherwise>
                    <p>Please enter the code you received via email</p>
                </c:otherwise>
            </c:choose>
            
            <form method="post" action="${appURL}/confirm">

                <div class="mb-4">
                    <label for="inputCode" class="form-label">Code *</label>
                    <div class="input-group input-group-lg">
                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3"><i class="fas fa-lock"></i></span>
                        <input type="text" class="form-control border-0 bg-light rounded-end ps-1" placeholder="XXXXXX" id="inputCode" name="inputCode"  value="${results.code}">
                    </div>
                </div>
                <!-- Button -->
                <div class="align-items-center mt-0">
                    <div class="d-grid">
                        <button class="btn btn-orange mb-0" type="submit">Confirm</button>
                    </div>
                </div>
            </form>
            <div class="text-center">
                <p>Didn't receive the code? <a href="${appURL}/confirm?resend">Resend</a></p>
            </div>

        </div>
    </div>
</div>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>