$(document).ready(function () {
    $(".task").on("click", function(){
        detailsTask(this.id);
    })
})

//Affiche les détails d'une tâche
function detailsTask( idTask)
{
    $.ajax({
        url: '/api/task/'+idTask,
        type: 'GET',
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        success: function (data) {
            if(data.nom)
                $("#taskDetails_nom").text(data.nom);
            else
                $("#taskDetails_nom").text("No name given to this task");
            if(data.dateCreation)
                $("#taskDetails_dateCreation").text(moment(data.dateCreation).format('DD/MM/YYYY HH:mm'));
            else
                $("#taskDetails_dateCreation").text("Not established");
            if(data.tempsDeCharge)
                if(data.uniteTempsDeCharge)
                    $("#taskDetails_totalHoursOfWork").text(data.tempsDeCharge+" "+data.uniteTempsDeCharge);
                else
                    $("#taskDetails_totalHoursOfWork").text(data.tempsDeCharge+" au");
            else
                $("#taskDetails_totalHoursOfWork").text( "unknow");
            if(data.description)
                $("#taskDetails_description").text(data.description);
            else
                $("#taskDetails_description").text();

        },
        error: function (resultat, statut, erreur) {
            toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
        }

    });
}

//Affiche le formulaire d'ajout de Story :
function showFormAddNewStory(){

    var divaddnewstory = document.getElementById("divaddnewstory");
    var newStory = document.getElementById("newStory");

    divaddnewstory.style.display = "block";
    newStory.style.display = "none";
}

//Affiche le formulaire d'ajout de tâche :
function showFormAddNewTask(obj){

    var divaddnewtask = obj.nextSibling.nextSibling;
    var newTask = obj;

    var nb_story = document.getElementById("divlistories").getElementsByClassName("story").length;
    //alert(nb_story);

    for(var i = 1; i <= nb_story; i++){
        if(document.getElementById('divaddnewtask' + i) && document.getElementById('divaddnewtask' + i) != divaddnewtask){
            document.getElementById('divaddnewtask' + i).style.display = "none";
            document.getElementById('divaddnewtask' + i).previousSibling.previousSibling.style.display = "block";
        }
    }

    if(divaddnewtask){
        if(divaddnewtask.style.display == "block"){
            divaddnewtask.style.display = "none";
            newTask.style.display = "block";
        }
        else{
            divaddnewtask.style.display = "block";
            newTask.style.display = "none";
        }
    }

    var divaddnewstory = document.getElementById("divaddnewstory");
    var newStory = document.getElementById("newStory");

    if(newStory.style.display == "none"){
        divaddnewstory.style.display = "none";
        newStory.style.display = "block";
    }
}

//Créer une story reliée au sprint de la page sprint actuelle :
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

function addNewTaskInStory(idStory){
    var task = {};
    task.nom = $("#newtaskname"+idStory).val();
    task.description = $("#newtaskdescription"+idStory).val();
    task.status = $("#newtaskstatus"+idStory).val();
    task.tempsDeCharge = $("#newtasktotalhoursofwork"+idStory).val();
    task.dateCreation = Date.now();
    $.ajax({
        url: '/api/task/add/story/'+idStory,
        type: 'POST',
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        data: JSON.stringify(task),
        success: function (data) {
            toastr.success(data.nom + " has been successfully added.");
            document.location.reload();
        },
        error: function (resultat, statut, erreur) {
            toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
        }

    });
}

//Déplacer une tâche entre les statuts TODO/DOING/DONE :
function dragDrop( idStory ){
    dragula([document.getElementById("todo"+idStory), document.getElementById("doing"+idStory), document.getElementById("done"+idStory)],
        {revertOnSpill: true});
}

//Supprimer une tâche (id de la tâche en paramètre) :
function deleteTaskById(idTask){
    $.ajax({
        url: '/api/task/delete/'+idTask,
        type: 'DELETE',
        headers: {"Accept": "application/json", "Content-Type": "application/json"},
        success: function (data) {
            toastr.success(data.nom + " has been successfully deleted");
            $("#"+idTask).hide(500);
        },
        error: function (resultat, statut, erreur) {
            toastr.error("An error occured. <br/>(" + statut + " - " + erreur + ").");
        }
    });
}