<%@page import="Utilities.RandomString"%>
<%@page import="Model.User"%>
<%@page import="DAO.DBDAOImplUser"%>
<%@page import="Model.EligibleNominee"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Model.Candidate"%>
<%@page import="Model.Nominee"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Voter"%>
<%@page import="Model.Election"%>
<jsp:include page="headerSidebar.jsp"/>
<!-- BEGIN PAGE LEVEL STYLE -->
<link rel="stylesheet" href="../assets/plugins/magnific/magnific-popup.css">    
<link rel="stylesheet" type="text/css" href="../assets/dtp/jquery.datetimepicker.css"/>
<!-- END PAGE LEVEL STYLE -->
<%
    String DOMAIN_BASE = request.getRequestURL().substring(0, request.getRequestURL().indexOf("Electio") + 8);
    Election el = (Election) request.getAttribute("election");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<div id="main-content">
    <div class="page-title">
        <h3><strong>
                <%= el.getName()%>
            </strong></h3>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading align-center">
                    <div class="btn-group">
                        <a href="#" class="btn btn-primary"><i class="fa fa-info-circle"></i> General</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=nominees" class="btn btn-info"><i class="fa fa-user"></i> Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=candidates" class="btn btn-info"><i class="fa fa-user"></i> Candidates</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=voters" class="btn btn-info"><i class="fa fa-group"></i> Voters</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=probable_nominees" class="btn btn-info"><i class="fa fa-list"></i> Probable Nominees</a>
                        <a href="Controller?action=view_election_detail&id=<%= el.getId() %>&tab=statistics" class="btn btn-info"><i class="fa fa-bar-chart-o"></i> Election Statistics</a>               
                    </div>
                </div>
                
                <div class="panel-body">
                    <form action="Controller" method="POST" class="form-horizontal">
                        <fieldset>
                            <div class="col-lg-12">
                                <input type="hidden" name="id" value="<%= el.getId()%>">
                                <div class="form-group">
                                    <label for="name" class="control-label col-lg-3"><strong>Election Name</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="name" value="<%=el.getName()%>">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="description" class="control-label col-lg-3"><strong>Description</strong></label>
                                    <div class="col-lg-7">
                                        <textarea class="form-control" name="description"><%= el.getDescription()%></textarea>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="requirements" class="control-label col-lg-3"><strong>Eligibility Requirements</strong></label>
                                    <div class="col-lg-7">
                                        <div class="tinymceScreen">
                                            <textarea name="requirements" id="requirements">
                                                <%= el.getRequirements()%>
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="type" class="control-label col-lg-3"><strong>Election Type</strong></label>
                                    <div class="col-lg-7">
                                        <select class="form-control" name="type">
                                            <% if (el.getType_id() == 2) {%>
                                            <option value="2" selected>Weighted</option>
                                            <option value="1">Preferential</option>
                                            <%} else {%>
                                            <option value="2">Weighted</option>
                                            <option value="1" selected>Preferential</option>
                                            <%}%>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="nomination_start" class="control-label col-lg-3"><strong>Nomination Starts on</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="nomination_start" id="nomination_start" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getNomination_start().getTime()))%>"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="nomination_end" class="control-label col-lg-3"><strong>Nomination Ends on</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="nomination_end" id="nomination_end" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getNomination_end().getTime()))%>"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="withdrawal_start" class="control-label col-lg-3"><strong>Withdrawal Starts on</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="withdrawal_start" id="withdrawal_start" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getWithdrawal_start().getTime()))%>"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="withdrawal_end" class="control-label col-lg-3"><strong>Withdrawal Ends on</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="withdrawal_end" id="withdrawal_end" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getWithdrawal_end().getTime()))%>"/>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="voting_start" class="control-label col-lg-3"><strong>Voting Starts on</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="voting_start" id="voting_start" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getVoting_start().getTime()))%>"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label for="voting_end" class="control-label col-lg-3"><strong>Voting Ends on</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="voting_end" id="voting_end" placeholder="Click to choose date" value="<%= sdf.format(new Date(el.getVoting_end().getTime()))%>"/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <label class="control-label col-lg-3"><strong>Petition Period (In Days)</strong></label>
                                    <div class="col-lg-7">
                                        <input type="text" class="form-control" name="petition_duration" value="<%= el.getPetition_duration()%>">
                                    </div>
                                </div>
                                <br>

                                <div class="form-group">
                                    <div class="col-lg-7 col-lg-offset-3">
                                        <button type="submit" name="action" value="update_election" class="btn btn-success"><i class="fa fa-floppy-o"></i> Save</button>
                                        <button type="reset" class="btn btn-danger"><i class="fa fa-eraser"></i> Clear</button>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/plugins/magnific/jquery.magnific-popup.min.js"></script>
<script src="//tinymce.cachefly.net/4.0/tinymce.min.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.js"></script>
<script type="text/javascript" src="../assets/dtp/jquery.datetimepicker.js"></script>
<script type="text/javascript" src="../js/script.js"></script>
<!-- END  PAGE LEVEL SCRIPTS -->


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