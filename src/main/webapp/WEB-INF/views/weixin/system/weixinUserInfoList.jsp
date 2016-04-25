<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>微信粉丝信息管理</title>
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
		<li class="active"><a href="${ctx}/system/weixinUserInfo/">微信粉丝信息列表</a></li>
		<shiro:hasPermission name="system:weixinUserInfo:edit"><li><a href="${ctx}/system/weixinUserInfo/form">微信粉丝信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="weixinUserInfo" action="${ctx}/system/weixinUserInfo/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>昵称：</label>
				<form:input path="nickname" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>姓名：</label>
				<form:input path="username" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>联系方式：</label>
				<form:input path="tel" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="address" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信号标识</th>
				<th>昵称</th>
				<th>性别</th>
				<th>国家</th>
				<th>省份</th>
				<th>城市</th>
				<th>头像</th>
				<th>姓名</th>
				<th>联系方式</th>
				<th>地址</th>
				<th>最后登录时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="system:weixinUserInfo:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="weixinUserInfo">
			<tr>
				<td><a href="${ctx}/system/weixinUserInfo/form?id=${weixinUserInfo.id}">
					${weixinUserInfo.openid}
				</a></td>
				<td>
					${weixinUserInfo.nickname}
				</td>
				<td>
					${weixinUserInfo.sex==1?'男':'女'}
				</td>
				<td>
					${weixinUserInfo.country}
				</td>
				<td>
					${weixinUserInfo.province}
				</td>
				<td>
					${weixinUserInfo.city}
				</td>
				<td>
				    <img src="${weixinUserInfo.headimgurl}" width="150px" height="150px"/>
				</td>
				<td>
					${weixinUserInfo.username}
				</td>
				<td>
					${weixinUserInfo.tel}
				</td>
				<td>
					${weixinUserInfo.address}
				</td>
				<td>
					<fmt:formatDate value="${weixinUserInfo.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${weixinUserInfo.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${weixinUserInfo.remarks}
				</td>
				<shiro:hasPermission name="system:weixinUserInfo:edit"><td>
    				<a href="${ctx}/system/weixinUserInfo/form?id=${weixinUserInfo.id}">修改</a>
					<a href="${ctx}/system/weixinUserInfo/delete?id=${weixinUserInfo.id}" onclick="return confirmx('确认要删除该微信粉丝信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>