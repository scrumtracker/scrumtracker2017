$(document).ready(function () {

    getListStoriesWithoutSprint();

    $("#createNewStory").click(function () {
        var url = '/api/story/add';
        var story = {};
        story.nom = $("#newstorynameUnaffected").val();
        story.description = $("#newstorydescriptionUnaffected").val();
        story.points = $("#newstorypointsUnaffected").val();
        story.status = $("#newstorysprintUnaffectedToDo").val();
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

    //Partie DnD
    dragDropProject();


    //fin document . ready
});

function showStory(id){
    $.getJSON('/api/story/'+id,
        function (data) {
            var detailStory = document.getElementById("detailsStory");
            if(data.points == null) {
                data.points = "";
            }
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

//Cache les détails latéraux (utile après suppression d'un élément)
function hideDetails(){
    var detail = document.getElementById("detailsStory");
    detail.innerHTML = "";
    detail = document.getElementById("detailsSprint");
    detail.innerHTML = "";
    detail = document.getElementById("detailsTask");
    detail.innerHTML = "";
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
            var titlelisteStories = document.getElementById("divtitleliststory");
            var listeStories = document.getElementById("divliststory");
            var html = '';
            var html2 = '';

            if (data.length!=0) {
                html ='<p class="h2 text-left">List of stories</p>';

                $.each(data, function (key, val) {
                    html2 +=
                        '<div id="storyId'+val.id+'">'+
                        '<div onclick="showStory('+val.id+')" class="padd mouseLink divStoryUnaffected">'+
                        '<span class="'+val.status+'">'+val.status+'</span>'+
                        '<span>'+val.nom+'</span>'+
                        '<div class="deleteStorylist">'+
                        '<button type="button" class="btnremove" onclick="modifierStoryById('+val.id+', null)">'+
                        '<span class="glyremovelist glyphicon glyphicon-edit"></span>'+
                        '</button>'+
                        '<button type="button" class="btnremove" onclick="effacerStoryById('+val.id+')">'+
                        '<span class="glyremovelist glyphicon glyphicon-remove-sign"></span>'+
                        '</button>'+
                        '</div>'+
                        '</div>'+
                        '</div>';
                });
                titlelisteStories.innerHTML = html;
                listeStories.innerHTML = html2;
            }
            else {
                html='<p class="h2 text-left" id="storyNone">No story (for now)</p>';
                titlelisteStories.innerHTML = html;
            }
        });
}


//Efface une story selon son id
function effacerStoryById( idStory )
{
    bootbox.confirm({
        message: "Would you really want to delete story #"+idStory+"?",
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
                    url: '/api/story/delete/'+idStory,
                    type: 'DELETE',
                    headers: {"Accept": "application/json", "Content-Type": "application/json"},
                    success: function (data) {
                        $("#storyId"+idStory).hide(500);
                        toastr.success("Story "+idStory+" has been deleted.");
                        var detail = document.getElementById("detailsStory");
                        detail.innerHTML = "";
                    },
                    error: function (resultat, statut, erreur) {
                        toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
                    }

                });
            }
        }
    });


}

function modifierStoryById(idStory, idSprint)
{
    if(idSprint != null) {
        addNewStory(document.getElementById('buttonAjouterStory'+idSprint));
        $("#buttonEnregistrerModif"+idSprint).show();
        $("#buttonEnregistrerModif"+idSprint).attr("onclick", "enregistrerModification("+idStory+","+idSprint+")");
        $("#buttonEnregistrerStory"+idSprint).hide();
        $.getJSON('/api/story/'+idStory,
            function (data) {
                $("#newstoryname"+idSprint).val(data.nom);
                $("#newstorydescription"+idSprint).val(data.description);
                $("#newstorystatus"+idSprint).val(data.status).change();
                $("#newstorypoints"+idSprint).val(data.points);
            });
    } else {
        addNewStoryUnaffected();
        $("#updateNewStory").show();
        $("#updateNewStory").attr("onclick", "enregistrerModification("+idStory+","+idSprint+")");
        $("#createNewStory").hide();
        $.getJSON('/api/story/'+idStory,
            function (data) {
                $("#newstorynameUnaffected").val(data.nom);
                $("#newstorydescriptionUnaffected").val(data.description);
                $("#newstorypointsUnaffected").val(data.points);
                $("#newstorysprintUnaffectedToDo").val(data.status).change();
            });
    }
}

