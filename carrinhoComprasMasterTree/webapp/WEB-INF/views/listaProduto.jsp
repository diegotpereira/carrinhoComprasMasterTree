<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

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
 
   <div class="page-title">Lista de Produtos</div>
 
 
 
   <c:forEach items="${paginationProducts.list}" var="prodInfo">
       <div class="product-preview-container">
           <ul>
               <li><img class="product-image"
                   src="${pageContext.request.contextPath}/produtoImage?codigo=${prodInfo.codigo}" /></li>
               <li>Code: ${prodInfo.codigo}</li>
               <li>Name: ${prodInfo.nome}</li>
               <li>Price: <fmt:formatNumber value="${prodInfo.preco}" type="currency"/></li>
               <li><a
                   href="${pageContext.request.contextPath}/buyProduct?codigo=${prodInfo.codigo}">
                       Buy Now</a></li>
               <!-- For Manager edit Product -->
               <security:authorize  access="hasRole('ROLE_MANAGER')">
                 <li><a style="color:red;"
                     href="${pageContext.request.contextPath}/produto?codigo=${prodInfo.codigo}">
                       Edit Product</a></li>
               </security:authorize>
           </ul>
       </div>
 
   </c:forEach>
   <br/>
  
 
   <c:if test="${paginationProducts.totalPages > 1}">
       <div class="page-navigator">
          <c:forEach items="${paginationProducts.navigationPages}" var = "page">
              <c:if test="${page != -1 }">
                <a href="listaProduto?page=${page}" class="nav-item">${page}</a>
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