<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Page</title>
</head>
<body>
<div>
<table>
    <tr>
        <th>id</th>
        <th>title</th>
        <th>writer</th>
        <th>date</th>
        <th>hits</th>
    </tr>
    <c:forEach items="${dialogList}" var="board">
        <tr>
            <td>${board.id}</td>
            <td>
                <a href="/board?id=${board.id}&page=${paging.page}">${board.boardTitle}</a>
            </td>
            <td>${board.boardWriter}</td>
            <td>${board.boardCreatedTime}</td>
            <td>${board.boardHits}</td>
        </tr>
    </c:forEach>
</table>
</div>
<div>
    <c:choose>
        <c:when test="${pager.page <= 1}">
            <a>[이전]</a>
        </c:when>
        <c:otherwise>
            <a href="/board/paging?page=${pager.page - 1}">[이전]</a>
        </c:otherwise>
    </c:choose>

    <c:forEach begin="${pager.startPage}" end="${pager.endPage}" var="i" step="1">
        <c:choose>
            <c:when test="${i eq pager.page}">
                <a>${i}</a>
            </c:when>
            <c:otherwise>
                <a href="/board/paging?page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${pager.page >= pager.maxPage}">
            <a>[다음]</a>
        </c:when>
        <c:otherwise>
            <a href="/board/paging?page=${pager.page + 1}">[다음]</a>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>