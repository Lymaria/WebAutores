package autores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionBD {

	private static final String URL_BD = "jdbc:mysql://localhost:3306/libros";
	private static final String CONTRASENNA = "";
	private static final String USUARIO = "root";
	private static Connection conexion;

	public static void abrirConexion() throws SQLException {

		conexion = DriverManager.getConnection(URL_BD, USUARIO, CONTRASENNA);

	}

	public static void cerrarConexion() throws SQLException {

		conexion.close();

	}

	public static int insertarAutor(String nombreAutor, String nac, String fnacAutor) throws SQLException {

		int filasAfectadas;
		Statement sentencia = conexion.createStatement();

		filasAfectadas = sentencia.executeUpdate("INSERT INTO autor (Nombre, Nacionalidad, Fnacimiento) VALUES  " + "('"
				+ nombreAutor + "', '" + nac + "', '" + fnacAutor + "')");

		return filasAfectadas;

	}

	public static int insertarLibro(int isbn, String editorial, String titulo, String idioma, int id_autor, String tematica)
			throws SQLException {

		int filasAfectadas;

		Statement sentencia = conexion.createStatement();
		filasAfectadas = sentencia.executeUpdate("INSERT INTO libro VALUES " + "('" + isbn + "', '" + editorial + "', '"
				+ titulo + "', '" + idioma + "', " + id_autor + ", '" + tematica + "')");

		return filasAfectadas;
	}

	public static ResultSet consultarAutores() throws SQLException {

		ResultSet resultadoQuery;
		Statement sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery("select * from autor");

		return resultadoQuery;
	}

	public static ResultSet consultarLibros() throws SQLException {

		ResultSet resultadoQuery;
		Statement sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery(
				"select L.Isbn, L.Editorial, L.Titulo, L.Idioma, A.Nombre, T.Nombre from libro L INNER JOIN autor A ON L.Id_Autor = A.Id_Autor"
				+ " INNER JOIN tematicas T on L.Id_Tematica=T.Id_Tematica");

		return resultadoQuery;

	}

	public static ResultSet consultarAutoresPorNombre(String nombre) throws SQLException {

		ResultSet resultadoQuery;
		Statement sentencia = conexion.createStatement();
		resultadoQuery = sentencia.executeQuery("select * from autor WHERE Nombre LIKE '%" + nombre + "%'");

		return resultadoQuery;
	}
	
	public static ResultSet consultarTematica(String nombre) throws SQLException {
		ResultSet resul;
		Statement sentencia= conexion.createStatement();
		resul= sentencia.executeQuery("select * from tematicas where nombre like '%" + nombre + "%'");
		
		return resul;
	}
	
	public static boolean existeLibro(int isbn) throws SQLException {
		boolean existe= false;
		Statement sentencia= conexion.createStatement();
		ResultSet resul;
		
		resul= sentencia.executeQuery("select *  from libro where isbn= " + isbn);
		
		if (resul.next()) {
			existe= true;
		}
		
				
		return existe;
	}
	public static boolean existeTema(int id) throws SQLException {
		boolean existe= false;
		Statement sentencia= conexion.createStatement();
		ResultSet resul;
		
		resul= sentencia.executeQuery("select *  from tematica where ID_Tematica '% " + id);
		
		if (resul.next()) {
			existe= true;
		}
		
				
		return existe;
	}
	
	public static int modificarTematica(int isbn, int id) throws SQLException {
		int fila=0;
		String sql;
		Statement sentencia= conexion.createStatement();
		boolean existe= existeLibro(isbn);
		boolean hayTema= existeTema(id);
		
		
		if (existe && hayTema) {
			sql= "update libro set isbn= " + isbn + "where Id_tematica = " + id;
			fila= sentencia.executeUpdate(sql);
		}
		return fila;
	}
	
	public static int borrarLibro(int isbn) throws SQLException {
		int fila=0;
		String sql;
		Statement sentencia= conexion.createStatement();
		boolean existe= existeLibro(isbn);
		
		if(existe) {
			sql= "delete from libro where isbn=" + isbn;
			fila= sentencia.executeUpdate(sql);
		}
		return fila;
	}
}
