<%-- 
    Document   : MassMail
    Created on : Oct 17, 2014, 1:20:14 PM
    Author     : darshit
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Election</title>
    </head>
    <body>
        <form action="MassMail" method="post">
            To: <input type="text" name="to"><br>
            Subject: <input type="text" name="subject"><br>
            Message:<br><textarea name="msg"></textarea><br>
            <input type="submit" value="send" name="send">
        </form>
    </body>
</html>
