<jsp:include page="header.jsp"/>

<div class="page-header">
    <h1 class="page-title">Election Results</h1>
</div>
<div class="row well">
    <div class="col-lg-10 col-lg-offset-1">
        
        <div class="panel panel-info">
            <div class="panel-heading">File a Petition</div>
            <div class="panel-body">
                <form action="Controller" method="POST" class="form-horizontal">
                    <fieldset>
                        <input type="hidden" name="type" value="save"/>
                        <div class="form-group">
                            <label class="control-label col-lg-8">Please give description about the Petition</label>
                        </div>
                        <div class="form-group">
                            <div class=" col-lg-12">
                                <textarea class="form-control" required></textarea>
                            </div>
                        </div>

                        <div class="form-group"><div class=" col-lg-12">
                            <button class="btn btn-primary" type="submit" name="action" value="file_petition"><i class="fa fa-envelope"></i> File Petition now!</button>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="footer.jsp"/>