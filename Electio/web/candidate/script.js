/* 
 JavaScrip file for Candidate view
 * */

$(document).ready(function () {
    $('#email').focusout(function () {
        var email = $(this).val();
        if(email == "" || !validateEmail(email)){
            $(this).css("border-color","red").focus();
        }else{
            $(this).css("border-color","#c0c0c0");
            $.get("Controller",{
                action:"check_nominee",
                email:email,
            },function(data, status){
               if(data.status ){
                   $('#nominee-status').html(data.name);
                   $('#pass-link').attr('href', 'RegisterExistingNominee?cmd=password');
                   $('#continue_link').attr('href','RegisterExistingNominee?cmd=register');
                   $('#nominee-modal').modal();
               }
            }, "json");
        }

    });
});

function validateEmail(email)
{
    var reg = /^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/;
    if (reg.test(email)) {
        return true;
    }
    else {
        return false;
    }
}