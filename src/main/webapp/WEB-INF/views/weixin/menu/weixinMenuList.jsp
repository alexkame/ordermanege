<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信菜单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<script type="text/javascript">
		$(document).ready(function() {
			var tpl = $("#treeTableTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
			var data = ${fns:toJson(list)}, ids = [], rootIds = [];
			for (var i=0; i<data.length; i++){
				ids.push(data[i].id);
			}
			ids = ',' + ids.join(',') + ',';
			for (var i=0; i<data.length; i++){
				if (ids.indexOf(','+data[i].parentId+',') == -1){
					if ((','+rootIds.join(',')+',').indexOf(','+data[i].parentId+',') == -1){
						rootIds.push(data[i].parentId);
					}
				}
			}
			for (var i=0; i<rootIds.length; i++){
				addRow("#treeTableList", tpl, data, rootIds[i], true);
			}
			$("#treeTable").treeTable({expandLevel : 5});
			
			//生成菜单
			$("#btnCreateMenu").click(function(){
				top.$.jBox.confirm("确认要生成微信菜单吗？","系统提示",function(v,h,f){
					if(v=="ok"){
						$("#searchForm").attr("action","${ctx}/menu/weixinMenu/createMenu");
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function addRow(list, tpl, data, pid, root){
			for (var i=0; i<data.length; i++){
				var row = data[i];
				if ((${fns:jsGetVal('row.parentId')}) == pid){
					$(list).append(Mustache.render(tpl, {
						dict: {
						blank123:0}, pid: (root?0:pid), row: row
					}));
					addRow(list, tpl, data, row.id);
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/menu/weixinMenu/">微信菜单列表</a></li>
		<shiro:hasPermission name="menu:weixinMenu:edit"><li><a href="${ctx}/menu/weixinMenu/form">微信菜单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinMenu" action="${ctx}/menu/weixinMenu/" method="post" class="breadcrumb form-search">
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>URL：</label>
				<form:input path="url" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			<input id="btnCreateMenu" class="btn btn-primary" type="button" value="生成菜单"/>
			</li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="treeTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名称</th>
				<th>URL</th>
				<th>更新时间</th>
				<shiro:hasPermission name="menu:weixinMenu:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody id="treeTableList"></tbody>
	</table>
	<script type="text/template" id="treeTableTpl">
		<tr id="{{row.id}}" pId="{{pid}}">
			<td><a href="${ctx}/menu/weixinMenu/form?id={{row.id}}">
				{{row.name}}
			</a></td>
			<td>
				{{row.url}}
			</td>
			<td>
				{{row.updateDate}}
			</td>
			<shiro:hasPermission name="menu:weixinMenu:edit"><td>
   				<a href="${ctx}/menu/weixinMenu/form?id={{row.id}}">修改</a>
				<a href="${ctx}/menu/weixinMenu/delete?id={{row.id}}" onclick="return confirmx('确认要删除该微信菜单及所有子微信菜单吗？', this.href)">删除</a>
				<a href="${ctx}/menu/weixinMenu/form?parent.id={{row.id}}">添加下级微信菜单</a> 
			</td></shiro:hasPermission>
		</tr>
	</script>
</body>
</html>