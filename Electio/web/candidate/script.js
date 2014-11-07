/* 
 JavaScrip file for Candidate view
 * */

$(document).ready(function() {
    $('#email').focusout(function() {
        var email = $(this).val();
        var election_id = $('input[name=election_id]').val();
        if (email == "" || !validateEmail(email)) {
            $(this).css("border-color", "red").focus();
        } else {
            $(this).css("border-color", "#c0c0c0");
            $.get("Controller", {
                action: "check_nominee",
                election_id: election_id,
                email: email,
            }, function(data, status) {
                if (data.status) {
                    $('#nominee-status').html(data.name);
                    $('#pass-link').attr('href', 'RegisterExistingNominee?cmd=password&email=' + email + '&election_id=' + election_id);
                    $('#continue_link').attr('href', 'RegisterExistingNominee?cmd=register&email=' + email + '&election_id=' + election_id);
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