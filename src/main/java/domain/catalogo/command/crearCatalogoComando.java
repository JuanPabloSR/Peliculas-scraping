package domain.catalogo.command;

import domain.generic.Command;

public class crearCatalogoComando extends Command {
    private String catalogoId;
    private String nombre;

/*    public CreateCatalogCommand(String catalogId, String name) {
        this.catalogId = catalogId;
        this.name = name;
    }*/

    public crearCatalogoComando() {
    }

    public String getCatalogoId() {
        return catalogoId;
    }

    public void setCatalogoId(String catalogoId) {
        this.catalogoId = catalogoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
