jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Expression Evaluator</title>
</head>
<body>
<h1>Evaluate Expression</h1>
<form method="post">
    <label for="expression">Enter an expression:</label>
    <input type="text" id="expression" name="expression" />
    <button type="submit">Evaluate</button>
</form>

<% if (request.getMethod().equalsIgnoreCase("POST")) { %>
    <% String userExpression = request.getParameter("expression"); %>
    <c:if test="${not empty userExpression}">
        <!-- Attempt to evaluate the expression -->
        <c:catch var="evaluationException">
            <c:set var="result" value="<%= javax.script.ScriptEngineManager().getEngineByName(\"JavaScript\").eval(userExpression) %>" />
            <p>Result: ${result}</p>
        </c:catch>
        <c:if test="${not empty evaluationException}">
            <p style="color: red;">Error evaluating expression: ${evaluationException.message}</p>
        </c:if>
    </c:if>
<% } %>
</body>
</html>