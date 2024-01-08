import java.time.LocalDate;

public class Prueba {

    private static Conexion conexion;

    private static RepositorioConquistas repositorioConquistas;

    public static void main(String[] args) {
        conexion = new Conexion();

        repositorioConquistas = new RepositorioConquistas(conexion.conexion);

        Conquista conquista = new Conquista();
        conquista.setTerritorio("Lille");
        conquista.setFechaConquista(LocalDate.of(2023,10,22));
        conquista.setAntiguoPoseedor("Francia");
        conquista.setDescripcion("Easy");

        repositorioConquistas.inserta(conquista);
        System.out.println("Conquista insertada");

        repositorioConquistas.listarTodos();

        conquista.setId(3);
        conquista.setDescripcion("GG EZ");
        repositorioConquistas.modificar(conquista);
        System.out.println("Conquista modificada");

        repositorioConquistas.listarTodos();

        repositorioConquistas.delete(1);
        System.out.println("Conquista eliminada");

        repositorioConquistas.listarTodos();



    }
}
