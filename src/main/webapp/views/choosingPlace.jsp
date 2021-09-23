<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:set var="pageTitle" value="Зал кинотеатра" />
<c:set var="pageScript" value="hall.js" />
<%@ include file="../template/layouts/pageHeader.jsp" %>

<%
    int totalCols = (Integer) request.getAttribute("cols");
    int totalRows = (Integer) request.getAttribute("rows");
%>
<div class="row">
    <div class="col-12">
        <h4 class="text-center">Бронирование места на фильм &quot;<c:out value="${cinemaSession.filmName}"/>&quot;</h4>
        <table id="hall" class="table table-bordered">
            <thead>
            <tr>
                <th>Ряд №</th>
                <th colspan="<%=totalCols%>">Место</th>
            </tr>
            </thead>
            <tbody>
            <% for (int i = 1; i <= totalRows; i++) { %>
            <tr>
                <th><%=i%></th>
                <% for (int j = 1; j <= totalCols; j++) { %>
                <td id="cell-r<%=i%>c<%=j%>"><%=j%></td>
                <% } %>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<div class="row">
    <div class="col-12">
        <form id="choose-place" action="<c:url value="/place.do"/>" method="post">
            <input name="nSession" id="iSession" type="hidden" value="<c:out value="${cinemaSession.id}"/>"/>
            <input name="nRow" id="iRow" type="hidden" value=""/>
            <input name="nCol" id="iCol" type="hidden" value=""/>
            <button id="btn-submit" type="submit" class="btn btn-primary pull-right">Забронировать</button>
        </form>
    </div>
</div>

<%@ include file="../template/layouts/pageFooter.jsp" %>