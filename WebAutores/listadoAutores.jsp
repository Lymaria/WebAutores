<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="autores.GestionBD"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listado de Autores</title>
<link rel="stylesheet" href="estilo.css">
</head>
<body>
	<div class="parent">
		<div class="div1">
			<img src="logo.png" width="100" height="100">
		</div>
		<div class="div2">Listado de autores</div>
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
			<%
			GestionBD.abrirConexion();
			ResultSet listado = GestionBD.consultarAutores();
			%>
			<table>
				<tr>
					<th>Nombre</th>
					<th>Nacionalidad</th>
					<th>Fecha de nacimiento</th>
				</tr>
				<%
				while (listado.next()) {
				%>
				<tr>
					<td><%=listado.getString("nombre")%></td>
					<td><%=listado.getString("nacionalidad")%></td>
					<td><%=listado.getString("fNacimiento")%></td>
				</tr>

				<%
				}
				GestionBD.cerrarConexion();
				%>
			</table>
			<button>
				<a href="index.html">Volver</a>
			</button>
		</div>
	</div>

</body>
</html>