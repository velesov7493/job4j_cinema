<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<c:import url="../template/layouts/pageHeader.jsp">
    <c:param name="pageTitle" value="Зал кинотеатра" />
</c:import>
<%
    int totalCols = (Integer) request.getAttribute("cols");
    int totalRows = (Integer) request.getAttribute("rows");
%>
<div class="row">
    <h4>Бронирование места на сеанс <c:out value="${session.filmName}"/></h4>
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
            <td><%=j%></td>
            <% } %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<c:import url="../template/layouts/pageFooter.jsp" />