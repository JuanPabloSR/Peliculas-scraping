package domain.catalogo;

import domain.catalogo.event.catalogoCreado;
import domain.catalogo.event.peliculaAsignada;
import domain.generic.EventChange;

import java.util.HashMap;

public class catalogoEventChange implements EventChange {
    public catalogoEventChange(Catalogo catalogo) {

        listener((catalogoCreado event)-> {
            catalogo.nombre = event.getNombre();
            catalogo.peliculas = new HashMap<>();
        });

        listener((peliculaAsignada event)-> {
            var pelicula =  new Pelicula(event.getPeliculaId(), event.getNombre(),event.getFecha(),event.getDuracion(),event.getDescripcion(),event.getGenero(), event.getUrl());
            catalogo.peliculas.put(event.getPeliculaId(), pelicula);
        });

    }

}
