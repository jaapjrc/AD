import java.sql.*;

public class MiClaseH2 {

    private Connection myConnection;

   public  MiClaseH2(){
       try {
           myConnection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
   }
    public void crearTabla() throws SQLException {
        try {
            Statement statement = myConnection.createStatement();
            String textoSQL="CREATE TABLE IF NOT EXISTS libros (id int(3) primary key, titulo varchar(60), autor varchar(40));";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }
    public void crearTablaFK() throws SQLException {
        try {
            Statement statement = myConnection.createStatement();
            String textoSQL="CREATE TABLE IF NOT EXISTS libros (id int(3) primary key, titulo varchar(60), autor varchar(40), idEditorial varchar(40) " +
                    "CONSTRAINT FK_Editorial FOREIGN KEY (idEditorial) REFERENCES Editoriales(id));";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }


    public void eliminarTabla(String tabla) throws SQLException {
        try {
            Statement statement = myConnection.createStatement();
            String textoSQL="DROP TABLE " + tabla + ";";
            statement.execute(textoSQL);
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void insertarRegistro() throws SQLException{
        try  {

            String textoSQL="insert into libros (id, titulo, autor) values (?, ?, ?)";

            PreparedStatement preparedStatement = myConnection.prepareStatement(textoSQL);
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Panza de burro");
            preparedStatement.setString(3, "Andrea Abreu");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }
    }

    public void leerRegistroId(int id) throws SQLException{
        try{
            String textoSQL="select * from libros where id=?";

            PreparedStatement preparedStatement= myConnection.prepareStatement(textoSQL);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                System.out.println("Libro:" +  id + "," + titulo + "," + autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
