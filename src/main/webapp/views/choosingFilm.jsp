<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:set var="pageTitle" value="Выбор фильма" />
<c:set var="pageScript" value="film.js" />
<%@ include file="../template/layouts/pageHeader.jsp" %>
<div class="row">
    <div class="col-xl-4 col-lg-6 col-md-9 col-sm-12 h-center">
        <ul class="filmList">
            <c:forEach items="${films}" var="film">
            <li id="<c:out value="${film.id}"/>"><c:out value="${film.filmName}"/></li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="row">
    <div class="col-xl-4 col-lg-6 col-md-9 col-sm-12 h-center">
        <form id="choose-film" action="<c:url value="/film.do"/>" method="post">
            <input name="nSession" id="iSession" type="hidden" value=""/>
            <button type="submit" class="btn btn-primary pull-right">Выбрать</button>
        </form>
    </div>
</div>

<%@ include file="../template/layouts/pageFooter.jsp" %>