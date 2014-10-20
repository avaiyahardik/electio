<jsp:include page="headerSidebar.jsp"/>
<link href="../assets/plugins/modal/css/component.css" rel="stylesheet">

<script type="text/JavaScript">
function deleteElection(){
    alert("?");
}    
</script>


<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading bg-blue">
            <h3 class="panel-title"><strong>Election List</strong></h3>
        </div>

        <div class="panel-body">

            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12 table-responsive table-red">
                    <table class="table table-hover table-dynamic table-tools">
                        <thead>
                            <tr>
                                <th>Election Name</th>
                                <th>Created on</th>
                                <th>Election Type</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>

                            <!--
                            Show the data using loop here
                            Example set is given here
                            -->
                            <tr>
                                <td><strong><a href="#">SPC Elections 2014</a></strong></td>
                                <td>2014/02/05 10:25</td>
                                <td>Preferential</td>
                                <td>
                                    <a href="#" class="btn btn-success">Result</a>
                                    <a href="#" class="btn btn-default">Download Data</a>
                                    <a href="javascript:deleteElection()" class="btn btn-effect btn-danger">Delete</a>
                                </td>
                            </tr>

                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="../assets/plugins/bootstrap-datepicker/bootstrap-datepicker.js"></script>
<script src="../assets/plugins/modal/js/classie.js"></script>
<script src="../assets/plugins/modal/js/modalEffects.js"></script>

<jsp:include page="footer.jsp"/>