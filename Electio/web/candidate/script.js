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
            var election_id = $("input[name='election_id']").val();
            $.post("Controller?action=check_nominee",{
                email:email,
                election_id:election_id
            },function(data, status){
               alert(data.status);
            });
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