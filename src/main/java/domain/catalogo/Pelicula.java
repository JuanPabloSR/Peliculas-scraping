package domain.catalogo;

import java.util.Objects;

public class Pelicula {
    private final String id;
    private String nombre;
    private String fecha;
    private String duracion;
    private String descripcion;
    private String genero;
    private String url;


    public Pelicula(String id, String nombre, String fecha, String duracion, String descripcion, String genero, String url) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.genero = genero;
        this.url = url;
    }

    public String id() {
        return id;
    }

    public String Nombre() {
        return nombre;
    }

    public String Fecha() {
        return fecha;
    }

    public String Duracion() {
        return duracion;
    }

    public String Descripcion() {
        return descripcion;
    }

    public String Genero() {
        return genero;
    }

    public String Url() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return Objects.equals(id, pelicula.id) && Objects.equals(nombre, pelicula.nombre) && Objects.equals(fecha, pelicula.fecha) && Objects.equals(duracion, pelicula.duracion) && Objects.equals(descripcion, pelicula.descripcion) && Objects.equals(genero, pelicula.genero) && Objects.equals(url, pelicula.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, fecha, duracion, descripcion, genero, url);
    }
}
