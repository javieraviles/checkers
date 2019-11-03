package es.urjccode.mastercloudapps.adcs.draughts.models;

public enum Error {
    OUT_COORDINATE("No es una coordenada del tablero"), 
    EMPTY_ORIGIN("No hay ficha que mover"), 
    OPPOSITE_PIECE("No es una de tus fichas"),
    NOT_DIAGONAL("No vas en diagonal"),
    BAD_DISTANCE("No respetas la distancia"),
    NOT_EMPTY_TARGET("No está vacío el destino"),
    NOT_ADVANCED("No avanzas"),
    EATING_EMPTY("No comes contrarias"),
    WRONG_OPTION("Opción incorrecta");

    private String text;
 
    Error(String text) {
        this.text = text;
    }
 
    public String getText() {
        return text;
    }
}