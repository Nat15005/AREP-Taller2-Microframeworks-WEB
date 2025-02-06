package edu.escuelaing.arep;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Framework web sencillo que permite manejar peticiones HTTP GET y servir archivos estáticos.
 * Permite registrar rutas para manejar peticiones y especificar la ubicación de los archivos estáticos.
 */
public class WebFramework {

    // Ruta por defecto para los archivos estáticos
    private static String staticFolder = "src/main/resources/static";

    // Mapa que almacena las rutas GET registradas y sus manejadores
    static final Map<String, BiFunction<Request, Response, String>> getRoutes = new HashMap<>();

    /**
     * Permite configurar la ubicación de los archivos estáticos.
     *
     * @param folder Ruta donde se encuentran los archivos estáticos.
     */
    public static void staticfiles(String folder) {
        staticFolder = folder;
    }

    /**
     * Registra un nuevo endpoint GET en el framework.
     *
     * @param path    Ruta de la API.
     * @param handler Función lambda que maneja la petición.
     */
    public static void get(String path, BiFunction<Request, Response, String> handler) {
        getRoutes.put(path, handler);
    }

    /**
     * Maneja las peticiones entrantes según el metodo y recurso solicitado.
     * Si la petición es un GET y la ruta está registrada, ejecuta el manejador correspondiente.
     * Si no, intenta servir un archivo estático.
     *
     * @param method      El metodo HTTP (por ejemplo, "GET").
     * @param resource    La ruta del recurso solicitado.
     * @param queryParams Parámetros de consulta de la URL.
     * @param out         El flujo de salida donde se enviará la respuesta.
     * @throws IOException Si ocurre un error al escribir en el flujo de salida.
     */
    public static void handleRequest(String method, String resource, Map<String, String> queryParams, OutputStream out) throws IOException {
        if ("GET".equals(method) && getRoutes.containsKey(resource)) {
            Request req = new Request(queryParams);
            Response res = new Response();
            String responseBody = getRoutes.get(resource).apply(req, res);

            String response = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: " + res.getContentType() + "\r\n" +
                    "Content-Length: " + responseBody.length() + "\r\n" +
                    "\r\n" +
                    responseBody;
            out.write(response.getBytes());
        } else {
            FileHandler.serveFile(resource, out);
        }
    }

    /**
     * Obtiene la ruta de la carpeta de archivos estáticos configurada.
     *
     * @return La ruta de la carpeta de archivos estáticos.
     */
    public static String getStaticFolder() {
        return staticFolder;
    }
}
