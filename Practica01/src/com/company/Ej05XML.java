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

/*
    1-Modifica el programa para añadir 2 nuevos productos (lechugas y calabazas) al XML.
    2-Modifica los productos para añadir una nueva propiedad llamada "categoría"
    3-Modifica la creación de productos y los métodos escribirXML y leerXML para que se guarde y se muestre el valor de dicha categoría.
 */

public class Ej05XML {

    public static void main(String[] args) {
        ArrayList<Producto> lista=new ArrayList<Producto>();
        lista.add(new Producto("patata", 3, "A"));
        lista.add(new Producto("tomate", 5, "B"));
        // Ejercicio 1
        lista.add(new Producto("lechugas", 1.20f, "C"));
        lista.add(new Producto("calabazas", 10, "C"));

        escribirXML(lista);
        leerFicheroXML();
    }

    public static void escribirXML(ArrayList<Producto> listaProductos) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            documento = dom.createDocument(null,  "xml", null);

            Element raiz = documento.createElement("Productos");
            documento.getDocumentElement().appendChild(raiz);

            Element nodoProducto = null, nodoDatos = null;
            Text texto = null;

            for (Producto producto : listaProductos) {

                nodoProducto = documento.createElement("Producto");
                raiz.appendChild(nodoProducto);

                nodoDatos = documento.createElement("nombre");
                nodoProducto.appendChild(nodoDatos);

                nodoDatos.setAttribute("nacionalidad", "española");

                texto = documento.createTextNode(producto.getNombre());
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("precio");
                nodoProducto.appendChild(nodoDatos);

                texto = documento.createTextNode(String.valueOf(producto.getPrecio()));
                nodoDatos.appendChild(texto);
                // Ejercicio 3
                nodoDatos = documento.createElement("categoria");
                nodoProducto.appendChild(nodoDatos);

                texto = documento.createTextNode(producto.getCategoria());
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

    public static void leerFicheroXML() {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document documento = null;

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            documento = builder.parse(new File("archivo.xml"));

            // Recorre cada uno de los nodos 'Persona'
            NodeList productos = documento.getElementsByTagName("Producto");
            for (int i = 0; i < productos.getLength(); i++) {
                Node producto = productos.item(i);
                Element elemento = (Element) producto;

                System.out.print(elemento.getElementsByTagName("nombre").item(0).
                        getChildNodes().item(0).getNodeValue());
                System.out.print(" ");
                System.out.print(elemento.getElementsByTagName("precio").item(0).
                        getChildNodes().item(0).getNodeValue());
                // Ejercicio 3
                System.out.print(" ");
                System.out.print(elemento.getElementsByTagName("categoria").item(0).
                        getChildNodes().item(0).getNodeValue());
                System.out.println();
            }

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (org.xml.sax.SAXException saxe) {
            saxe.printStackTrace();
        }
    }
}
class Producto {
    private String nombre;
    private float precio;
    // Ejercicio 2
    private String categoria;

    public Producto(String nombre, float precio, String categoria){
        this.nombre=nombre;
        this.precio=precio;
        // Ejercicio 2
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    // Ejercicio 2
    public String getCategoria() {
        return categoria;
    }
    // Ejercicio 2
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
}