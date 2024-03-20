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
                        <form action="${appURL}/edit-profile" method="POST">
                            <div class="row g-4">
                                <!-- First Name -->
                                <div class="col-md-6">
                                    <label for="firstNameInput" class="form-label">First Name</label>
                                    <input type="text" class="form-control" id="firstNameInput" name="firstNameInput" value="${activeUser.firstName}">
                                </div>
    
                                <!-- Last name-->
                                <div class="col-md-6">
                                    <label for="lastNameInput" class="form-label">Last Name</label>
                                    <input type="text" class="form-control" id="lastNameInput" name="lastNameInput" value="${activeUser.lastName}">
                                </div>
    
                                <!-- Email id -->
                                <div class="col-md-6">
                                    <label class="form-label" for="emailInput">Email</label>
                                    <input class="form-control" type="text" id="emailInput" name="emailInput" value="${activeUser.email}">
                                </div>
    
                                <!-- Phone number -->
                                <div class="col-md-6">
                                    <label class="form-label" for="phoneInput">Phone number</label>
                                    <input type="text" class="form-control" id="phoneInput" name="phoneInput" value="${activeUser.phone}">
                                </div>
    
                                <div class="col-md-6">
                                    <label for="languageInput" class="form-label">Language</label>
                                    <select name="languageInput" id="languageInput" class="form-select">
                                        <option value="en-US" ${activeUser.language eq 'en-US' ? 'selected' : ''}>English</option>
                                        <option value="fr-FR" ${activeUser.language eq 'fr-FR' ? 'selected' : ''}>French</option>
                                        <option value="es-MX" ${activeUser.language eq 'es-MX' ? 'selected' : ''}>Spanish</option>
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