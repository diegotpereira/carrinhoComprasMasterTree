<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Confirmação do Carrinho Compras</title>
 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css">
 
</head>
<body>
  <jsp:include page="_header.jsp" />
 
  <jsp:include page="_menu.jsp" />
 
  <fmt:setLocale value="en_US" scope="session"/>
 
  <div class="page-title">Confirmação</div>
 
 
 
  <div class="customer-info-container">
      <h3>Informação do Cliente:</h3>
      <ul>
          <li>Nome: ${meuCarrinho.clienteInfo.nome}</li>
          <li>Email: ${meuCarrinho.clienteInfo.email}</li>
          <li>Telefone: ${meuCarrinho.clienteInfo.telefone}</li>
          <li>Endereço: ${meuCarrinho.clienteInfo.endereco}</li>
      </ul>
      <h3>Resumo do Carrinho:</h3>
      <ul>
          <li>Quantidade: ${meuCarrinho.quantidadeTotal}</li>
          <li>Total:
          <span class="total">
            <fmt:formatNumber value="${meuCarrinho.montanteTotal}" type="currency"/>
          </span></li>
      </ul>
  </div>
 
  <form method="POST"
      action="${pageContext.request.contextPath}/carrinhoComprasConfirmacao">
 
      <!-- Edit Cart -->
      <a class="navi-item"
          href="${pageContext.request.contextPath}/carrinhoCompras">Editar Carrinho</a>
 
      <!-- Edit Customer Info -->
      <a class="navi-item"
          href="${pageContext.request.contextPath}/carrinhoComprasCliente">Editar Informação do Cliente</a>
 
      <!-- Send/Save -->
      <input type="submit" value="Enviar" class="button-send-sc" />
  </form>
 
  <div class="container">
 
      <c:forEach items="${meuCarrinho.carrinhoLinhas}" var="CarrinhoLinhaInfo">
          <div class="product-preview-container">
              <ul>
                  <li><img class="product-image"
                      src="${pageContext.request.contextPath}/produtoImage?codigo=${carrinhoLinhaInfo.produtoInfo.codigo}" /></li>
                  <li>Codigo: ${carrinhoLinhaInfo.produtoInfo.codigo} <input
                      type="hidden" name="codigo" value="${carrinhoLinhaInfo.produtoInfo.codigo}" />
                  </li>
                  <li>Nome: ${carrinhoLinhaInfo.produtoInfo.nome}</li>
                  <li>Preço: <span class="preco">
                     <fmt:formatNumber value="${carrinhoLinhaInfo.produtoInfo.preco}" type="currency"/>
                  </span>
                  </li>
                  <li>Quantidade: ${carrinhoLinhaInfo.quantidade}</li>
                  <li>Subtotal:
                    <span class="subtotal">
                       <fmt:formatNumber value="${carrinhoLinhaInfo.montante}" type="currency"/>
                    </span>
                  </li>
              </ul>
          </div>
      </c:forEach>
 
  </div>
 
  <jsp:include page="_footer.jsp" />
 
</body>
</html>