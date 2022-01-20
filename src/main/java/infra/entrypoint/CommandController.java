package infra.entrypoint;


import domain.catalogo.command.agregarPeliculaComando;
import domain.catalogo.command.crearCatalogoComando;
import io.vertx.mutiny.core.eventbus.EventBus;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/catalogo")
public class CommandController {

    private final EventBus bus;

    public CommandController(EventBus bus){
        this.bus = bus;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/crear")
    public Response executor(crearCatalogoComando command) {
        bus.publish(command.getType(), command);
        return Response.ok().build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/agregarpelicula")
    public Response executor(agregarPeliculaComando command) {
        bus.publish(command.getType(), command);//emitir comandos, los casos de uso
        return Response.ok().build();
    }
}
