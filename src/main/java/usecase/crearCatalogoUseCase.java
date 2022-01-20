package usecase;

import domain.catalogo.Catalogo;
import domain.catalogo.command.crearCatalogoComando;
import domain.generic.DomainEvent;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.function.Function;


@Dependent
public class crearCatalogoUseCase implements Function<crearCatalogoComando, List<DomainEvent>> {

    @Override
    public List<DomainEvent> apply(crearCatalogoComando command) {
        var course = new Catalogo(command.getCatalogoId(),command.getNombre());
        return course.getUncommittedChanges();
    }
}
