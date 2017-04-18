$(document).ready(function () {
    getListProjects();
    $("#createNewProject").click(function () {

        if($("#newprojectname").val()!='') {
            $.ajax({
                url: '/api/project/add',
                type: 'POST',
                headers: {"Accept": "application/json", "Content-Type": "application/json"},
                data: '{"description": "' + $("#newprojectdescription").val() + '", "nom": "' + $("#newprojectname").val() + '"}',
                success: function (data) {
                    $('#formaddnewproject').hide();
                    $('#formaddnewproject').trigger("reset");
                    $('#newProject').show();
                    getListProjects();
                    //getProjectsListMenu();
                    toastr.success(data.nom+" added");
                    $("#newprojectname").val("");
                    $("#newprojectdescription").val("");
                },
                error: function (resultat, statut, erreur) {
                    toastr.error("An error occurred. This project may already exists. (" + statut + " - " + erreur + ")");
                }
            });
        }
        else{
            toastr.error("Project name is required.");
        }
    });

    function getListProjects() {
        $.getJSON('/api/project',
            function (data) {
                listeProjets = document.getElementById("divlistproject");

                if (data.length!=0) {
                    var html = '<p class="h2 text-center" th:text="#{project.list}"></p>';

                    $.each(data, function (key, val) {

                        html +=
                            "<div class='row' id='projectId"+val.id+"'>" +
                            "<div class='col-sm-3'></div>" +
                            "<div class='col-sm-6 list-group'>" +
                            "<a href=project/" + val.id + " data-ajax='false' class='list-group-item'>" +
                            "<div class='dsc'>" + val.nom + "</div>" +
                            "</a>" +
                            "</div>" +
                            "<div class='col-sm-3 list-group'>" +
                            "<div class='col-sm-3'><button type='button' class='btnremove' onclick='editProjectById("+val.id+")'><span class='glyremove glyphicon glyphicon-edit'></span></button>"+
                            "<button type='button' class='btnremove' onclick='deleteProjectById("+val.id+")'><span class='glyremove glyphicon glyphicon-remove-sign'></span></button></div>"+
                            "</div>"+
                            "</div>";
                    });
                    $("#projectNone").hide();
                    listeProjets.innerHTML = html;
                }
                else {
                    $("#projectNone").show();
                }
            });
    };
});


$("#codeActivated").change(function () {
    if ($("#codeActivated").is(':checked')) {
        $("#code").prop('disabled', false);
    }
    else {
        $("#code").prop('disabled', true);
    }
});
function addNewProject() {

    var formaddnewproject = document.getElementById("formaddnewproject");
    var newProject = document.getElementById("newProject");

    formaddnewproject.style.display = "block";
    newProject.style.display = "none";
}

function hideNewProject(){

    var newprojectname = document.getElementById("newprojectname");
    var newprojectdescription = document.getElementById("newprojectdescription");
    var formaddnewproject = document.getElementById("formaddnewproject");
    var newProject = document.getElementById("newProject");

    newprojectname.value = "";
    newprojectdescription.value = "";
    newProject.style.display = "inline-block";
    formaddnewproject.style.display = "none";
}


//Efface un projet d'id idProject
function deleteProjectById(idProject)
{
    bootbox.confirm({
        message: "Would you really want to delete project #"+idProject+"?",
        buttons: {
            confirm: {
                label: 'Yes',
                className: 'btn-success'
            },
            cancel: {
                label: 'No',
                className: 'btn-danger'
            }
        },
        callback: function (result) {
            if(result)
            {
                $.ajax({
                    url: '/api/project/delete/'+idProject,
                    type: 'DELETE',
                    headers: {"Accept": "application/json", "Content-Type": "application/json"},
                    success: function (data) {
                        toastr.success("Project #"+idProject+" has been deleted");
                        $("#projectId"+idProject).hide(500);
                    },
                    error: function (resultat, statut, erreur) {
                        toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
                    }

                });
            }
        }
    });

}

function editProjectById(idProject) {
    addNewProject();
    $("#modifierNewProject").show();
    $("#modifierNewProject").attr("onclick", "enregistrerModificationProject("+idProject+")");
    $("#createNewProject").hide();
    $.getJSON('/api/project/'+idProject,
        function (data) {
            $("#newprojectname").val(data.nom);
            $("#newprojectdescription").val(data.description);
        });
}

function enregistrerModificationProject(idProject) {
    var project = {};
    project.nom = $("#newprojectname").val();
    project.description = $("#newprojectdescription").val();

    if(project.nom != '') {
        $.ajax({
            url: '/api/project/update/'+idProject,
            type: 'POST',
            headers: {"Accept": "application/json", "Content-Type": "application/json"},
            data:  JSON.stringify(project),
            success: function (data) {
                toastr.success(data.nom + " modifié");
                document.location.reload();
            },
            error: function (resultat, statut, erreur) {
                toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ")");
            }

        });
    }else {toastr.error("Project name is required.");}
}



