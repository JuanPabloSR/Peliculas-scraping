package domain.catalogo.event;

import domain.generic.DomainEvent;

public class catalogoCreado extends DomainEvent {

    private final String nombre;

    public catalogoCreado(String nombre) {
        super("sofkau.catalogo.CatalogoCreado");
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
