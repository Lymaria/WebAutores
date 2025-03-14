<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="autores.GestionBD"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Resultado</title>
<link rel="stylesheet" href="estilo.css">
</head>
<body>
	<div class="parent">
		<div class="div1">
			<img src="logo.png" width="100" height="100">
		</div>
		<div class="div2">Resultado de la consulta</div>
		<div class="div3">
			<button>
				<a href="insertarAutor.html">Nuevo autor</a>
			</button>
			<br>
			<br>
			<button>
				<a href="listadoAutores.jsp">Listar autores</a>
			</button>
		</div>
		<div class="div4">
			<%!
			String nombre, nacionalidad, fNacimiento;
			%>
		    <%
			nombre = request.getParameter("Nombre");
			nacionalidad = request.getParameter("Nacionalidad");
			fNacimiento = request.getParameter("FNacimiento");

			GestionBD.abrirConexion();
			GestionBD.insertarAutor(nombre, nacionalidad, fNacimiento);
			GestionBD.cerrarConexion();
			%>

			<h1>El autor ha sido insertado correctamente</h1>

			<button>
				<a href="index.html">Volver</a>
			</button>
		</div>
	</div>



</body>
</html>