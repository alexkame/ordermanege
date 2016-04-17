<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信管理员管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/admin/weixinAdminUser/">微信管理员列表</a></li>
		<shiro:hasPermission name="admin:weixinAdminUser:edit"><li><a href="${ctx}/admin/weixinAdminUser/form">微信管理员添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinAdminUser" action="${ctx}/admin/weixinAdminUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>微信粉丝表ID：</label>
				<form:input path="weisinUserInfo.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>用户名：</label>
				<form:input path="userName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>联系方式：</label>
				<form:input path="tel" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信粉丝表ID</th>
				<th>用户名</th>
				<th>姓名</th>
				<th>联系方式</th>
				<th>最后登录时间</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="admin:weixinAdminUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinAdminUser">
			<tr>
				<td><a href="${ctx}/admin/weixinAdminUser/form?id=${weixinAdminUser.id}">
					${weixinAdminUser.weisinUserInfo.id}
				</a></td>
				<td>
					${weixinAdminUser.userName}
				</td>
				<td>
					${weixinAdminUser.name}
				</td>
				<td>
					${weixinAdminUser.tel}
				</td>
				<td>
					<fmt:formatDate value="${weixinAdminUser.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinAdminUser.remarks}
				</td>
				<td>
					<fmt:formatDate value="${weixinAdminUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="admin:weixinAdminUser:edit"><td>
    				<a href="${ctx}/admin/weixinAdminUser/form?id=${weixinAdminUser.id}">修改</a>
					<a href="${ctx}/admin/weixinAdminUser/delete?id=${weixinAdminUser.id}" onclick="return confirmx('确认要删除该微信管理员吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>