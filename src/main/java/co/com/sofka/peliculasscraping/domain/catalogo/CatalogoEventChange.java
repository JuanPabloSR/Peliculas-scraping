package co.com.sofka.peliculasscraping.domain.catalogo;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.peliculasscraping.domain.catalogo.event.CatalogoCreado;
import co.com.sofka.peliculasscraping.domain.catalogo.event.PeliculaAsignada;

import java.util.HashMap;


public class CatalogoEventChange implements EventChange {

    public CatalogoEventChange(Catalogo catalogo) {
        listener((CatalogoCreado event)-> {
            catalogo.nombre = event.getNombre();
            catalogo.peliculas = new HashMap<>();
        });

        listener((PeliculaAsignada event)->{
            var pelicula = new Pelicula(event.getId(), event.getUrl(), event.getNombre(), event.getGenero(), event.getSinopsis(), event.getFecha());
            catalogo.peliculas.put(event.getId(), pelicula);
        });
    }
}