<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增/修改页面</title>
</head>
<body>
	<!-- 注意引入js的位置必须包含在body内 -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/newWin1.js"></script>
	<div id="cc" class="easyui-layout" data-options="fit:true">   
	    <div data-options="region:'center'" style="padding:3px;background:#eee;">
	    	<form id="saveForm">
				<input name="id" type="hidden"/>
				<table>
					<tr>
						<td>用户名</td>
						<td><input name="username" class="easyui-textbox" data-options="required:true"/></td>
					</tr>
					<tr>
						<td>真实姓名</td>
						<td><input name="realname" class="easyui-textbox" /></td>
					</tr>
				</table>
			</form>	
	    </div>   
	    <div data-options="region:'south'" style="height:33px;padding:3px;text-align: center" >
			<a  id="saveBtn"  class="easyui-linkbutton" data-options="iconCls:'icon-page_save'">保存</a>
			<a  id="closeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">关闭</a>
	    </div>   
	</div>  
</body>
</html>