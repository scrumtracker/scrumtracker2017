$(document).ready(function () {

    getListStoriesWithoutSprint();

    $("#createNewStory").click(function () {
        var url = '/api/story/add';
        var story = {};
        story.nom = $("#newstorynameUnaffected").val();
        story.description = $("#newstorydescriptionUnaffected").val();
        story.points = $("#newstorypointsUnaffected").val();
        var storySprint = $("#newstorysprintUnaffected").val();
        if(storySprint != '') {
            url = '/api/story/add/sprint/'+storySprint;
        }else{
            url = '/api/story/add';
        }
        if(story.nom!='') {
            $.ajax({
                url: url,
                type: 'POST',
                headers: {"Accept": "application/json", "Content-Type": "application/json"},
                data: JSON.stringify(story),
                success: function (data) {
                    $('#divaddnewstoryUnaffected').hide();
                    $('#divaddnewstoryUnaffected').trigger("reset");
                    $('button#newStoryUnaffected').show();
                    toastr.success(data.nom + " added");
                    getListStoriesWithoutSprint();
                    document.location.reload();
                },
                error: function (resultat, statut, erreur) {
                    toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ")");
                }

            });
        }
        else{toastr.error("Story name is required.");}

    });


    //fin document . ready
});

function showStory(id){
    $.getJSON('/api/story/'+id,
        function (data) {
            var detailStory = document.getElementById("detailsStory");

            if (data.length!=0) {
                var html =  '<p>Story name : '+data.nom+'</p>'+
                    '<p>Creation date : ' + moment(data.dateCreation).format('DD/MM/YYYY HH:mm') + '<br/>(' + moment(data.dateCreation).fromNow() +')</p>'+
                    '<p>State : '+data.status+'</p>'+
                    '<p>Total hours of works : '+ data.points + '</p>'+
                    '<p>Number of tasks : 0</p>'+
                    '<p>Description : '+data.description+'</p>';
                detailStory.innerHTML = html;
            }
        });
}

function showFormToAddNewSprint(){

    var divaddnewsprint = document.getElementById("divaddnewsprint");
    var newSprint = document.getElementById("newSprint");

    var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

    divaddnewsprint.style.display = "block";
    newSprint.style.display = "none";

    divaddnewstoryUnaffected.style.display = "none";
    newStoryUnaffected.style.display = "block";
}

function updateDateFin(value) {
    document.getElementById("newSprintDateFin").setAttribute("min", value);
}


function addNewStoryUnaffected(){

    var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

    var divaddnewsprint = document.getElementById("divaddnewsprint");
    var newSprint = document.getElementById("newSprint");

    divaddnewstoryUnaffected.style.display = "block";
    newStoryUnaffected.style.display = "none";

    divaddnewsprint.style.display = "none";
    newSprint.style.display = "block";
}

function detailSprint(id, obj){

    var sousMenu = obj.childNodes[5];
    //alert(sousMenu);
    sousMenu.style.display = "block";

    $.getJSON('/api/sprint/' + id,
        function (data) {
            detailofSprint = document.getElementById("detailsSprint");
            if (data.length != 0) {
                var html = '<p>Sprint name : ' + data.nom + '</p>' +
                    '<p>Starting date : ' + moment(data.dateDebut).format('DD/MM/YYYY HH:mm') + '<br/>(' + moment(data.dateDebut).fromNow() + ')</p>' +
                    '<p>Ending date : ' + moment(data.dateFin).format('DD/MM/YYYY HH:mm') + '<br/>(' + moment(data.dateFin).fromNow() + ')</p>';
                detailofSprint.innerHTML = html;
            }
        });
}

