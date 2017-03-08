$(document).ready(function(){

    $("#createNewProject").click(function(){


        //var descriptionProject = document.getElementById("newprojectdescription");
        //var nameProject = document.getElementById("newprojectname");

        $.ajax({
            url : '/api/project/add',
            type : 'POST',
            data : '{"description": "' + $("#newprojectdescription").val() + '", "nom": "' + $("#nameProject").val() + '"}',
            dataType : 'application/json',
            accept : 'application/json'
           /* success : function('<h1>Success</h1>', statut){ // code_html contient le HTML renvoyé},
            error : function(resultat, statut, erreur){ }

            });*/

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



