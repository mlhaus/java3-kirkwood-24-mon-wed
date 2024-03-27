<%@include file="/WEB-INF/learnx/top.jsp"%>
<main>
    <%@include file="/WEB-INF/learnx/left-side-bar-header.jsp"%>
    <div class="container">
        <div class="row">
            <%@include file="/WEB-INF/learnx/left-side-bar.jsp"%>

            <div class="col-xl-9">
                <div class="card border rounded-3">
                    <div class="card-header border-bottom">
                        <h3 class="card-header-title">${pageTitle}</h3>
                    </div>
                    <div class="card-body">

                        <c:if test="${not empty flashMessageSuccess}">
                            <div class="alert alert-success">
                               ${flashMessageSuccess}
                            </div>
                            <c:remove var="flashMessageSuccess"></c:remove>
                        </c:if>
                        <c:if test="${not empty flashMessageWarning}">
                            <div class="alert alert-warning">
                                    ${flashMessageWarning}
                            </div>
                            <c:remove var="flashMessageWarning"></c:remove>
                        </c:if>


                        <form action="${appURL}/edit-profile" method="POST">
                            <div class="row g-4">
                                <!-- First Name -->
                                <div class="col-md-6">
                                    <label for="firstNameInput" class="form-label">First Name</label>
                                    <input type="text" class="form-control" id="firstNameInput" name="firstNameInput" value="${fn:escapeXml(activeUser.firstName)}">
                                </div>
    
                                <!-- Last name-->
                                <div class="col-md-6">
                                    <label for="lastNameInput" class="form-label">Last Name</label>
                                    <input type="text" class="form-control" id="lastNameInput" name="lastNameInput" value="${fn:escapeXml(activeUser.lastName)}">
                                </div>
    
                                <div class="col-md-6">
                                    <label for="languageInput" class="form-label">Language</label>
                                    <select name="languageInput" id="languageInput" class="form-select <c:if test="${not empty results.languageError}">is-invalid</c:if>">
                                        <option value="en-US" ${activeUser.language eq 'en-US' ? 'selected' : ''}>English</option>
                                        <option value="fr-FR" ${activeUser.language eq 'fr-FR' ? 'selected' : ''}>French</option>
                                        <option value="es-MX" ${activeUser.language eq 'es-MX' ? 'selected' : ''}>Spanish</option>
                                    </select>
                                    <c:if test="${not empty results.languageError}">
                                        <div class="invalid-feedback">
                                            ${results.languageError}
                                        </div>
                                    </c:if>
                                </div>

                                <div class="col-md-6">
                                    <label for="languageInput" class="form-label">Timezone</label>
                                    <select name="languageInput" id="timezoneInput" class="form-select">
                                    </select>
                                </div>
    
                                <!-- Save button -->
                                <div class="d-sm-flex justify-content-end">
                                    <button type="submit" class="btn btn-primary mb-0">Save changes</button>
                                </div>
    
                            </div>
                        </form>
                    </div>
                </div>
            </div>



        </div>
    </div>
</main>
<%@include file="/WEB-INF/learnx/bottom.jsp"%>