<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product List</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <fmt:setLocale value="en_US" scope="session"/>
  
   <div class="page-title">Lista de Pedidos</div>
 
   <div>Contagem total do pedidos: ${paginationResult.totalRecords}</div>
  
   <table border="1" style="width:100%">
       <tr>
           <th>Pedido Numero</th>
           <th>Pedido Data</th>
           <th>Cliente Nome</th>
           <th>Cliente Endereço</th>
           <th>Cliente Email</th>
           <th>Montante</th>
           <th>Mostrar</th>
       </tr>
       <c:forEach items="${paginationResult.list}" var="pedidoInfo">
           <tr>
               <td>${pedidoInfo.pedidoNum}</td>
               <td>
                  <fmt:formatDate value="${pedidoInfo.pedidoData}" pattern="dd-MM-yyyy HH:mm"/>
               </td>
               <td>${pedidoInfo.clienteNome}</td>
               <td>${pedidoInfo.clienteEndereco}</td>
               <td>${pedidoInfo.clienteEmail}</td>
               <td style="color:red;">
                  <fmt:formatNumber value="${pedidoInfo.montante}" type="currency"/>
               </td>
               <td><a href="${pageContext.request.contextPath}/pedido?pedidoId=${pedidoInfo.id}">
                  View</a></td>
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