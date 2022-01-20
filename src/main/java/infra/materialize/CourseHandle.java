package infra.materialize;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import domain.catalogo.event.catalogoCreado;
import domain.catalogo.event.peliculaAsignada;
import io.quarkus.vertx.ConsumeEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


@ApplicationScoped
public class CourseHandle {
    private final MongoClient mongoClient;

    public CourseHandle(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    @ConsumeEvent(value = "sofkau.catalogo.CatalogoCreado", blocking = true)
    void consumeProgramCreated(catalogoCreado event) {
        Map<String, Object> document = new HashMap<>();
        document.put("_id", event.getAggregateId());
        document.put("name", event.getNombre());

        mongoClient.getDatabase("queries")
                .getCollection("catalog")
                .insertOne(new Document(document));
    }



    @ConsumeEvent(value = "sofkau.catalogo.PeliculaAsignada", blocking = true)
    void consumeLessonAdded(peliculaAsignada event) {
        BasicDBObject document = new BasicDBObject();
        document.put("movie."+event.getNombre()+".name",event.getNombre());
        document.put("movie."+event.getNombre()+".date", Instant.now());
        document.put("movie."+event.getNombre()+".descripcion", event.getDescripcion());
        document.put("movie."+event.getNombre()+".duracion", event.getDuracion());
        document.put("movie."+event.getNombre()+".genero", event.getGenero());
        document.put("movie."+event.getNombre()+".año", event.getFecha());
        document.put("movie."+event.getNombre()+".url", event.getUrl());
        System.out.println("holaaaaa" + event.getNombre());
        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", document);

        mongoClient.getDatabase("queries")
                .getCollection("catalog")
                .updateOne( Filters.eq("_id", event.getAggregateId()), updateObject);
       }
}