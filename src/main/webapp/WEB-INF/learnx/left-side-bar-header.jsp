<div class="container">
    <div class="row mb-4">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <h1 class="card-title">${activeUser.firstName}&nbsp;${activeUser.lastName}</h1>
                    <ul class="list-inline">
                        <li class="list-inline-item"><i class="fas fa-star"></i>Member since ${activeUser.created_at}</li>
                    </ul>
                </div>
            </div><%--   Close card--%>
        </div><%-- Close col --%>
        <!-- Advanced filter responsive toggler START -->
        <!-- Divider -->
        <hr class="d-xl-none">
        <div class="col-12 col-xl-3 d-flex justify-content-end align-items-center">
            <button class="btn btn-primary d-xl-none" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasSidebar" aria-controls="offcanvasSidebar">
                <i class="fas fa-bars"></i>
            </button>
        </div>
        <!-- Advanced filter responsive toggler END -->
    </div>
</div>