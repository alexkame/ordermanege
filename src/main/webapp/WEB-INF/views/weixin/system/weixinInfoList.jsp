<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信信息管理</title>
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
		<li class="active"><a href="${ctx}/system/weixinInfo/">微信信息列表</a></li>
		<shiro:hasPermission name="system:weixinInfo:edit"><li><a href="${ctx}/system/weixinInfo/form">微信信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinInfo" action="${ctx}/system/weixinInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>微信名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>微信号：</label>
				<form:input path="weixinid" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>微信昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信名</th>
				<th>微信号</th>
				<th>微信昵称</th>
				<th>微信URL</th>
				<th>微信token</th>
				<th>微信appid</th>
				<th>微信appsecret</th>
				<th>微信商家账号</th>
				<th>微信商家秘钥</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="system:weixinInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinInfo">
			<tr>
				<td><a href="${ctx}/system/weixinInfo/form?id=${weixinInfo.id}">
					${weixinInfo.name}
				</a></td>
				<td>
					${weixinInfo.weixinid}
				</td>
				<td>
					${weixinInfo.nickname}
				</td>
				<td>
					${weixinInfo.url}
				</td>
				<td>
					${weixinInfo.token}
				</td>
				<td>
					${weixinInfo.appid}
				</td>
				<td>
					${weixinInfo.appsecret}
				</td>
				<td>
					${weixinInfo.partner}
				</td>
				<td>
					${weixinInfo.partnerkey}
				</td>
				<td>
					<fmt:formatDate value="${weixinInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinInfo.remarks}
				</td>
				<shiro:hasPermission name="system:weixinInfo:edit"><td>
    				<a href="${ctx}/system/weixinInfo/form?id=${weixinInfo.id}">修改</a>
					<a href="${ctx}/system/weixinInfo/delete?id=${weixinInfo.id}" onclick="return confirmx('确认要删除该微信信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>