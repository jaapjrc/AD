import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Conquista {

    private int id;
    private String territorio;
    private LocalDate fechaConquista;
    private String antiguoPoseedor;
    private String descripcion;

    public Conquista() {
    }

    public Conquista(int id, String territorio, LocalDate fechaConquista, String antiguoPoseedor, String descripcion) {
        this.id = id;
        this.territorio = territorio;
        this.fechaConquista = fechaConquista;
        this.antiguoPoseedor = antiguoPoseedor;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerritorio() {
        return territorio;
    }

    public void setTerritorio(String territorio) {
        this.territorio = territorio;
    }

    public LocalDate getFechaConquista() {
        return fechaConquista;
    }

    public void setFechaConquista(LocalDate fechaConquista) {
        this.fechaConquista = fechaConquista;
    }

    public String getAntiguoPoseedor() {
        return antiguoPoseedor;
    }

    public void setAntiguoPoseedor(String antiguoPoseedor) {
        this.antiguoPoseedor = antiguoPoseedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Conquista{" +
                "id=" + id +
                ", territorio='" + territorio + '\'' +
                ", fechaConquista=" + fechaConquista.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ", antiguoPoseedor='" + antiguoPoseedor + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
