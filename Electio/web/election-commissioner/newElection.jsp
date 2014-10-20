<%-- 
    Document   : newElection
    Created on : Oct 19, 2014, 4:39:09 PM
    Author     : Vishal Jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="headerSidebar.jsp"/>

<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>

<link rel="stylesheet" type="text/css" href="../assets/dtp/jquery.datetimepicker.css"/>

<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading bg-blue">
            <h3 class="panel-title"><strong>Create New Election</strong></h3>
        </div>

        <div class="panel-body">
            <form action="" method="POST" class="form-horizontal">
                <fieldset>
                    <div class="col-lg-10">
                        <div class="form-group">
                            <label for="electionname" class="control-label col-lg-3"><strong>Election Name</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="electionname">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="electiondescription" class="control-label col-lg-3"><strong>Description</strong></label>
                            <div class="col-lg-7">
                                <textarea class="form-control" name="electiondescription"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="criteria" class="control-label col-lg-3"><strong>Criteria</strong></label>
                            <div class="col-lg-7">
                                <div class="tinymceScreen">
                                    <textarea name="criteria" id="criteria">
                                    </textarea>
                                </div>

                            </div>
                        </div>

                        <div class="form-group">
                            <label for="electiontype" class="control-label col-lg-3"><strong>Election Type</strong></label>
                            <div class="col-lg-7">
                                <select class="form-control" name="electiontype">
                                    <option value="">Weighted</option>
                                    <option value="">Preferential</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="nominationstart" class="control-label col-lg-3"><strong>Nomination Starts on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="nomstart" id="nomstart" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="nominationend" class="control-label col-lg-3"><strong>Nomination Ends on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="nomend" id="nomend" placeholder="Click to choose date"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="withdrawalstart" class="control-label col-lg-3"><strong>Withdrawal Starts on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="withdrawstart" id="withdrawstart" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="withdrawalend" class="control-label col-lg-3"><strong>Withdrawal Ends on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="withdrawend" id="withdrawend" placeholder="Click to choose date"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="votingstart" class="control-label col-lg-3"><strong>Voting Starts on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="votestart" id="votestart" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="votingend" class="control-label col-lg-3"><strong>Voting Ends on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="voteend" id="voteend" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-lg-3"><strong>Petition Period (In Days)</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="petitonperiod">
                            </div>
                        </div>
                        <br>

                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-3">
                                <button type="submit" class="btn btn-success"><i class="fa fa-pencil"></i> Create</button>
                                <button type="reset" class="btn btn-danger"><i class="fa fa-eraser"></i> Clear</button>
                            </div>
                        </div>

                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>


<script type="text/javascript" src="../assets/dtp/jquery.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.datetimepicker.js"></script>
<script type="text/javascript">
    $('#nomstart').datetimepicker()
            .datetimepicker({step: 10});

    $('#nomend').datetimepicker()
            .datetimepicker({step: 10});

    $('#withdrawstart').datetimepicker()
            .datetimepicker({step: 10});
    $('#withdrawend').datetimepicker()
            .datetimepicker({step: 10});

    $('#votestart').datetimepicker()
            .datetimepicker({step: 10});

    $('#voteend').datetimepicker()
            .datetimepicker({step: 10});



    tinymce.init({selector: '#criteria'});
</script>

<jsp:include page="footer.jsp"/>