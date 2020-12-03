<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Finalizar carrinho de compras</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
   <jsp:include page="_header.jsp" />
 
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Finalizar</div>
  
   <div class="container">
       <h3>Obrigado pelo pedido</h3>
       O número do seu pedido é: ${ultimoCarrinhoPedido.pedidoNum}
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>