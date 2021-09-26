<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:set var="pageTitle" value="Ошибка" />
<%@ include file="../template/layouts/pageHeader.jsp" %>

<div class="row">
    <div class="col-12 error-panel">
        <h2>Ошибка бронирования места в зале</h2>
        <p>Выбранное Вами место уже занято! Пожалуйста выберите другое место.</p>
        <a class="btn btn-outline-secondary pull-right" href="<c:url value="/place.do"/>">Выбрать</a>
    </div>
</div>

<%@ include file="../template/layouts/pageFooter.jsp" %>