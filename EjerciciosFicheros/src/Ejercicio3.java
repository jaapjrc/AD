import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Ejercicio3 {
    public static void main(String[] args) {
        String linea = JOptionPane.showInputDialog("Escribe algo");
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("diario.txt", true));
            pw.println(LocalDateTime.now() + " - " + linea);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
