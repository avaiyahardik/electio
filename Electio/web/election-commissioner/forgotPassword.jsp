<%-- 
    Document   : password
    Created on : Oct 20, 2014, 5:42:15 PM
    Author     : Vishal Jain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>

        <!-- 
            Display msg and err from request object as we shown in every jsp pages.
            On successfully sending new password to election commission msg will be set to "Password sent successfully to <email id>"
            If system fails to send password to given email err will be set to "Fail to send email at <email>"
        
            Create form with following elements and give action="Controller" method="POST"
            Create text box named "email" to take email of election commissioner.
            Create submit button named "action" as "Send Password" give value="reset_password" -->
    </body>
</html>
