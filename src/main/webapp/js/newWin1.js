$(function(){
	
	var URL = {
		saveObj : "AddGridData"	
	};
	var addForm = $("#saveForm");
	
	renderBtn();
	function renderBtn(){
		$("#saveBtn").bind("click",function(){
			saveObj();
		});
		
		$("#closeBtn").bind("click",function(){
			$("#newWin").window("destroy");//close有时会无法关闭，可直接销毁
		});
	}
	
	function saveObj(){
		var formData = addForm.serializeObject();
		if(addForm.form("validate")){
			$.messager.confirm("提示","是否保存?",function(r){
				if(r){
					$.messager.progress({text:"正在保存..."});
					$.ajax({
						url : URL.saveObj,
						data :formData,
						type :"post",
						success: function(data){
							$.messager.progress("close");
							$.messager.show({
								title:'提示',
								msg:data
							});
							if(data == "success"){
								$("#newWin").window("destroy");//close有时会无法关闭，可直接销毁
								$("#grid").datagrid("load");
							}
						}
					});
				}
			});
		}
	}
});