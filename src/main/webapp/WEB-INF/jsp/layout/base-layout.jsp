<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
	<head>

		<tiles:insertAttribute name="header"/>
	</head>

	<body id="page-top">
		<%--<tiles:insertAttribute name="nav"/>--%>

		<div id="wrapper">
			<tiles:insertAttribute name="left"/>

			<div id="content-wrapper">
				<tiles:insertAttribute name="body"/>
				<tiles:insertAttribute name="footer"/>
			</div>
		</div>
		<%--<a class="scroll-to-top rounded" href="#page-top">
			<i class="fas fa-angle-up"></i>
		</a>--%>
	</body>
</html>