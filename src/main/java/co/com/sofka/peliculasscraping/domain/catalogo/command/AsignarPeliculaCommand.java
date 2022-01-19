package co.com.sofka.peliculasscraping.domain.catalogo.command;

import co.com.sofka.domain.generic.Command;

public class AsignarPeliculaCommand extends Command {

    private  String catalogoId;
    private  String url;
    private  String nombre;
    private  String genero;
    private  String sinopsis;
    private  String fecha;

    public AsignarPeliculaCommand() {
    }

    public String getCatalogoId() {
        return catalogoId;
    }

    public String getUrl() {
        return url;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public String getFecha() {
        return fecha;
    }

    public void setCatalogoId(String catalogoId) {
        this.catalogoId = catalogoId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}