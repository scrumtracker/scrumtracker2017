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
                getStoriesListMenu();

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