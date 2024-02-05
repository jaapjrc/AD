import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

public class Animales {

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Document> collection;

    public Animales() {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("animales");
        collection = database.getCollection("animales");
    }

    public void crearAnimal(String nombre, String continente, int velocidad, String alimentacion) {
        Document animal = new Document();
        animal.put("nombre", nombre);
        animal.put("continente", continente);
        animal.put("velocidad", velocidad);
        animal.put("alimentacion", alimentacion);

        collection.insertOne(animal);
    }

    public void modificarAnimal(String nombre, String campo, String valor) {
        Document filter = new Document();
        filter.put("nombre", nombre);

        Document update = new Document();
        update.put(campo, valor);

        collection.updateOne(filter, update);
    }

    public void eliminarAnimal(String nombre) {
        Document filter = new Document();
        filter.put("nombre", nombre);

        collection.deleteOne(filter);
    }

    public ArrayList<Document> listarAnimales() {
        return collection.find().into(new ArrayList<>());
    }
}
