function addNewSprint(){

    var formaddnewsprint = document.getElementById("formaddnewsprint");
    var newSprint = document.getElementById("newSprint");

    var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

    formaddnewsprint.style.display = "block";
    newSprint.style.display = "none";




    divaddnewstoryUnaffected.style.display = "none";
    newStoryUnaffected.style.display = "block";
}

function showSprint(obj){

	var sousMenu = obj.childNodes[5];

	//alert(sousMenu.nodeName);
	
	/*
	if(sousMenu.style.display == "block"){
		sousMenu.style.display = "none";
	}
	else{
		sousMenu.style.display = "block";
	}*/
	
	sousMenu.style.display = "block";
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



	var formaddnewsprint = document.getElementById("formaddnewsprint");
    var newSprint = document.getElementById("newSprint");
    var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

	if(newStoryUnaffected.style.display == "none"){
		divaddnewstoryUnaffected.style.display = "none";
		newStoryUnaffected.style.display = "block";
	}
	if(newSprint.style.display == "none"){
		formaddnewsprint.style.display = "none";
		newSprint.style.display = "block";
	}
}

function addNewSprintUnaffected(){

	var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

    var formaddnewsprint = document.getElementById("formaddnewsprint");
    var newSprint = document.getElementById("newSprint");

    divaddnewstoryUnaffected.style.display = "block";
    newStoryUnaffected.style.display = "none";




    formaddnewsprint.style.display = "none";
    newSprint.style.display = "block";
}