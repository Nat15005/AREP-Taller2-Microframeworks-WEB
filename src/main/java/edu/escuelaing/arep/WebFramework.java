package edu.escuelaing.arep;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class WebFramework {
    private static String staticFolder = "src/main/resources/static"; // Ruta por defecto
    private static final Map<String, BiFunction<Request, Response, String>> getRoutes = new HashMap<>();

    /**
     * Permite configurar la ubicación de los archivos estáticos.
     * @param folder Ruta donde se encuentran los archivos estáticos.
     */
    public static void staticfiles(String folder) {
        staticFolder = folder;
    }

    /**
     * Registra un nuevo endpoint GET en el framework.
     * @param path Ruta de la API.
     * @param handler Función lambda que maneja la petición.
     */
    public static void get(String path, BiFunction<Request, Response, String> handler) {
        getRoutes.put(path, handler);
    }

    /**
     * Maneja las peticiones entrantes.
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

    public static String getStaticFolder() {
        return staticFolder;
    }
}
