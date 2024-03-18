<%@include file="/WEB-INF/learnx/top.jsp"%>
<main class="container">
    <section class="p-0 d-flex align-items-center position-relative overflow-hidden">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8 m-auto">
                    <div class="row my-5">
                        <div class="col-sm-10 col-xl-8 m-auto">
                            <h2>Nice to see you!</h2>
                            
                            <c:choose>
                                <c:when test="${not empty results.loginError}">
                                    <p class="alert alert-danger">
                                            ${results.loginError}
                                    </p>
                                </c:when>
                                <c:when test="${not empty flashMessageWarning}">
                                    <p class="alert alert-warning">
                                            ${flashMessageWarning}
                                    </p>
                                    <c:remove var="flashMessageWarning" scope="session"></c:remove>
                                </c:when>
                                <c:otherwise>
                                    <p class="lead mb-4">Please sign in to your account.</p>
                                </c:otherwise>
                            </c:choose>

                            <!-- Form START -->
                            <form action="${appURL}/signin" method="post">
                                <!-- Email -->
                                <div class="mb-4">
                                    <label for="inputEmail1" class="form-label">Email address *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3"><i class="bi bi-envelope-fill"></i></span>
                                        <input type="text" class="form-control border-0 bg-light rounded-end ps-1" placeholder="E-mail" id="inputEmail1" name="inputEmail1"  value="${results.email}">
                                    </div>
                                </div>
                                <!-- Password -->
                                <div class="mb-4">
                                    <label for="inputPassword1" class="form-label">Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="form-control border-0 bg-light rounded-end ps-1" placeholder="*********" id="inputPassword1" name="inputPassword1"  value="${results.password1}">
                                    </div>
                                </div>
                                
                                <!-- Check box -->
                                <div class="mb-4">
                                    <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="checkbox-1" name="checkbox-1" value="yes" <c:if test="${results.remember eq 'yes'}">checked</c:if>>
                                        <label class="form-check-label" for="checkbox-1">Remember me for 7 days</label>
                                    </div>
                                </div>
                                <!-- Button -->
                                <div class="align-items-center mt-0">
                                    <div class="d-grid">
                                        <button class="btn btn-orange mb-0" type="submit">Sign In</button>
                                    </div>
                                </div>

                                <input type="hidden" name="redirect" value="${redirect}">
                                
                            </form>
                            <!-- Form END -->

                            <!-- Sign in link -->
                            <div class="mt-4 text-center">
                                <span>Forgot your password? <a href="${appURL}/reset-password">Reset it here</a></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>