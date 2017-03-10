$(document).ready(function () {

    getProjectsListMenu();
    getSprintsListMenu();

});

});

function getProjectsListMenu() {
    $.getJSON('/api/project',
        function (data) {
            projectsListMenu = document.getElementById("projectsListMenu");
            var html = '';

            if (data.length != 0) {

                $.each(data, function (key, val) {
                    html += '<li><a href="project/' + val.id + '">' + val.nom + '</a></li>';
                });

            }
            else {
                html = '<li class="text-center"> No data </li>';
            }
            projectsListMenu.innerHTML = html;

        });

};

function getSprintsListMenu() {
    $.getJSON('/api/sprint',
        function (data) {
            sprintsListMenu = document.getElementById("sprintsListMenu");
            var html = '';

            if (data.length != 0) {

                $.each(data, function (key, val) {
                    html += '<li><a href="sprint/' + val.id + '">' + val.nom + '</a></li>';
                });

            }
            else {
                html = '<li class="text-center"> No data </li>';
            }
            sprintsListMenu.innerHTML = html;

        });

};

