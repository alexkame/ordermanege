<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单明细管理</title>
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
		<li class="active"><a href="${ctx}/order/orderDetail/">订单明细列表</a></li>
		<shiro:hasPermission name="order:orderDetail:edit"><li><a href="${ctx}/order/orderDetail/form">订单明细添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="orderDetail" action="${ctx}/order/orderDetail/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单ID</th>
				<th>配件ID</th>
				<th>数量</th>
				<th>类型(1:配件,2:瓦片)</th>
				<th>节数</th>
				<th>片数</th>
				<th>颜色(1:深灰,2:枣红,3:砖红,4:蓝色)</th>
				<th>厚度(1:2.5mm,2:3.0mm)</th>
				<th>每片长度(米)</th>
				<th>总平方</th>
				<shiro:hasPermission name="order:orderDetail:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="orderDetail">
			<tr>
				<td><a href="${ctx}/order/orderDetail/form?id=${orderDetail.id}">
					${orderDetail.orderId.id}
				</a></td>
				<td>
					${orderDetail.partsInfoId.id}
				</td>
				<td>
					${orderDetail.num}
				</td>
				<td>
					${orderDetail.type}
				</td>
				<td>
					${orderDetail.nodeNum}
				</td>
				<td>
					${orderDetail.tableNum}
				</td>
				<td>
					${orderDetail.color}
				</td>
				<td>
					${orderDetail.thickness}
				</td>
				<td>
					${orderDetail.pieceLength}
				</td>
				<td>
					${orderDetail.totalSquare}
				</td>
				<shiro:hasPermission name="order:orderDetail:edit"><td>
    				<a href="${ctx}/order/orderDetail/form?id=${orderDetail.id}">修改</a>
					<a href="${ctx}/order/orderDetail/delete?id=${orderDetail.id}" onclick="return confirmx('确认要删除该订单明细吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>