<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Enter Customer Information</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
<jsp:include page="_header.jsp" />
<jsp:include page="_menu.jsp" />
 
<div class="page-title">Entre Com Informação do Cliente</div>
 
   <form:form method="POST" modelAttribute="clienteForm"
       action="${pageContext.request.contextPath}/carrinhoComprasCliente">
 
       <table>
           <tr>
               <td>Nome *</td>
               <td><form:input path="nome" /></td>
               <td><form:errors path="nome" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Email *</td>
               <td><form:input path="email" /></td>
               <td><form:errors path="email" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Telefone *</td>
               <td><form:input path="telefone" /></td>
               <td><form:errors path="telefone" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Endereço *</td>
               <td><form:input path="endereco" /></td>
               <td><form:errors path="endereco" class="error-message" /></td>
           </tr>
 
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