<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de Produtos</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
    
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="page-title">Informação de Pedido</div>
 
   <div class="customer-info-container">
       <h3>Informação do Cliente:</h3>
       <ul>
           <li>Nome: ${pedidoInfo.clienteNome}</li>
           <li>Email: ${pedidoInfo.clienteEmail}</li>
           <li>Telefone: ${pedidoInfo.clienteTelefone}</li>
           <li>Endereço: ${pedidoInfo.clienteEndereco}</li>
       </ul>
       <h3>Resumo do Pedido:</h3>
       <ul>
           <li>Total:
           <span class="total">
           <fmt:formatNumber value="${pedidoInfo.montante}" type="currency"/>
           </span></li>
       </ul>
   </div>
    
   <br/>
    
   <table border="1" style="width:100%">
       <tr>
           <th>Produto Código</th>
           <th>Produto Nome</th>
           <th>Quantidade</th>
           <th>Preço</th>
           <th>Montante</th>
       </tr>
       <c:forEach items="${pedidoInfo.detalhe}" var="pedidoDetalheInfo">
           <tr>
               <td>${pedidoDetalheInfo.produtoCodigo}</td>
               <td>${pedidoDetalheInfo.produtoNome}</td>
               <td>${pedidoDetalheInfo.quantidade}</td>
               <td>
                <fmt:formatNumber value="${pedidoDetalheInfo.preco}" type="currency"/>
               </td>
               <td>
                <fmt:formatNumber value="${pedidoDetalheInfo.montante}" type="currency"/>
               </td>  
           </tr>
       </c:forEach>
   </table>
   <c:if test="${paginationResult.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationResult.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="pedidoLista?page=${page}" class="nav-item">${page}</a>
              </c:if>
              <c:if test="${page == -1 }">
                <span class="nav-item"> ... </span>
              </c:if>
          </c:forEach>
            
       </div>
   </c:if>
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>