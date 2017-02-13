$("#codeActivated").change(function(){
    if($("#codeActivated").is(':checked'))
    {
        $("#code").prop('disabled', false);
    }
    else
    {
        $("#code").prop('disabled', true);
    }
});

$("#newProject").click(function(){
    bootbox.alert({
        message: "This is the small alert!"
    });
})