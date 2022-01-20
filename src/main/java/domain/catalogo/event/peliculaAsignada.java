package domain.catalogo.event;

import domain.generic.DomainEvent;

public class peliculaAsignada extends DomainEvent {
    private final String peliculaId;
    private final String nombre;
    private final String fecha;
    private final String duracion;
    private final String descripcion;
    private final String genero;
    private final String url;


    public peliculaAsignada(String peliculaId, String nombre, String fecha, String duracion, String descripcion, String genero, String url) {
        super("sofkau.catalogo.PeliculaAsignada");
        this.peliculaId = peliculaId;
        this.nombre = nombre;
        this.fecha = fecha;
        this.duracion = duracion;
        this.descripcion = descripcion;
        this.genero = genero;
        this.url = url;
    }

    public String getPeliculaId() {
        return peliculaId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public String getUrl() {
        return url;
    }
}
