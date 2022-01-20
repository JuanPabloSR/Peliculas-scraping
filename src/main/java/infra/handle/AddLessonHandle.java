package infra.handle;

import domain.catalogo.command.agregarPeliculaComando;
import infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;
import usecase.extraerCatalogoUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddLessonHandle extends UseCaseHandle {

    private final extraerCatalogoUseCase useCase;

    public AddLessonHandle(extraerCatalogoUseCase useCase) {
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.catalogo.agregar")
    void consumeBlocking(agregarPeliculaComando command) {
        var events = useCase.apply(command);
        process(command.getCatalogoId(), events);
    }


}
