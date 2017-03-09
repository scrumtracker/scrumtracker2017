$(document).ready(function(){

    $("#createNewProject").click(function(){

        $.ajax({

            url: '/api/project/add',
            type: 'POST',
            contentType: "application/json",
            accept: 'application/json',
            dataType: "json",
            data: '{"nom":"WAOUUUUUU","description":"YOUHOUUUHOUUUHOUUUHOUUU"}',
            //data : '{"description": "' + $("#newprojectdescription").val() + '", "nom": "' + $("#nameProject").val() + '"}',
            success: function (data) {
                $('#divTest').html(data);
            },
            error: function (resultat, statut, erreur) {
                $('#divTest').html(resultat + " /// " + statut + " /// " + erreur);
            }

        });

    });

});


$("#codeActivated").change(function(){
    if($("#codeActivated").is(':checked'))
    {
        $("#code").prop('disabled', false);
    }
    else
    {
        $("#code").prop('disabled', true);
    }
});
function addNewProject(){

    var formaddnewproject = document.getElementById("formaddnewproject");
    var newProject = document.getElementById("newProject");

    formaddnewproject.style.display = "block";
    newProject.style.display = "none";
}
/*
$("#newProject").click(function(){
    bootbox.alert({
        message: "This is the small alert!"
    });

    if($("#formaddnewproject").style.display == "block"){
        $("#formaddnewproject").style.display = "none";
    }else{
        $("#formaddnewproject").style.display = "block";
    }
})*/



