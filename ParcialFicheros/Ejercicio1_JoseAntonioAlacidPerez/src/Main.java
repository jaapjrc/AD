import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {
        String linea = JOptionPane.showInputDialog("Escribe algo");
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("diarioDeJohnDifool.txt", true));
            pw.println(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            pw.println(linea);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

