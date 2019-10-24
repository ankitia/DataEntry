function setStatus(status,urlId){	
	$.ajax({
		type : "POST",
		url : "updateUrlStatus",
		data :{
			status : status,
			urlId : urlId,
			action : "companyData"
		},
		success : function(data){
			
		},
		error : function(e){
			consloe.log("Error ::: "+e);
		}
	});	
}

