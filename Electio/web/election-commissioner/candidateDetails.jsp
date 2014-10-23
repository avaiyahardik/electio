<jsp:include page="headerSidebar.jsp"/>
<div id="main-content">
    <div class="page-title">
        <h2>Candidate Details</h2>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body">
                    <div class="col-md-3">
                        <img src="../assets/img/avatars/avatar4_big.png" alt=""/>
                        <!-- replace the Image source -->
                    </div>

                    <div class="col-md-9">
                        <div class="row">
                            <div class="col-md-12 profile-info">
                                <h1>
                                    Vishal Jain
                                    <!-- Remove the Above name and put the name from DB -->

                                </h1>
                                <div class="m-t-10"></div>
                                <ul class="list-unstyled list-inline">
                                    <li class="m-r-20"><i class="p-r-5 c-dark">@</i> Email ID</li>
                                    <li class="m-r-20"><i class="fa fa-mobile p-r-5 c-purple"></i> Mobile No</li>
                                    <li class="m-r-20"><i class="fa fa-info p-r-5 c-brown"></i> Organization Name</li>
                                    <li class="m-r-20"><i class="fa fa-envelope p-r-5 c-blue"></i> Organization Address</li>
                                    <li><i class="fa fa-tag p-r-5 c-red"></i> About Organization</li>
                                </ul>
                                <div class="m-t-20"></div>
                                <a href="#" class="btn btn-dark"><i class="fa fa-paperclip"></i> Requirements</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="align-center">
                            <a href="Controller?action=approve_nominee&election_id=&email=" class="btn btn-success"><i class="fa fa-check"></i> Approve</a>
                            <a href="Controller?action=reject_nominee&election_id=&email=" class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i> Reject</a>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>