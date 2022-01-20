package infra.handle;

import domain.catalogo.command.crearCatalogoComando;
import infra.generic.UseCaseHandle;
import io.quarkus.vertx.ConsumeEvent;
import usecase.crearCatalogoUseCase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreateCourseHandle extends UseCaseHandle {

    private final crearCatalogoUseCase useCase;

    public CreateCourseHandle(crearCatalogoUseCase useCase) {
        this.useCase = useCase;
    }

    @ConsumeEvent(value = "sofka.catalogo.crear")
    void consumeBlocking(crearCatalogoComando command) {
        var events = useCase.apply(command);
        process(command.getCatalogoId(), events);
    }
}
