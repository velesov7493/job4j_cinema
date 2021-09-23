<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:set var="pageTitle" value="Оплата" />
<%@ include file="../template/layouts/pageHeader.jsp" %>

<div class="row">
    <div class="col-12">
        <h3 class="text-center">Забронировано место на фильм &quot;<c:out value="${cinemaSession.filmName}"/>&quot;</h3>
        <p>Вы выбрали <c:out value="${row}"/> ряд <c:out value="${col}"/> место.</p>
        <p>К оплате : 500 рублей.</p>
        <form action="<c:url value="/payment.do"/>" method="post">
            <div class="form-group">
                <label for="username">ФИО</label>
                <input name="nUserName" type="text" class="form-control" id="username" placeholder="Ф.И.О." required />
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input name="nEmail" type="text" class="form-control" id="email" placeholder="Email" required />
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input name="nPhone" type="text" class="form-control" id="phone" placeholder="Номер телефона" required />
            </div>
            <button type="submit" class="btn btn-success pull-right">Оплатить</button>
        </form>
    </div>
</div>

<%@ include file="../template/layouts/pageFooter.jsp" %>
