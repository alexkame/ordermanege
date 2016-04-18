<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单表管理</title>
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
		<li class="active"><a href="${ctx}/order/ordertable/">订单表列表</a></li>
		<shiro:hasPermission name="order:ordertable:edit"><li><a href="${ctx}/order/ordertable/form">订单表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="ordertable" action="${ctx}/order/ordertable/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>微信粉丝ID：</label>
				<form:input path="weixinUserInfoId.id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>订单号：</label>
				<form:input path="orderNum" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>微信粉丝ID</th>
				<th>订单号</th>
				<th>下单时间</th>
				<th>状态(1:待发货,2:已发货,3:作废)</th>
				<th>总平方</th>
				<th>发货时间</th>
				<shiro:hasPermission name="order:ordertable:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ordertable">
			<tr>
				<td><a href="${ctx}/order/ordertable/form?id=${ordertable.id}">
					${ordertable.weixinUserInfoId.id}
				</a></td>
				<td>
					${ordertable.orderNum}
				</td>
				<td>
					<fmt:formatDate value="${ordertable.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${ordertable.status}
				</td>
				<td>
					${ordertable.totalSquare}
				</td>
				<td>
					<fmt:formatDate value="${ordertable.deliveryTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="order:ordertable:edit"><td>
    				<a href="${ctx}/order/ordertable/form?id=${ordertable.id}">修改</a>
					<a href="${ctx}/order/ordertable/delete?id=${ordertable.id}" onclick="return confirmx('确认要删除该订单表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>