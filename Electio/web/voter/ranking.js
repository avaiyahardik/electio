$(document).ready(function(){
   $('#error').hide(); 
});

function checkRankings(){
    var inputs = document.getElementsByTagName("select");
    for (var i = 0; i<inputs.length; i++){
        if (inputs[i].value == "--"){
            $('#error').show("slow");
            return false;
         }
     }
     $('#error').hide("slow");
     return true;
}

function check()
{
    var a = document.forms["theForm"].getElementsByTagName("select");
    var len = a.length;
    var arr = [];
    var j = 0, i, k, l, flag;
    for (i = 0; i < len; i++) {
        if (a[i].value != "--")
        {
            arr[j++] = a[i].value;
        }
    }

    for (i = 0; i < len; i++) {
        if (a[i].value == "--")
        {
            var options = a[i].getElementsByTagName('option');
            var opLen = options.length;
            for (l = 1; l < opLen; l++) {
                a[i].remove(1);
            }


            for (k = 1; k <= len; k++)
            {
                flag = 0;
                for (l = 0; l < arr.length; l++) {
                    if (arr[l] == k) {
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) {
                    var option = document.createElement("option");
                    option.text = k;
                    option.value = k;
                    a[i].add(option);
                }
            }
        } else {
            var temp = a[i].value;
            var options = a[i].getElementsByTagName('option');
            var opLen = options.length;
            for (l = 1; l < opLen; l++) {
                a[i].remove(1);
            }


            for (k = 1; k <= len; k++)
            {
                flag = 0;
                for (l = 0; l < arr.length; l++) {
                    if (arr[l] == k) {
                        flag = 1;
                        break;
                    }
                }

                if (k == temp) {
                    var option = document.createElement("option");
                    option.text = k;
                    option.value = k;
                    option.selected = "selected";
                    a[i].add(option);
                }
                if (flag == 0) {
                    var option = document.createElement("option");
                    option.text = k;
                    option.value = k;
                    a[i].add(option);
                }
            }
        }
    }
}