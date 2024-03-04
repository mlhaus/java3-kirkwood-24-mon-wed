<%@include file="/WEB-INF/learnx/top.jsp"%>
<main class="container">
    <section class="p-0 d-flex align-items-center position-relative overflow-hidden">
        <div class="container-fluid">
            <div class="row">
                <div class="col-12 col-lg-8 m-auto">
                    <div class="row my-5">
                        <div class="col-sm-10 col-xl-8 m-auto">
                            <h2>${pageTitle}</h2>
                            <p class="lead mb-4">Please create a new password.</p>

                            <!-- Form START -->
                            <form action="${appURL}/new-password" method="post">
                                <!-- Password -->
                                <div class="mb-4">
                                    <label for="inputPassword1" class="form-label">Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="form-control border-0 bg-light rounded-end ps-1 <c:if test="${not empty results.password1Error}">is-invalid</c:if>" placeholder="*********" id="inputPassword1" name="inputPassword1"  value="${results.password1}">
                                        <c:if test="${not empty results.password1Error}">
                                            <div class="invalid-feedback">
                                                    ${results.password1Error}
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- Confirm Password -->
                                <div class="mb-4">
                                    <label for="inputPassword2" class="form-label">Confirm Password *</label>
                                    <div class="input-group input-group-lg">
                                        <span class="input-group-text bg-light rounded-start border-0 text-secondary px-3"><i class="fas fa-lock"></i></span>
                                        <input type="password" class="form-control border-0 bg-light rounded-end ps-1 <c:if test="${not empty results.password2Error}">is-invalid</c:if>" placeholder="*********" id="inputPassword2" name="inputPassword2"  value="${results.password2}">
                                        <c:if test="${not empty results.password2Error}">
                                            <div class="invalid-feedback">
                                                    ${results.password2Error}
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                                <!-- Button -->
                                <div class="align-items-center mt-0">
                                    <div class="d-grid">
                                        <button class="btn btn-orange mb-0" type="submit">Update Password</button>
                                    </div>
                                </div>
                            </form>
                            <!-- Form END -->

                            <!-- Sign in link -->
                            <div class="mt-4 text-center">
                                <span>Already have an account? <a href="${appURL}/login">Log in here</a></span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>