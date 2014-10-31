$(document).ready(function () {

    var editing = 0;
    var old_email, old_html, election_id;
    var input_id, cnt = 0;

    // Deleting a voter
    $(':button.btn-del').live("click", function () {
        if (confirm("Do you really want to delete the voter")) {
            var to_remove = $(this).parent().parent();
            var data_array = $(this).val().split("-");
            var election_id = data_array[0];
            var voter_email = data_array[1];

            $.post("UpdateVoter",
                    {
                        cmd: "delete",
                        election_id: election_id,
                        voter_email: voter_email
                    },
            function (data, status) {
                if (data == "Deleted") {
                    to_remove.addClass("danger");
                    to_remove.fadeOut(500);
                } else {
                    alert("Could not remove voter, please try again..")
                }

            });
        }
    });

// Editing a voter's data (Email only)
    $(':button.btn-edit').live("click", function () {
        to_edit = $(this).parent().parent();
        //alert('Edit button pressed');
        if (editing == 0) {
            editing = 1;
            var to_edit = $(this).parent().parent();
            var data_array = $(this).val().split("-");
            old_html = to_edit.html();
            election_id = data_array[0];
            old_email = data_array[1];
            input_id = "email-" + cnt++;

            var html_string = "<td><input type='text' id='" + input_id + "' value='" + old_email + "'></td><td></td><td><button class='btn-save btn btn-success btn-sm'><i class='fa fa-floppy-o'></i> Save</button><button class='btn-cancel btn btn-warning btn-sm'><i class='fa fa-mail-reply'></i> Cancel</button></td>";
            to_edit.html(html_string);
        } else {
            alert("You can edit a single instance at a time..");
        }
    });

    // Saving edited voter's data
    $(':button.btn-save').live("click", function () {
        var to_remove = $(this).parent().parent();
        input_id = "#" + input_id;
        var new_email = $(input_id).live().val();
        //alert(old_email + " " + new_email);

        if (old_email == new_email) {
            to_remove.html(old_html);
        }
        else {
            if (validateEmail(new_email)) {
                $.post("UpdateVoter", {
                    cmd: "update",
                    old_email: old_email,
                    new_email: new_email,
                    election_id: election_id
                }, function (data, status) {
                    if (data == "Updated") {
                        var new_html = old_html.split(old_email).join(new_email);
                        to_remove.html(new_html);
                    } else {
                        alert("Error Updating voter's data, try again.");
                    }
                });
            } else {
                alert("Invalid email address, try again.");
            }

        }
        editing = 0;

    });

// Cancel editing
    $(':button.btn-cancel').live("click", function () {
        var to_remove = $(this).parent().parent();
        to_remove.html(old_html);
        editing = 0;
    });

    //Adding new voter
    $(':button.btn-add').click(function () {
        var email_id = prompt("Enter email for new voter");

        if (email_id) {
            if (validateEmail(email_id)) {
                var election_id = $(this).val();

                var new_row = "<tr><td>" + email_id + "</td><td class='align-center'><i class='fa fa-circle' style='color:red'></td><td><button value='" + election_id + "-" + email_id + "' class='btn-edit btn-default btn-sm'><i class='fa fa-edit'></i> Edit</button><button value='" + election_id + "-" + email_id + "' class='btn-del btn btn-sm btn-danger'><i class='glyphicon glyphicon-remove'></i> Delete</button></td></tr>";
                $(new_row).insertAfter('#blank_row');

                $.post("UpdateVoter", {
                    cmd: "add",
                    email: email_id,
                    election_id: election_id
                }, function (data, status) {
                    if (data == "Added") {
                        alert("New Voter added successfully.");
                        $(email_id).insertAfter('#blank_space');
                    } else {
                        alert("Error adding new voter, try again");
                    }
                });
            } else {
                alert("Invalid email address, try again.");
            }
        }else{
            alert("Please enter an email address");
        }

    });

    $('#link-upload').click(function () {
        $(this).hide();
        $('#upload-voters').fadeIn('fast');
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