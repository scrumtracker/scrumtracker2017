$(document).ready(function () {

    getProjectsListMenu();
    getStoriesListMenu();

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

function getStoriesListMenu() {
    $.getJSON('/api/story',
        function (data) {
            storiesListMenu = document.getElementById("storiesListMenu");
            var html = '';

            if (data.length != 0) {

                $.each(data, function (key, val) {
                    html += '<li><a href="story/' + val.id + '">' + val.nom + '</a></li>';
                });

            }
            else {
                html = '<li class="text-center"> No data </li>';
            }
            storiesListMenu.innerHTML = html;

        });

};

