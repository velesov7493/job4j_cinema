<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:import url="../template/layouts/pageHeader.jsp">
    <c:param name="pageTitle" value="Оплата" />
</c:import>

<div class="row">
    <h3>Вы выбрали <c:out value="${row}"/> ряд <c:out value="${col}"/> место. К оплате : 500 рублей.</h3>
</div>
<div class="row">
    <form action="<c:url value="/payment.do?row=${row}&col=${col}"/>" method="post">
        <div class="form-group">
            <label for="username">ФИО</label>
            <input type="text" class="form-control" id="username" placeholder="Ф.И.О."/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" class="form-control" id="email" placeholder="Email"/>
        </div>
        <div class="form-group">
            <label for="phone">Номер телефона</label>
            <input type="text" class="form-control" id="phone" placeholder="Номер телефона"/>
        </div>
        <button type="button" class="btn btn-success">Оплатить</button>
    </form>
</div>

<c:import url="../template/layouts/pageFooter.jsp" />
