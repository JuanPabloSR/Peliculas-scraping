package domain.catalogo;

import domain.catalogo.event.catalogoCreado;
import domain.catalogo.event.peliculaAsignada;
import domain.generic.AggregateRoot;
import domain.generic.DomainEvent;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Catalogo extends AggregateRoot {
    protected Map<String, Pelicula> peliculas;
    protected String nombre;

    public Catalogo(String catalogoId, String nombre) {
        super(catalogoId);
        Objects.requireNonNull(nombre);
        subscribe(new catalogoEventChange(this));
        appendChange(new catalogoCreado(nombre)).apply();
    }

    private Catalogo(String catalogoId){
        super(catalogoId);
        subscribe(new catalogoEventChange(this));
    }

    public static Catalogo from(String catalogoId, List<DomainEvent> events){
        var catalogo = new Catalogo(catalogoId);
        events.forEach(catalogo::applyEvent);
        return catalogo;
    }

    public void agregarPelicula(String peliculaId, String nombre, String fecha, String duracion, String descripcion, String genero,String url){
/*        Objects.requireNonNull(movieId);
        Objects.requireNonNull(name);
        Objects.requireNonNull(year);
        Objects.requireNonNull(duration);
        Objects.requireNonNull(description);
        Objects.requireNonNull(gender);
        Objects.requireNonNull(path);*/
        appendChange(new peliculaAsignada(peliculaId, nombre, fecha,duracion,descripcion,genero,url)).apply();
    }



    public Map<String, Pelicula> peliculas() {
        return peliculas;
    }

    public String nombre() {
        return nombre;
    }
}
