<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Access Denied</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
 
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Acesso negado!</div>
  
   <h3 style="color:red;">Desculpe, você não pode acessar está página!</h3>
  
  
   <jsp:include page="_footer.jsp" />
 
</body>
</html>