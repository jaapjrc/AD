import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class MainAlumno {

    public static void escribirXML(ArrayList<Alumno> listaAlumno) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null, "xml", null);

            Element raiz = documento.createElement("Alumnos");
            documento.getDocumentElement().appendChild(raiz);
            Element nodoAlumno = null, nodoDatos = null;
            Text texto = null;
            for (Alumno alumno : listaAlumno) {
                nodoAlumno = documento.createElement("Alumno");
                raiz.appendChild(nodoAlumno);
                nodoDatos = documento.createElement("nombre");
                nodoAlumno.appendChild(nodoDatos);
                texto = documento.createTextNode(alumno.getNombre());
                nodoDatos.appendChild(texto);
                nodoDatos = documento.createElement("nota");
                nodoAlumno.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(alumno.getNota()));
                nodoDatos.appendChild(texto);
            }
            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("archivo.xml"));
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, resultado);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException tce) {
            tce.printStackTrace();
        } catch (TransformerException te) {
            te.printStackTrace();
        }
    }

    public static void leerXML(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(new File("archivo.xml"));

            NodeList alumnos = documento.getElementsByTagName("Alumno");

            for (int i = 0; i < alumnos.getLength(); i++) {
                Node alumno = alumnos.item(i);
                Element elemento = (Element) alumno;

                System.out.println(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
                System.out.println(elemento.getElementsByTagName("nota").item(0).getChildNodes().item(0).getNodeValue());
                System.out.println("--------------------------------------------------------");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ArrayList<Alumno> lista = new ArrayList<>();
        lista.add(new Alumno("Jose", 9));
        lista.add(new Alumno("Gabriel", 9.50));
        lista.add(new Alumno("Dani", 9));
        lista.add(new Alumno("Javi", 8));
        lista.add(new Alumno("Andrea", 7.50));

        escribirXML(lista);
        leerXML();

    }
}
