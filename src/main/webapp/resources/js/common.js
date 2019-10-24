function getMoreLinks(tableName){
		$.ajax({
			type :"POST",
			url  : "getMoreLinks",
			data : {
				tableName : tableName,			
			}, 
			success : function(data){
				
				if(data=="true"){
					$("#getMoreLinks").hide();
					location.reload();	
				}else{
					alert(data);
				}
				
			},error : function(e){
				console.log("Error getMoreLinks ::"+e);
			}
		});
	
}