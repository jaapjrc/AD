public class Libro {

    private int id;

    private String titulo;

    private String autor;

    private int idEditorial;

    public Libro() {
    }

    public Libro(int id, String titulo, String autor, int idEditorial) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idEditorial = idEditorial;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getIdEditorial() {
        return idEditorial;
    }

    public void setIdEditorial(int idEditorial) {
        this.idEditorial = idEditorial;
    }
}
