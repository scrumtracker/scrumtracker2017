function addNewStory(){

    var divaddnewstory = document.getElementById("divaddnewstory");
    var newStory = document.getElementById("newStory");

    divaddnewstory.style.display = "block";
    newStory.style.display = "none";
}

function addNewTask(obj){

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
