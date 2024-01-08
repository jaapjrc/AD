package com.example.ejercicio7_joseantonioalacidperez;

import javafx.collections.ObservableList;
import org.hibernate.Session;

public class RepositorioSalas {
    public static ObservableList<Sala> listarTodos(){
        Session s=HibernateUtil.openSession();
        //Mostrar todos los registros de la tabla
        s.beginTransaction();
        ObservableList<Sala> salas= (ObservableList<Sala>) s.createQuery("from salas").list();
        s.getTransaction().commit();
        s.close();
        return salas;
    }

    public static void insertar(Sala sala){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.save(sala);
        s.getTransaction().commit();
        s.close();
    }

    public static void modificar(Sala sala){
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        s.update(sala);
        s.getTransaction().commit();
        s.close();
    }

    public static void eliminar(Sala sala){
        //Eliminar un registro en la tabla
        Session s=HibernateUtil.openSession();
        s.beginTransaction();
        //Para borrar solo necesito el id
        s.delete(sala);
        s.getTransaction().commit();
        s.close();
    }
}
