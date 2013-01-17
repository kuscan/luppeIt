var baseUrl = "http://swe.cmpe.boun.edu.tr:7880";

$(document).ready(function() {

	$("#selectAll").bind('click', function(){
		$('#userCategories').each(function(){
			$('#userCategories option').prop("selected", true);
		});
	});
	
	$("#deselectAll").bind('click', function(){
		$('#userCategories').each(function(){
			$('#userCategories option').prop("selected", false);
		});
	});
	
	$("#addTagButton").bind('click', function(){
		if ($('#addTagFormInfo').css('visibility') == 'visible') {
			$('#addTagFormInfo').css('visibility', 'collapse');
			$('#addTagFormInfo').html('');
			$('#addTagForm').css('visibility', 'visible');
			$('#addTagButton').html('<i class="icon-minus"></i>');
		} else {
			$('#addTagFormInfo').css('visibility', 'visible');
			$('#addTagFormInfo').html('Add Tag');
			$('#addTagForm').css('visibility', 'collapse');
			$('#addTagButton').html('<i class="icon-plus"></i>');
		}
	});
	
	$("#addTagFormInfo").bind('click', function(){
		if ($('#addTagFormInfo').css('visibility') == 'visible') {
			$('#addTagFormInfo').css('visibility', 'collapse');
			$('#addTagFormInfo').html('');
			$('#addTagForm').css('visibility', 'visible');
			$('#addTagButton').html('<i class="icon-minus"></i>');
		} else {
			$('#addTagFormInfo').css('visibility', 'visible');
			$('#addTagFormInfo').html('Add Tag');
			$('#addTagForm').css('visibility', 'collapse');
			$('#addTagButton').html('<i class="icon-plus"></i>');
		}
	});
	
	
	$("#tags").keyup(function autocompleteTag() {
		var text = $(this).val();
		console.log(text);
		jQuery.noConflict();
		$.ajax({
			url: baseUrl + "/autocompleteTag/" + text,
			type: "POST",
			data: {text: text},
			success: function(response) {
				console.log(response);
				$("#tags").autocomplete({ source: response });
			}
		});
	});
		
});

function luppeShare(shareId) {
    $.ajax({
    	url: baseUrl + "/luppeShare",
    	type: "POST",
    	data: {shareId: shareId},
    	success: function(response){ 
    	    if (response.indexOf("Success") >= 0) {
    	    	$("#luppeButton" + shareId).html('<div class="lupped-button"><i class="icon-thumbs-up"></i> Lupped</div>');
    	    	$("#digButton" + shareId).html('<a onclick="digShare(' + shareId + ')"><div class="dig-button"><i class="icon-thumbs-down"></i> Bury</div></a>');
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
				$("#digButton" + shareId).html('<div class="digged-button"><i class="icon-thumbs-down"></i> Buried</div>');
				$("#luppeButton" + shareId).html('<a onclick="luppeShare(' + shareId + ')"><div class="luppe-button"><i class="icon-thumbs-down"></i> Luppe</div></a>');
			}
		}
	});
}

function confirmShareTag(shareTagId) {
	$.ajax({
		url: baseUrl + "/confirmShareTag/" + shareTagId,
		type: "POST",
		data: {shareTagId: shareTagId},
		success: function(response) {
			if ($('#contributeTagTruthMessage').html().indexOf("Thanks") >= 0) {
				
			} else {
				$('#contributeTagTruthMessage').html("Thanks for contribution :)");
			}
		}
	});
}

function denyShareTag(shareTagId) {
	$.ajax({
		url: baseUrl + "/denyShareTag/" + shareTagId,
		type: "POST",
		data: {shareTagId: shareTagId},
		success: function(response) {
			if ($('#contributeTagTruthMessage').html().indexOf("Thanks") >= 0) {
				
			} else {
				$('#contributeTagTruthMessage').html("Thanks for contribution :)");
			}
		}
	});
}

