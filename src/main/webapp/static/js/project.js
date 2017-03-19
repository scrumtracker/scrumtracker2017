$(document).ready(function () {

    $("#createNewStory").click(function () {
        var story = {};
        story.nom = $("#newstorynameUnaffected").val();
        story.description = $("#newstorydescriptionUnaffected").val();
        story.points = $("#newstorypointsUnaffected").val();

        $.ajax({
            url: '/api/story/add',
            type: 'POST',
            headers: {"Accept": "application/json", "Content-Type": "application/json"},
            data: JSON.stringify(story),
            success: function (data) {
                $('#divaddnewstoryUnaffected').hide();
                $('#divaddnewstoryUnaffected').trigger("reset");
                $('#divaddnewstoryUnaffected').show();
                $('#divMessage').html(data.nom + " has been successfully added.");
                getListStories();
            },
            error: function (resultat, statut, erreur) {
                $('#divMessage').html("This story already exists. Please choose another name. <br/>(" + statut + " - " + erreur + ")");
            }

        });

    });

    getListStories();


    function getListStories() {
        $.getJSON('/api/story',
            function (data) {
                listeStories = document.getElementById("divliststory");

                if (data.length!=0) {
                    var html = '<p class="h2 text-center" th:text="#{story.list}"></p>';

                    $.each(data, function (key, val) {

                        html +=
                            '<li>'+
                            '<div class="list-group-item" onclick="showStory('+val.id+')">'
                            + val.nom +
                            '<div class="deleteStorylist">'+
                            '<button type="button" class="btnremove">'+
                            '<span class="glyremovelist glyphicon glyphicon-remove-sign"></span>'+
                            '</button>'+
                            '</div>'+
                            '</div>'+
                            '</li>';
                    });
                    $("#storyNone").hide();
                    listeStories.innerHTML = html;

                }
                else {
                    $("#storyNone").show();
                }


            });

    };

    $("#createnewSprint").click(function () {

        var sprint = {};
        sprint.nom = $("#newsprintname").val();
        sprint.dateDebut = $("#newSprintDateDebut").val();
        sprint.dateFin = $("#newSprintDateFin").val();
        sprint.idProject = $("#newsprintprojectid").val();

        $.ajax({

            url: '/api/sprint/add',
            type: 'POST',
            headers: {"Accept": "application/json", "Content-Type": "application/json"},
            data: JSON.stringify(sprint),
            success: function (data) {
                $('#divaddnewsprint').hide();
                $('#divaddnewsprint').trigger("reset");
                $('#divaddsprint').show();
                toastr.success(data.nom+" ajouté");
                getListSprints();
                getSprintsListMenu();
            },
            error: function (resultat, statut, erreur) {
                toastr.error(statut+" - "+erreur);
            }

        });

    });

    getListSprints();

    function getListSprints() {
        $.getJSON('/api/sprint',
            function (data) {
                listeSprints = document.getElementById("divlistsprint");

                if (data.length!=0) {
                    var html = '<p class="h2 text-center" th:text="#{sprint.list}"></p>';

                    $.each(data, function (key, val) {

                        html +=
                            '<div class="sprint">'+
                            '<div class="list-group-item storyinsprint" onclick="detailSprint('+val.id+')">'+
                            '<div class="padd2">'+
                            '<span class="bold">' + val.nom+ '</span>'+
                            '</div>'+
                            '<button class="btn btn-xs btn-default btnseetasks" onclick="">'+
                            '<span th:text="#{see.tasks}"></span>'+
                            '<span class="glybtnleft glyphicon glyphicon-chevron-right"></span>'+
                            '</button>'+
                            '</div>'+
                            '</div>';
                    });
                    $("#sprintNone").hide();
                    listeSprints.innerHTML = html;
                }
                else {
                    $("#sprintNone").show();
                }
            });
    }
});

function addNewSprint(){

    var divaddnewsprint = document.getElementById("divaddnewsprint");
    var newSprint = document.getElementById("newSprint");

    var divaddnewstoryUnaffected = document.getElementById("divaddnewstoryUnaffected");
    var newStoryUnaffected = document.getElementById("newStoryUnaffected");

    divaddnewsprint.style.display = "block";
    newSprint.style.display = "none";

    divaddnewstoryUnaffected.style.display = "none";
    newStoryUnaffected.style.display = "block";
}

function showSprint(obj){

	var sousMenu = obj.childNodes[5];
	
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

function showStory(id){
    console.log(id);

    $.getJSON('/api/story/'+id,
        function (data) {
            detailStory = document.getElementById("detailsStory");

            if (data.length!=0) {
                var html =  '<p>Story name : '+data.nom+'</p>'+
                '<p>Creation date : </p>'+
                '<p>State : '+data.status+'</p>'+
                '<p>Total hours of works : </p>'+
                '<p>Number of tasks : </p>'+
                '<p>Goals : '+data.description+'</p>';
                detailStory.innerHTML = html;
            }
        });


}

function detailSprint(id){

    $.getJSON('/api/sprint/'+id,
        function (data) {
            detailofSprint = document.getElementById("detailsSprint");
            if (data.length!=0) {
                console.log(data);
                var html =  '<p>Sprint name : '+data.nom+'</p>'+
                    '<p>Date of beginning :' +data.dateDebut+ '</p>'+
                    '<p>Date of the end : '+data.dateFin+'</p>';
                detailofSprint.innerHTML = html;
            }
        });
}