//Affiche les stories non affectées à un sprint
function getListStoriesWithoutSprint() {
    $.getJSON('/api/story/sprint/null',
        function (data) {
            listeStories = document.getElementById("divliststory");
            var html = '';

            if (data.length!=0) {
                html = '<li><p class="h2 text-left">List of stories</p></li>';

                $.each(data, function (key, val) {
                    html +=
                        '<li>'+
                        '<div class="padd mouseLink divStoryUnaffected" onclick="showStory('+val.id+')">'
                        + val.nom +
                        '<div class="deleteStorylist">'+
                        '<button type="button" class="btnremove">'+
                        '<span class="glyremovelist glyphicon glyphicon-remove-sign"></span>'+
                        '</button>'+
                        '</div>'+
                        '</div>'+
                        '</li>';
                });
                listeStories.innerHTML = html;
            }
            else {
                html='<li><p class="h2 text-left" id="storyNone">No story (for now)</p></li>';
                listeStories.innerHTML = html;
            }
        });
};

//Efface une story selon son id
function effacerStoryById( idStory )
{
    $.ajax({

        url: '/api/story/delete/'+idStory,
        type: 'DELETE',
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        success: function (data) {
            $("#storyId"+idStory).hide(500);
            toastr.success("Story "+idStory+" deleted.");
        },
        error: function (resultat, statut, erreur) {
            toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
        }

    });
}

function addNewStory(obj){

    var divaddnewstory = obj.nextSibling.nextSibling;
    var newStory = obj;

    var nb_sprint = document.getElementById("divlistsprint").getElementsByClassName("sprint").length;
    //alert(nb_sprint);

    for(var i = 1; i <= nb_sprint; i++){
        if(document.getElementById('divaddnewstory' + i) && document.getElementById('divaddnewstory' + i) != divaddnewstory){
            document.getElementById('divaddnewstory' + i).style.display = "none";
            document.getElementById('divaddnewstory' + i).previousSibling.previousSibling.style.display = "block";
        }
    }

    if(divaddnewstory){
        if(divaddnewstory.style.display == "block"){
            divaddnewstory.style.display = "none";
            newStory.style.display = "block";
        }
        else{
            divaddnewstory.style.display = "block";
            newStory.style.display = "none";
        }
    }


    var divaddnewsprint = document.getElementById("divaddnewsprint");
    var newSprint = document.getElementById("newSprint");
    var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

    if(newStoryUnaffected.style.display == "none"){
        divaddnewstoryUnaffected.style.display = "none";
        newStoryUnaffected.style.display = "block";
    }
    if(newSprint.style.display == "none"){
        divaddnewsprint.style.display = "none";
        newSprint.style.display = "block";
    }
}


//Créé une story reliée à un sprint
function creerStoryDansSprint( idSprint )
{
    var story = {};
    story.nom = $("#newstoryname"+idSprint).val();
    story.description = $("#newstorydescription"+idSprint).val();
    story.status = $("#newstorystatus"+idSprint).val();
    $.ajax({
        url: '/api/story/add/sprint/'+idSprint,
        type: 'POST',
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        data: JSON.stringify(story),
        success: function (data) {
            toastr.success(data.nom + " has been successfully added.");
            document.location.reload();
        },
        error: function (resultat, statut, erreur) {
            toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
        }

    });
}

//Créé un sprint relié à un project
function creerSprintDansProject( idProject )
{
    var sprint = {};
    sprint.nom = $("#newsprintname").val();
    sprint.dateDebut = moment($("#newSprintDateDebut").val()+","+$("#newSprintHeureDebut").val(),'YYYY-MM-DD,HH:mm').format('x');
    sprint.dateFin = moment($("#newSprintDateFin").val()+","+$("#newSprintHeureFin").val(),'YYYY-MM-DD,HH:mm').format('x');
    $.ajax({
        url: '/api/sprint/add/project/'+idProject,
        type: 'POST',
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        data: JSON.stringify(sprint),
        success: function (data) {
            toastr.success(data.nom + " has been successfully added.");
            document.location.reload();
        },
        error: function (resultat, statut, erreur) {
            toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
        }

    });
}

////En construction////
window.onload = function() {

    var containers = $('.listStoryInSprint').toArray();
    containers.concat($('#divliststory').toArray());

    dragula([document.getElementById('divliststory'),containers], {
        isContainer: function (el) {
            return el.classList.contains('listStoryInSprint');
        }
    });
}
