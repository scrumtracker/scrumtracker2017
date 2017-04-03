$(document).ready(function () {
    //getProjectsListMenu();
    //getSprintsListMenu();
});

function getProjectsListMenu() {
    $.getJSON('/api/project',
        function (data) {
            //On retire tous les projets affichés
            $(".menuProject").remove();

            if (data.length != 0) {
                // si on a des projets On cache le 'no data'
                $("#menuProjectsNoData").hide();
                //On rajoute les nouveaux projets
                $.each(data, function (key, val) {
                    var html = '<li class="menuProject"><a href="../project/' + val.id + '">' + val.nom + '</a></li>';
                    $("#projectsListMenu").append(html);
                });
            }
            else
            {
                //sinon On affiche le 'no data'
                $("#menuProjectsNoData").show();
            }
        });

};

function getSprintsListMenu() {
    $.getJSON('/api/sprint',
        function (data) {
            //On retire tous les SPRINTS affichés
            $(".menuSprint").remove();

            if (data.length != 0) {
                // si on a des sprints On cache le 'no data'
                $("#menuSprintsNoData").hide();
                //On retire les 'vieux' projets
                $(".menuSprint").remove();
                //On rajoute les nouveaux projets
                $.each(data, function (key, val) {
                    var html = '<li class="menuSprint"><a href="../sprint/' + val.id + '">' + val.nom + '</a></li>';
                    $("#sprintsListMenu").append(html);
                });
            }
            else
            {
                //sinon On affiche le 'no data'
                $("#menuSprintsNoData").show();
            }
        });

};