
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js sidebar-large lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js sidebar-large lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js sidebar-large lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js sidebar-large"><!--<![endif]-->
    <head>
        <!-- BEGIN META SECTION -->
        <meta charset="utf-8">
        <title>Electi - Error</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" href="assets/img/favicon.png">
        
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/style.min.css" rel="stylesheet">
        <!-- END  MANDATORY STYLE -->
  
    </head>

    <body class="error-page">
        <div class="row">
            <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-offset-1 col-xs-10">
                <div class="error-container">
                    <div class="error-main">
                        <h1> Oops.</h1>
                        <h3> Something went wrong, please try again... </h3>
                        <h4> Go back to our site or <a href="contact.jsp">contact us</a> about the problem. </h4>
                        <br>
                        <button class="btn btn-dark" type="button"  onclick="goBack()">Go Back</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="footer">
            <div class="copyright">© Copyright Electio, 2014</div>
        </div>
        <script>
            function goBack() {
                window.history.back()
            }
        </script>
    </body>
</html>
