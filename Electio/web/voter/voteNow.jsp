<jsp:include page="header.jsp"/>
<title>Voting now</title>

<div class="col-md-12">
    <div class="page-header">
        <h1 class="page-title">You are voting for <span class="label label-info">Hello</span> </h1>
    </div>

    <form class="form-horizontal">

        <!-- Candidate List -->

        <div class="col-lg-8 col-lg-offset-2">
            <div class="form-group">
                <div class="input-group">
                    <span class="input-group-addon">
                        <input type="radio" name="ballot" value="candidate_email_id">
                    </span>
                    <label class="form-control">Candidate name</label>
                </div>
            </div>
        </div>

        <div class="col-lg-8 col-lg-offset-2">
            <div class="form-group">
                <button type="submit" name="" value="" class="btn btn-success"><i class="fa fa-thumbs-o-up"></i> Caste Your Vote !!</button>
            </div>
        </div>

    </form>
</div>
<jsp:include page="footer.jsp"/>