$(document).ready(function() {

    $('input[name=tag]').keyup(function() {
        clearTimeout($.data(this, 'timer'));
        var wait = setTimeout(search, 1000);
        $(this).data('timer', wait);
    });

    function search() {
        alert($('input[name=tag]').val());
    }
    
});

var baseUrl = "http://localhost:7880";

function luppeShare(shareId) {
    $.ajax({
    	url: baseUrl + "/luppeShare",
    	type: "POST",
    	data: {shareId: shareId},
    	success: function(response){ 
    	    if (response.indexOf("Success") >= 0) {
    	    	$("#luppeButton" + shareId).html('<div class="lupped-button"><i class="icon-thumbs-up"></i> Lupped</div>');
    	    	$("#digButton" + shareId).html('<a onclick="digShare(' + shareId + ')"><div class="dig-button"><i class="icon-thumbs-down"></i> Dig</div></a>');
    	    }
    	}
    });
}

function digShare(shareId) {
	$.ajax({
		url: baseUrl + "/digShare",
		type: "POST",
		data: {shareId: shareId},
		success: function(response){
			if (response.indexOf("Success") >= 0) {
				$("#digButton" + shareId).html('<div class="digged-button"><i class="icon-thumbs-down"></i> Digged</div>');
				$("#luppeButton" + shareId).html('<a onclick="luppeShare(' + shareId + ')"><div class="luppe-button"><i class="icon-thumbs-down"></i> Dig</div></a>');
			}
		}
	});
}