function enregistrerModification(idStory, idSprint) {
    var story = {};
    if(idSprint != null) {
        story.nom = $("#newstoryname"+idSprint).val();
        story.description = $("#newstorydescription"+idSprint).val();
        story.status = $("#newstorystatus"+idSprint).val();
        story.points = $("#newstorypoints"+idSprint).val();
        var storySprint = idSprint;
    } else {
        story.nom = $("#newstorynameUnaffected").val();
        story.description = $("#newstorydescriptionUnaffected").val();
        story.points = $("#newstorypointsUnaffected").val();
        story.status = $("#newstorysprintUnaffectedToDo").val();
        var storySprint = $("#newstorysprintUnaffected").val();
    }

    if(story.nom!='') {
        $.ajax({
            url: '/api/story/update/'+idStory+'?idSprint='+storySprint,
            type: 'POST',
            headers: {"Accept": "application/json", "Content-Type": "application/json"},
            data:  JSON.stringify(story),
            success: function (data) {
                toastr.success(data.nom + " modifié");
                document.location.reload();
            },
            error: function (resultat, statut, erreur) {
                toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ")");
            }

        });
    }
    else{toastr.error("Story name is required.");}
}

function addNewStory(obj){

    var divaddnewstory = obj.nextSibling.nextSibling;
    var newStory = obj;

    var nb_sprint = document.getElementById("divlistsprint").getElementsByClassName("sprint").length;


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

//bouton annuler qui cache les div d'ajout
function hideNewStory() {

    var divaddnewsprint = document.getElementById("divaddnewsprint");
    var newSprint = document.getElementById("newSprint");
    var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

    if(divaddnewsprint.style.display = "block"){
        divaddnewsprint.style.display = "none";
        newSprint.style.display = "block";
    }
    if(divaddnewstoryUnaffected.style.display = "bock"){
        divaddnewstoryUnaffected.style.display = "none";
        newStoryUnaffected.style.display = "block";
    }
}

function hideNewStorySprint(){

    var divaddnewstory = obj.nextSibling.nextSibling;
    var newStory = obj;
    var nb_sprint = document.getElementById("divlistsprint").getElementsByClassName("sprint").length;

    for(var i = 1; i <= nb_sprint; i++){
        if(document.getElementById('divaddnewstory' + i) && document.getElementById('divaddnewstory' + i) != divaddnewstory){
            document.getElementById('divaddnewstory' + i).style.display = "block";
            document.getElementById('divaddnewstory' + i).previousSibling.previousSibling.style.display = "none";
        }
    }

    if(divaddnewstory.style.display == "block"){
        divaddnewstory.style.display = "none";
        newStory.style.display = "block";
    }
}

//Créé une story reliée à un sprint
function creerStoryDansSprint( idSprint )
{
    var story = {};
    story.nom = $("#newstoryname"+idSprint).val();
    story.description = $("#newstorydescription"+idSprint).val();
    story.status = $("#newstorystatus"+idSprint).val();
    story.points = $("#newstorypoints"+idSprint).val();
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
function deleteSprintById( idSprint )
{

    bootbox.confirm({
        message: "Would you really want to delete sprint #"+idSprint+"?",
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
                    url: '/api/sprint/delete/'+idSprint,
                    type: 'DELETE',
                    headers: {"Accept": "application/json", "Content-Type": "application/json"},
                    success: function (data) {
                        toastr.success("Sprint #"+idSprint+" has been deleted");
                        $("#sprintId"+idSprint).hide(500);
                        hideDetails();
                    },
                    error: function (resultat, statut, erreur) {
                        toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
                    }

                });
            }
        }
    });
}

function dragDropProject()
{
    //J'ai saigné du nez en écrivant cette ligne.
    var dg = dragula( $(".sprintContainer").children().map(function(){return document.getElementById(this.id)}).toArray(), function() {
        revertOnSpill: true
    });

    dg.on('drop', function(el){
        var idSprint = $(el).parent().attr('id').toString().toLowerCase().replace("liststoryinsprint","");
        var idStory = $(el).attr('id').toString().toLowerCase().replace("storyid","");

        if(null!=idSprint && null!=idStory)
        {
            $.ajax({
                url: '/api/sprint/'+idSprint+'/story/'+idStory,
                type: 'POST',
                headers: {"Accept": "application/json", "Content-Type": "application/json"},
                success: function (data) {
                    //RAS pour ne pas spammer
                },
                error: function (resultat, statut, erreur) {
                    toastr.error("An error occured while linking Sprint #"+idSprint+" & Story #"+idStory+". <br/>(" + statut + " - " + erreur + ").");
                }

            });
        }

    });
}


