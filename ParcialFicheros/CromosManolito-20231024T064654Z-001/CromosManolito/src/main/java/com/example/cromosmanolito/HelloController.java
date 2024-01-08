package com.example.cromosmanolito;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

public class HelloController {
    @FXML
    TextField txtJugador, txtSeleccion, txtCantidad;
    // Ejercicio 2
    public void guardar(){
            FileWriter fichero = null;
            PrintWriter escritor = null;

            try {
                fichero = new FileWriter("cromos.csv", true);
                escritor = new PrintWriter(fichero);
                escritor.println(txtJugador.getText() + ";" + txtSeleccion.getText() + ";" + txtCantidad.getText());
                escritor.close();
                fichero.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    // Ejercicio 3
    public void guardarXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null,  "xml", null);

            Element raiz = documento.createElement("Cromos");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoProducto = null, nodoDatos = null;
            Text texto = null;

                nodoProducto = documento.createElement("Cromo");
                raiz.appendChild(nodoProducto);

                nodoDatos = documento.createElement("jugador");
                nodoProducto.appendChild(nodoDatos);

                texto = documento.createTextNode(txtJugador.getText());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("seleccion");
                nodoProducto.appendChild(nodoDatos);

                texto = documento.createTextNode(txtSeleccion.getText());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("cantidad");
                nodoProducto.appendChild(nodoDatos);

                texto = documento.createTextNode(txtCantidad.getText());
                nodoDatos.appendChild(texto);

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("cromos.xml"));

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
}