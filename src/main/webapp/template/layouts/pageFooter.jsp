</div>
<c:if test="${not empty pageScript}">
    <script src="<%=request.getContextPath()%>/template/js/<c:out value="${pageScript}"/>"></script>
</c:if>
</body>
</html>