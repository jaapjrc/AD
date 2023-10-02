import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;

public class Ejercicio1 {
    public static void main(String[] args) {
        LocalDate fecha = LocalDate.now();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("fecha.txt"));
            pw.println(fecha);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
