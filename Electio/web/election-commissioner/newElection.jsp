<jsp:include page="headerSidebar.jsp"/>

<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>

<link rel="stylesheet" type="text/css" href="../assets/dtp/jquery.datetimepicker.css"/>

<div class="col-lg-12">
    <div class="panel panel-default">
        <div class="panel-heading bg-blue">
            <h3 class="panel-title"><strong>Create New Election</strong></h3>
        </div>

        <div class="panel-body">
            <form action="Controller" method="POST" class="form-horizontal">
                <fieldset>
                    <div class="col-lg-10">
                        <div class="form-group">
                            <label for="name" class="control-label col-lg-3"><strong>Election Name</strong></label>
                            <div class="col-lg-7">
                                <input type="text" class="form-control" name="name" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="description" class="control-label col-lg-3"><strong>Description</strong></label>
                            <div class="col-lg-7">
                                <textarea class="form-control" name="description" required></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="requirements" class="control-label col-lg-3"><strong>Eligibility Requirements</strong></label>
                            <div class="col-lg-7">
                                <div class="tinymceScreen">
                                    <textarea name="requirements" id="requirements">
                                    </textarea>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="type" class="control-label col-lg-3"><strong>Election Type</strong></label>
                            <div class="col-lg-7">
                                <select class="form-control" name="type">
                                    <option value="0">--select--</option>
                                    <option value="2">Weighted</option>
                                    <option value="1">Preferential</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="nomination_start" class="control-label col-lg-3"><strong>Nomination Starts on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" required="required" class="form-control" name="nomination_start" id="nomination_start" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="nomination_end" class="control-label col-lg-3"><strong>Nomination Ends on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" required="required" class="form-control" name="nomination_end" id="nomination_end" placeholder="Click to choose date"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="withdrawal_start" class="control-label col-lg-3"><strong>Withdrawal Starts on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" required="required" class="form-control" name="withdrawal_start" id="withdrawal_start" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="withdrawal_end" class="control-label col-lg-3"><strong>Withdrawal Ends on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" required="required" class="form-control" name="withdrawal_end" id="withdrawal_end" placeholder="Click to choose date"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="voting_start" class="control-label col-lg-3"><strong>Voting Starts on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" required="required" class="form-control" name="voting_start" id="voting_start" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="voting_end" class="control-label col-lg-3"><strong>Voting Ends on</strong></label>
                            <div class="col-lg-7">
                                <input type="text" required="required" class="form-control" name="voting_end" id="voting_end" placeholder="Click to choose date"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-lg-3"><strong>Petition Period (In Days)</strong></label>
                            <div class="col-lg-7">
                                <input type="text"  required="required" class="form-control" name="petition_duration">
                            </div>
                        </div>
                        <br>

                        <div class="form-group">
                            <div class="col-lg-7 col-lg-offset-3">
                                <button type="submit" name="action" value="create_new_election" class="btn btn-success"><i class="fa fa-pencil"></i> Create</button>
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
    $('#nomination_start').datetimepicker()
            .datetimepicker({step: 30});

    $('#nomination_end').datetimepicker()
            .datetimepicker({step: 30});

    $('#withdrawal_start').datetimepicker()
            .datetimepicker({step: 30});
    $('#withdrawal_end').datetimepicker()
            .datetimepicker({step: 30});

    $('#voting_start').datetimepicker()
            .datetimepicker({step: 30});

    $('#voting_end').datetimepicker()
            .datetimepicker({step: 30});



    tinymce.init({selector: '#requirements'});
</script>

<jsp:include page="footer.jsp"/>