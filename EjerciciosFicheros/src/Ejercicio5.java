import java.io.*;

public class Ejercicio5 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("diario.txt"));
            String linea = br.readLine();
            PrintWriter pw = new PrintWriter(new FileWriter("diario-backup.txt"));
            while (linea != null) {
                pw.println(linea);
                linea = br.readLine();
            }
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
