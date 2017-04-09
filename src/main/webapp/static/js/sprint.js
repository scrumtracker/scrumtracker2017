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

//Déplacer une tâche entre les statuts TODO/DOING/DONE :
function dragDrop( idStory ){
    dragula([document.getElementById("todo"+idStory), document.getElementById("doing"+idStory), document.getElementById("done"+idStory)],
        {revertOnSpill: true});
}