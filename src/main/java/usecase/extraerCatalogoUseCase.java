package usecase;

import domain.catalogo.Catalogo;
import domain.catalogo.command.agregarPeliculaComando;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.enterprise.context.Dependent;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@Dependent
public class extraerCatalogoUseCase implements Function<agregarPeliculaComando, List<DomainEvent>> {

        private final EventStoreRepository repository;

        final String baseURL = "https://pelisplus.so/estrenos";

    public extraerCatalogoUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<DomainEvent> apply(agregarPeliculaComando command) {

        var events = repository.getEventsBy("catalogo", command.getCatalogoId());
        var catalogo = Catalogo.from(command.getCatalogoId(),events);

        var document = urlBase();
        Elements entradas = document.select(".items-peliculas .item-pelicula a");

        for (Element elem : entradas) {
            final String urlPelicula = elem.attr("href");
            try {
                final Document pelicula = Jsoup.connect("https://pelisplus.so" + urlPelicula).get();
                String nombrePelicula = pelicula.select(".info-content h1").text();
                String genero = pelicula.select(".info-content p:nth-of-type(4) span:nth-of-type(2)").text();
                String descripcion = pelicula.select(".sinopsis").text();
                String fecha = pelicula.select(".info-content p:nth-of-type(2) span:nth-of-type(2)").text();
                String url = pelicula.select(".player.player-normal ul:nth-of-type(2)  li:nth-of-type(1)").attr("data-video");
                String duracion = pelicula.select(".info-content p:nth-of-type(3) span:nth-of-type(2)").text();

                catalogo.agregarPelicula(
                        command.getPeliculaId(),
                        nombrePelicula,
                        fecha,
                        duracion,
                        descripcion,
                        genero,
                        url
                );

            }catch (Exception e){
                throw new RuntimeException("No se pudo obtener la información de la pelicula");
            }

        }
        return catalogo.getUncommittedChanges();
    }


    public Document urlBase() {
        try {
            return Jsoup.connect(baseURL).get();
        }catch (IOException e){
            throw new RuntimeException("Error con es status de la página");
        }
    }

}
