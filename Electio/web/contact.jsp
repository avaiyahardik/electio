<%@page import="Utilities.RandomString"%>
<jsp:include page="header.jsp"/>
<title>Contact Us</title>
<div class="page-title">
    <h1>Contact Us</h1>
</div>
<hr>


<!-- Content Row -->
<div class="row">
    <!-- Map Column -->
    <div class="col-md-8">
        <!-- Embedded Google Map -->
        <iframe width="100%" height="350" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://maps.google.com/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=DAIICT,+DA+IICT+Road,+Gandhinagar,+Gujarat,+India&amp;aq=1&amp;oq=daiict+&amp;sll=37.0625,-95.677068&amp;sspn=40.59616,86.572266&amp;ie=UTF8&amp;hq=&amp;hnear=&amp;ll=23.188464,72.628422&amp;spn=0.006295,0.006295&amp;t=m&amp;output=embed"></iframe>

    </div>
    <!-- Contact Details Column -->
    <div class="col-md-4">
        <h3>Contact Details</h3>
        <p>
            DA-IICT CAMPUS<br>Dhirubhai Ambani Institute of Information and Communication Technology<br>
        </p>
        <p><i class="fa fa-phone"></i> 
            <abbr title="Phone">P</abbr>: (+91) 81282-44869</p>
        <p><i class="fa fa-envelope-o"></i> 
            <abbr title="Email">E</abbr>: <a href="mailto:<%=RandomString.ELECTIO_GMAIL_EMAIL%>"><%=RandomString.ELECTIO_GMAIL_EMAIL%></a>
        </p>
        <p><i class="fa fa-clock-o"></i> 
            <abbr title="Hours">H</abbr>: Monday - Friday: 9:00 AM to 5:00 PM</p>
        <ul class="list-unstyled list-inline list-social-icons">
            <li>
                <a href="#"><i class="fa fa-facebook-square fa-2x"></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-linkedin-square fa-2x"></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-twitter-square fa-2x"></i></a>
            </li>
            <li>
                <a href="#"><i class="fa fa-google-plus-square fa-2x"></i></a>
            </li>
        </ul>
    </div>
</div>
<!-- /.row -->

<!-- Contact Form -->

<div class="row">
    <div class="col-md-8">
        <h3>Send us a Message</h3>
        <form action="Controller" method="POST" class="form-horizontal">
            <fieldset>
                <div class="form-group">
                    <!-- BEGIN ERROR BOX -->

                    <%
                        String msg = (String) request.getAttribute("msg");
                        if (msg != null) {
                    %>

                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <%=msg%>
                    </div>
                    <% }%>

                    <%
                        String err = (String) request.getAttribute("err");
                        if (err != null) {
                    %>
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert">×</button>
                        <%=err%>

                    </div>
                    <% }%>
                    <!-- END ERROR BOX -->  
                </div>
                <div class="control-group form-group">
                    <div class="controls">
                        <label>Full Name:</label>
                        <div class="input-group">
                            <span class="input-group-addon bg-blue">
                                <span class="arrow"></span>
                                <i class="fa fa-user"></i> 
                            </span>
                            <input type="text" name="first_name" class="form-control" required>
                        </div>
                        <p class="help-block"></p>
                    </div>
                </div>
                <div class="control-group form-group">
                    <div class="controls">
                        <label>Phone Number:</label>
                        <div class="input-group">
                            <span class="input-group-addon bg-blue">
                                <span class="arrow"></span>
                                <i class="fa fa-mobile">+91</i> 
                            </span>
                            <input type="text" class="form-control" name="mobile_no" required>
                        </div>
                    </div>
                </div>
                <div class="control-group form-group">
                    <div class="controls">
                        <label>Email Address:</label>
                        <div class="input-group">
                            <span class="input-group-addon bg-blue">
                                <span class="arrow"></span>
                                <i>@</i> 
                            </span>
                            <input type="text" name="email_id" class="form-control" required>
                        </div>
                    </div>
                </div>
                <div class="control-group form-group">
                    <div class="controls">
                        <label>Message:</label>
                        <textarea rows="8" cols="100" name="message"class="form-control" id="message" required data-validation-required-message="Please enter your message" maxlength="999" style="resize:none"></textarea>
                    </div>
                </div>
                <div id="success"></div>
                <!-- For success/fail messages -->
                <button type="submit" name="action" value="feedback" class="btn btn-success"><i class="fa fa-check"></i> Send Message</button>
            </fieldset>
        </form>
    </div>

</div>

<jsp:include page="footer.jsp"/>