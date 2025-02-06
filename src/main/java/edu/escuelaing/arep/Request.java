package edu.escuelaing.arep;

import java.util.Map;

/**
 * Clase que representa una solicitud HTTP (Request).
 * Contiene los parámetros de consulta de la URL y proporciona métodos para obtener sus valores.
 */
public class Request {

    // Mapa de parámetros de consulta extraídos de la URL
    private final Map<String, String> queryParams;

    /**
     * Constructor de la clase Request.
     *
     * @param queryParams Mapa de parámetros de consulta extraídos de la URL.
     */
    public Request(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

    /**
     * Obtiene el valor de un parámetro de consulta específico.
     *
     * @param key El nombre del parámetro de consulta que se desea obtener.
     * @return El valor del parámetro de consulta, o una cadena vacía si no se encuentra el parámetro.
     */
    public String getValues(String key) {
        return queryParams.getOrDefault(key, "");
    }
}
