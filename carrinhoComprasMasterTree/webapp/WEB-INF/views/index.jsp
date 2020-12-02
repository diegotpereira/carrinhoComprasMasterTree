<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Loja de livros online</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
 
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Carrinho de Compras Demo</div>
  
   <div class="demo-container">
   <h3>Conteúdo da Aplicação</h3>
  
   <ul>
      <li>Venda online</li>
      <li>Página de administração</li>
      <li>Relatórios</li>
   </ul>
   </div>
  
  
   <jsp:include page="_footer.jsp" />
 
</body>
</html>