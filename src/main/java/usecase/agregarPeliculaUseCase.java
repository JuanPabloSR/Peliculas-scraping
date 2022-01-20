package usecase;

import java.util.function.Function;
import java.util.List;

import domain.catalogo.Catalogo;
import domain.catalogo.command.agregarPeliculaComando;
import domain.generic.DomainEvent;
import domain.generic.EventStoreRepository;

import javax.enterprise.context.Dependent;


@Dependent
public class agregarPeliculaUseCase implements Function<agregarPeliculaComando, List<DomainEvent>> {

    private  final EventStoreRepository repository;

    public agregarPeliculaUseCase(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DomainEvent> apply(agregarPeliculaComando command) {
        var events = repository.getEventsBy("catalogo", command.getCatalogoId());
        var course = Catalogo.from(command.getCatalogoId(),events);
        course.agregarPelicula(command.getPeliculaId(),command.getNombre(),command.getFecha(),command.getDuracion(),command.getDescripcion(),command.getGenero(),command.getUrl());
        return course.getUncommittedChanges();
    }


}
