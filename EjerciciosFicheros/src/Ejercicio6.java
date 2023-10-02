import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Ejercicio6 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("diario.txt"));
            String linea = br.readLine();
            BufferedReader brb = new BufferedReader(new FileReader("diario-backup.txt"));
            String lineab = brb.readLine();
            boolean igual = true;
            while (linea != null && lineab != null) {
                if (!linea.equals(lineab)) {
                    igual = false;
                    System.out.println("El diario y su backup son distintos.");
                }
                linea = br.readLine();
                lineab = brb.readLine();
            }
            if (igual) {
                System.out.println("El diario y su backup son iguales.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
