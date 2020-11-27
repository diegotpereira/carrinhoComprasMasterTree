<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produto</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
 
   <jsp:include page="_header.jsp" />
   <jsp:include page="_menu.jsp" />
 
   <div class="page-title">Produto</div>
  
   <c:if test="${not empty errorMessage }">
     <div class="error-message">
         ${errorMessage}
     </div>
   </c:if>
 
   <form:form modelAttribute="produtoForm" method="POST" enctype="multipart/form-data">
       <table style="text-align:left;">
           <tr>
               <td>Codigo *</td>
               <td style="color:red;">
                  <c:if test="${not empty produtoForm.codigo}">
                       <form:hidden path="codigo"/>
                       ${produtoForm.codigo}
                  </c:if>
                  <c:if test="${empty produtoForm.codigo}">
                       <form:input path="codigo" />
                       <form:hidden path="novoProduto" />
                  </c:if>
               </td>
               <td><form:errors path="codigo" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Nome *</td>
               <td><form:input path="nome" /></td>
               <td><form:errors path="nome" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Preço *</td>
               <td><form:input path="preco" /></td>
               <td><form:errors path="preco" class="error-message" /></td>
           </tr>
           <tr>
               <td>Image</td>
               <td>
               <img src="${pageContext.request.contextPath}/produtoImage?codigo=${produtoForm.codigo}" width="100"/></td>
               <td> </td>
           </tr>
<!--            <tr> -->
<!--                <td>Upload Image</td> -->
<%--                <td><form:input type="file" path="fileData"/></td> --%>
<!--                <td> </td> -->
<!--            </tr> -->
 
 
           <tr>
               <td>&nbsp;</td>
               <td><input type="submit" value="Submit" /> <input type="reset"
                   value="Reset" /></td>
           </tr>
       </table>
   </form:form>
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>