$(document).ready(function () {

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
                    getProjectsListMenu();
                    toastr.success(data.nom+" added");
                },
                error: function (resultat, statut, erreur) {
                    toastr.error("An error occurred. This project may already exists. (" + statut + " - " + erreur + ")");
                }
            });
        }
        else{
            //$('#divMessage').html("Project name is required.");
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
                            "<div class='row'>" +
                            "<div class='col-sm-3'></div>" +
                            "<div class='col-sm-6 list-group'>" +
                            "<a href=project/" + val.id + " data-ajax='false' class='list-group-item'>" +
                            "<div class='dsc'>" + val.nom + "</div>" +
                            "</a>" +
                            "</div>" +
                            "<div class='col-sm-3'></div>" +
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



