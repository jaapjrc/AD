package com.company;
import java.io.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

/* Ejercicios
    1-Modifica el programa para que al "empezar" un juego muestre el mensaje juego en mayúsculas
 */

public class Ej07XMLSAX {
    public static void main(String[] args) throws FileNotFoundException, IOException, SAXException{
        XMLReader  procesadorXML = XMLReaderFactory.createXMLReader();
        GestionContenido gestor= new GestionContenido();
        procesadorXML.setContentHandler(gestor);
        InputSource fileXML = new InputSource("juegos.xml");
        procesadorXML.parse(fileXML);
    }
}//fin PruebaSax1

class GestionContenido extends DefaultHandler {
    public GestionContenido() {
        super();
    }
    public void startDocument() {
        System.out.println("Comienzo del Documento XML");
    }
    public void endDocument() {
        System.out.println("Final del Documento XML");
    }

    public void startElement(String uri, String nombre, String nombreC, Attributes atts) {
        // Ejercicio 1
        if (nombre.equals("juego")) {
            System.out.printf("\tElemento: %s %n", nombre.toUpperCase());
        }else {
            System.out.printf("\tElemento: %s %n", nombre);
        }
    }
    public void endElement(String uri, String nombre, String nombreC) {
        System.out.printf("\tFin Elemento: %s %n", nombre);
    }
    public void characters(char[] ch, int inicio, int longitud) throws SAXException {
        String car=new String(ch, inicio, longitud);   //quitar saltos de línea
        car = car.replaceAll("[\t\n]","");
        System.out.printf ("\t Valor: %s %n", car);
    }

}//fin GestionContenido
