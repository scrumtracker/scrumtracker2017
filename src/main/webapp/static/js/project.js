$(document).ready(function () {

    $("#createNewStory").click(function () {

        $.ajax({

            url: '/api/story/add',
            // cache: false,
            type: 'POST',
            headers: {"Accept": "application/json", "Content-Type": "application/json"},
            data: '{"description": "' + $("#newstorydescriptionUnaffected").val() + '", "nom": "' + $("#newstorynameUnaffected").val() + '", "points": "' + $("#newstorypointsUnaffected").val() + '"}',
            success: function (data) {
                $('#divaddnewstoryUnaffected').hide();
                $('#divaddnewstoryUnaffected').trigger("reset");
                $('#divaddstoryunaffected').show();
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
                            '<a href=story/' + val.id + ' data-ajax="false" class="list-group-item">'+ val.nom +
                            '<div class="deleteStorylist">'+
                            '<button type="button" class="btnremove">'+
                            '<span class="glyremovelist glyphicon glyphicon-remove-sign"></span>'+
                            '</button>'+
                            '</div>'+
                            '</a>'+
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

        $.ajax({

            url: '/api/sprint/add',
            type: 'POST',
            headers: {"Accept": "application/json", "Content-Type": "application/json"},
            data: '{"nom": "' + $("#newsprintname").val() + '", "date de début": "' + $("#newSprintDateDebut").val() + '", "date de fin": "' + $("#newSprintDateFin").val() + '"}',
            success: function (data) {
                $('#divaddnewsprint').hide();
                $('#divaddnewsprint').trigger("reset");
                $('#divaddsprint').show();
                $('#divMessageSprint').html(data.nom + " has been successfully added.");
                getListSprints();

            },
            error: function (resultat, statut, erreur) {
                $('#divMessageSprint').html("This story already exists. Please choose another name. <br/>(" + statut + " - " + erreur + ")");
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
                            '<div id="onClickSprint" class="list-group-item storyinsprint" onclick="getDetailSprint(this)">'+
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

    };

    $("#onClickSprint").click(function () {

        $.ajax({

            url: '/api/sprint',
            type: 'GET',
            // headers: {"Accept": "application/json", "Content-Type": "application/json"},
            // data: '{"nom": "' + $("#newsprintname").val() + '", "date de début": "' + $("#newSprintDateDebut").val() + '", "date de fin": "' + $("#newSprintDateFin").val() + '"}',
            success: function (data) {
                listeSprints = document.getElementById("divlistsprint");

                if (data.length != 0) {
                    var html = '<p class="h2 text-center" th:text="#{sprint.list}"></p>';

                    $.each(data, function (key, val) {

                        html +=
                            '<div class="sprint">' +
                            '<div class="list-group-item storyinsprint" onclick="getDetailSprint(this)">' +
                            '<div class="padd2">' +
                            '<span class="bold">' + val.nom + '</span>' +
                            '</div>' +
                            '<button id="onClickSprint" class="btn btn-xs btn-default btnseetasks" onclick="">' +
                            '<span th:text="#{see.tasks}"></span>' +
                            '<span class="glybtnleft glyphicon glyphicon-chevron-right"></span>' +
                            '</button>' +
                            '</div>' +
                            '</div>';
                    });
                    $("#sprintNone").hide();
                    listeSprints.innerHTML = html;
                }
                else {
                    $("#sprintNone").show();
                }
            }
        })

    });


    function getDetailSprint() {
        $.getJSON('/api/sprint',
            function (data) {
                listeDetailSprint = document.getElementById("divdetail");

                if (data.length!=0) {
                    var html = '<p class="h2 text-center" th:text="#{sprint.detail}"></p>';

                    $.each(data, function (key, val) {

                        html +=
                            '<div class="detailSprint">'+
                            '<p class="h4 text-center"> D&eacutetail du Sprint </p>'+
                            '<div>'+
                            '<button type="button" class="btnedit btn-xs btn btn-default">'+
                            '<span class="glyedit glyphicon glyphicon-edit"></span>'+
                            '</button>'+
                            '</div>'+
                            '</div>'+
                            '<p>Nom du Sprint : ' +val.nom+ '</p>'+
                            '<p>D&eacutebut du Sprint : ' +val.dateDebut+ '</p>'+
                            '<p>Fin du Sprint : ' +val.dateFin+ '</p>'+
                            '</div>';
                    });
                    listeDetailSprint.innerHTML = html;
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