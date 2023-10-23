import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        HibernateUtil.inicia(); //Crea el SessionFactory
        insertar();
        listarParametros();
    }

    public static void insertar(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Editorial miEditorial=new Editorial("NuevaEditorial", "España");
        s.save(miEditorial);
        s.getTransaction().commit();
        s.close();
        System.out.println("El id es " + miEditorial.getId());
    }

    public static void insertar(Editorial editorial){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(editorial);
        s.getTransaction().commit();
        s.close();
        System.out.println("El id es " + editorial.getId());
    }

    public static void modificar(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Editorial miEditorial=new Editorial();
        miEditorial.setId(1);
        miEditorial.setEditorial("MiEditorial" + Math.random());
        miEditorial.setPais("China");
        s.update(miEditorial);
        s.getTransaction().commit();
        s.close();
    }

    public static void modificar(Editorial editorial){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(editorial);
        s.getTransaction().commit();
        s.close();
    }

    public static void eliminar(){
        //Eliminar un registro en la tabla
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        Editorial otraEditorial=new Editorial();
        //Para borrar solo necesito el id
        otraEditorial.setId(6);
        s.delete(otraEditorial);
        s.getTransaction().commit();
        s.close();
    }

    public static void eliminar(Editorial editorial){
        //Eliminar un registro en la tabla
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        //Para borrar solo necesito el id
        s.delete(editorial);
        s.getTransaction().commit();
        s.close();
    }

    public static void listarTodos(){
        Session s=HibernateUtil.openSession();
        //Mostrar todos los registros de la tabla
        s.beginTransaction();
        ArrayList<Editorial> editoriales= (ArrayList<Editorial>) s.createQuery("from Editorial").list();
        editoriales.forEach((mieditorial) -> System.out.println(mieditorial.toString()));
        s.getTransaction().commit();
        s.close();
    }

    public static void listarParametros(){
        Session s=HibernateUtil.openSession();
        s=HibernateUtil.openSession();
        s.beginTransaction();

        //Creamos la consulta: IMPORTANTE siempre usamos nombres de CLASES (no de tablas) y propiedades (no campos)
        Query query=s.createQuery("FROM Editorial e WHERE e.pais = :pais");

        //Asignamos un valor al parámetro pais que hemos definido en la línea anterior
        query.setParameter("pais", "España");

        //Realizamos la consulta y la guardamos en una lista
        List<Editorial> lista = (List<Editorial>) query.list();
        System.out.println("Listado de editoriales de España");
        lista.forEach((editorial) -> System.out.println(editorial.toString()));

        //Cerramos la transacción y la sesión
        s.getTransaction().commit();
        s.close();
    }

    public static void listarParametros(String pais){
        Session s=HibernateUtil.openSession();
        s=HibernateUtil.openSession();
        s.beginTransaction();

        //Creamos la consulta: IMPORTANTE siempre usamos nombres de CLASES (no de tablas) y propiedades (no campos)
        Query query=s.createQuery("FROM Editorial e WHERE e.pais = :pais");

        //Asignamos un valor al parámetro pais que hemos definido en la línea anterior
        query.setParameter("pais", pais);

        //Realizamos la consulta y la guardamos en una lista
        List<Editorial> lista = (List<Editorial>) query.list();
        System.out.println("Listado de editoriales de " + pais);
        lista.forEach((editorial) -> System.out.println(editorial.toString()));

        //Cerramos la transacción y la sesión
        s.getTransaction().commit();
        s.close();
    }

    public static List<Editorial> returnListarParametros(String pais){
        Session s=HibernateUtil.openSession();
        s=HibernateUtil.openSession();
        s.beginTransaction();
        pais = pais.toUpperCase();

        //Creamos la consulta: IMPORTANTE siempre usamos nombres de CLASES (no de tablas) y propiedades (no campos)
        Query query=s.createQuery("FROM Editorial e WHERE upper(e.pais) = :pais");

        //Asignamos un valor al parámetro pais que hemos definido en la línea anterior
        query.setParameter("pais", pais);

        //Realizamos la consulta y la guardamos en una lista
        List<Editorial> lista = (List<Editorial>) query.list();


        //Cerramos la transacción y la sesión
        s.getTransaction().commit();
        s.close();

        return lista;
    }

    public static void insertarEditorial(){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        String editorial = JOptionPane.showInputDialog("Introduce el nombre de la editorial:");
        String pais = JOptionPane.showInputDialog("Introduce el país:");
        Editorial miEditorial=new Editorial(editorial, pais);
        s.save(miEditorial);
        s.getTransaction().commit();
        s.close();
        System.out.println("El id es " + miEditorial.getId());
    }

    public static void insertar(Libro l){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(l);
        s.getTransaction().commit();
        s.close();
        System.out.println("El id es " + l.getId());
    }

    public static void modificar(Libro l){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(l);
        s.getTransaction().commit();
        s.close();
    }


    public static void eliminar(Libro l){
        //Eliminar un registro en la tabla
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        //Para borrar solo necesito el id
        s.delete(l);
        s.getTransaction().commit();
        s.close();
    }
}
