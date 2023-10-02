package com.company;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ej06XMLDOM {
    public static void main(String[] args){
        ArrayList<Juego> lista=new ArrayList<>();
        lista.add(new Juego("Ghost & Goblins", "arcade", 1983));
        lista.add(new Juego("Doom", "Shootemup", 1993));

        escribirXML(lista);
        leerFicheroXML();
    }

    public static void escribirXML(ArrayList<Juego> listaJuegos) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null,  "xml", null);
            Element raiz = documento.createElement("juegos");
            documento.getDocumentElement().appendChild(raiz);
            Element nodoJuego = null, nodoTitulo = null, nodoGenero=null, nodoAnyo=null;
            Text texto = null;

            for (Juego juego : listaJuegos) {
                nodoJuego = documento.createElement("juego");
                raiz.appendChild(nodoJuego);
                nodoTitulo = documento.createElement("titulo");
                nodoJuego.appendChild(nodoTitulo);
                texto = documento.createTextNode(juego.getTitulo());
                nodoTitulo.appendChild(texto);
                nodoGenero = documento.createElement("genero");
                nodoJuego.appendChild(nodoGenero);
                texto = documento.createTextNode(String.valueOf(juego.getGenero()));
                nodoGenero.appendChild(texto);
                nodoAnyo = documento.createElement("año");
                nodoJuego.appendChild(nodoAnyo);
                texto = documento.createTextNode(String.valueOf(juego.getAnyo()));
                nodoAnyo.appendChild(texto);
            }

            Source source = new DOMSource(documento);
            Result resultado = new StreamResult(new File("juegos.xml"));

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

    public static void leerFicheroXML() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(new File("juegos.xml"));
            // Recorre cada uno de los nodos 'Persona'
            NodeList juegos = documento.getElementsByTagName("juego");
            for (int i = 0; i < juegos.getLength(); i++) {
                Node juego = juegos.item(i);
                Element elemento = (Element) juego;
                System.out.print(elemento.getElementsByTagName("titulo").item(0).getChildNodes().item(0).getNodeValue());
                System.out.print(", ");
                System.out.print(elemento.getElementsByTagName("genero").item(0).getChildNodes().item(0).getNodeValue());
                System.out.print(", ");
                System.out.print(elemento.getElementsByTagName("año").item(0).getChildNodes().item(0).getNodeValue());
                System.out.println();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Juego{
    private String titulo, genero;
    private int anyo;

    public Juego(String titulo, String genero, int anyo) {
        this.titulo = titulo;
        this.genero = genero;
        this.anyo = anyo;
    }
    public String getTitulo() {     return titulo;    }
    public void setTitulo(String titulo) {      this.titulo = titulo;    }
    public String getGenero() {       return genero;    }
    public void setGenero(String genero) {        this.genero = genero;    }
    public int getAnyo() {        return anyo;    }
    public void setAnyo(int anyo) {        this.anyo = anyo;    }
}
