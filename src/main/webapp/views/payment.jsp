<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:import url="../template/layouts/pageHeader.jsp">
    <c:param name="pageTitle" value="Оплата" />
</c:import>

<div class="row">
    <h3>Забронировано место на фильм &quot;<c:out value="${cinemaSession.filmName}"/>&quot;</h3>
    <h3>Вы выбрали <c:out value="${row}"/> ряд <c:out value="${col}"/> место. К оплате : 500 рублей.</h3>
</div>
<div class="row">
    <div class="col-12">
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

<c:import url="../template/layouts/pageFooter.jsp" />
