import javax.persistence.*;

@Entity
@Table(name = "editoriales")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    int id;
    @Column(name="editorial")
    String editorial;
    @Column(name="pais")
    String pais;

    public Editorial() {
    }

    //En el constructor no es necesario poner el id, se autogenera
    public Editorial(String editorial, String pais) {
        this.editorial = editorial;
        this.pais = pais;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Editorial{" +
                "id=" + id +
                ", editorial='" + editorial + '\'' +
                ", pais='" + pais + '\'' +
                '}';
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
