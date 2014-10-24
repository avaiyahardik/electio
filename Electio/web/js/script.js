$(document).ready(function() {
    
    var editing = 0;
    var oldEmail;
    var election_id;
    $(':button.btn-del').click(function() {
        if (confirm("Do you really want to delete the voter")) {
            var toRemove = $(this).parent().parent();
            var dataArray = $(this).val().split("-");
            var election_id = dataArray[0];
            var voter_email = dataArray[1];

            $.post("Controller?action=edit_voter",
                    {
                        cmd: "delete",
                        election_id: election_id,
                        voter_email: voter_email
                    },
            function(data, status) {
                if (data == "Deleted") {
                    toRemove.addClass("danger");
                    toRemove.fadeOut(500);
                } else {
                    alert("Could not remove voter, please try again..")
                }

            });
        }
    });

    $(':button.btn-edit').click(function() {
        //alert('Edit button pressed');
        if(editing == 0){
            var toEdit = $(this).parent().parent();
            var dataArray = $(this).val().split("-");
            election_id = dataArray[0];
            oldEmail = dataArray[1];
            
            var insertAt = toEdit.next();
            // Hide the editing row
            toEdit.fadeOut("fast");
            
           var htmlString = "<tr><td><input type='text' id='new_email' value='"+oldEmail +"'></td><td>false</td><td><button class='btn-save btn btn-success btn-sm'><i class='fa fa-floppy-o'></i> Save</button><button class='btn-cancel btn btn-warning btn-sm'><i class='fa fa-mail-reply'></i> Cancel</button></td></tr>";
           //htmlString += $('#tbl_voters').html();
           //$('#tbl_voters').html(htmlString + $('#tbl_voters').html());
           $(htmlString).insertBefore(insertAt);
           
            editing = 1;
        }

    });
    
    $(':button.btn-save').live("click",function(){
        var newEmail = $('#new_email').live().val();
        //alert(oldEmail+" "+newEmail);
        
        if(oldEmail == newEmail){
            
        }
        else{
            
            
        }
        
    });
});