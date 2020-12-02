<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Carrinho de Compras</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
   <jsp:include page="_header.jsp" />
  
   <jsp:include page="_menu.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Meu Carrinho</div>
 
   <c:if test="${empty carrinhoForm or empty carrinhoForm.carrinhoLinhas}">
       <h2>Não há itens no carrinho</h2>
       <a href="${pageContext.request.contextPath}/listaProduto">Mostrar Lista de Produtos</a>
   </c:if>
 
   <c:if test="${not empty carrinhoForm and not empty carrinhoForm.carrinhoLinhas   }">
       <form:form method="POST" modelAttribute="carrinhoForm"
           action="${pageContext.request.contextPath}/carrinhoCompras">
 
           <c:forEach items="${carrinhoForm.carrinhoLinhas}" var="CarrinhoLinhaInfo"
               varStatus="varStatus">
               <div class="product-preview-container">
                   <ul>
                       <li><img class="product-image"
                           src="${pageContext.request.contextPath}/produtoImage?code=${CarrinhoLinhaInfo.produtoInfo.codigo}" />
                       </li>
                       <li>Code: ${CarrinhoLinhaInfo.produtoInfo.codigo} <form:hidden
                               path="carrinhoLinhas[${varStatus.index}].produtoInfo.codigo" />
 
                       </li>
                       <li>Nome: ${CarrinhoLinhaInfo.produtoInfo.nome}</li>
                       <li>Preço: <span class="preco">
                      
                         <fmt:formatNumber value="${CarrinhoLinhaInfo.produtoInfo.preco}" type="currency"/>
                        
                       </span></li>
                       <li>Quantidade: <form:input
                               path="carrinhoLinhas[${varStatus.index}].quantidade" /></li>
                       <li>Subtotal:
                         <span class="subtotal">
                        
                            <fmt:formatNumber value="${CarrinhoLinhaInfo.montante}" type="currency"/>
                      
                         </span>
                       </li>
                       <li><a
                           href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?code=${CarrinhoLinhaInfo.produtoInfo.codigo}">
                               Deletar </a></li>
                   </ul>
               </div>
           </c:forEach>
           <div style="clear: both"></div>
           <input class="button-update-sc" type="submit" value="Alterar Quantidade" />
           <a class="navi-item"
               href="${pageContext.request.contextPath}/carrinhoComprasCliente">Entre Com Informação do Cliente</a>
           <a class="navi-item"
               href="${pageContext.request.contextPath}/listaProduto">Continuar
               Compra</a>
       </form:form>
 
 
   </c:if>
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>