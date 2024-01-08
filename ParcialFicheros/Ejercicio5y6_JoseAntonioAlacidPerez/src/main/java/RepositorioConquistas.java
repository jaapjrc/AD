import java.sql.*;
import java.util.LinkedList;

public class RepositorioConquistas {
    Connection conexion;

    public RepositorioConquistas(Connection miConexion) {
        this.conexion = miConexion;
        createTable();
    }

    public void createTable(){
        Statement stmt=null;
        try {
            stmt = conexion.createStatement();
            String CREATE_TABLE_SQL="CREATE TABLE IF NOT EXISTS conquistas (" +
                    "id               INTEGER PRIMARY KEY AUTO_INCREMENT,\n" +
                    "territorio    VARCHAR(50)," +
                    "fecha_conquista     DATE," +
                    "antiguo_poseedor     VARCHAR(50)," +
                    "descripcion_conquista    VARCHAR(50));";
            stmt.executeUpdate(CREATE_TABLE_SQL);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public void listarTodos(){
        LinkedList<Conquista> lista=null;
        try {
            PreparedStatement ps=conexion.prepareStatement("SELECT * FROM conquistas");
            ResultSet rs=ps.executeQuery();
            lista= new LinkedList<Conquista>();
            while(rs.next()){
                Conquista aux=new Conquista();
                aux.setId(rs.getInt("id"));
                aux.setTerritorio(rs.getString("territorio"));
                aux.setFechaConquista(rs.getDate("fecha_conquista").toLocalDate());
                aux.setAntiguoPoseedor(rs.getString("antiguo_poseedor"));
                aux.setDescripcion(rs.getString("descripcion_conquista"));
                lista.add(aux);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lista.forEach((conquista) -> System.out.println(conquista.toString()));

    }

    public void inserta(Conquista a){
        PreparedStatement sentencia = null;
        String sentenciaSql = "INSERT INTO conquistas (territorio, fecha_conquista, antiguo_poseedor, descripcion_conquista) VALUES (?, ?, ?, ?)";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);
            sentencia.setString(1, a.getTerritorio());
            sentencia.setDate(2, Date.valueOf(a.getFechaConquista()));
            sentencia.setString(3, a.getAntiguoPoseedor());
            sentencia.setString(4, a.getDescripcion());
            sentencia.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void modificar(Conquista a){
        PreparedStatement sentencia = null;

        String sentenciaSQL = "UPDATE conquistas SET territorio=?, fecha_conquista=?, antiguo_poseedor=?, descripcion_conquista=? WHERE id = ?";

        try {
            sentencia = conexion.prepareStatement(sentenciaSQL);
            sentencia.setString(1, a.getTerritorio());
            sentencia.setDate(2, Date.valueOf(a.getFechaConquista()));
            sentencia.setString(3, a.getAntiguoPoseedor());
            sentencia.setString(4, a.getDescripcion());
            sentencia.setInt(5, a.getId());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        PreparedStatement sentencia = null;
        String sentenciaSql = "DELETE FROM conquistas WHERE id=?";
        try {
            sentencia = conexion.prepareStatement(sentenciaSql);

            sentencia.setInt(1, id);

            sentencia.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
