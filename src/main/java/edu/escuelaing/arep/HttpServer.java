package edu.escuelaing.arep;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase que implementa un servidor HTTP que escucha en el puerto 35000.
 * Permite manejar solicitudes REST y servir archivos estáticos desde una carpeta configurada.
 */
public class HttpServer {

    // Puerto en el que el servidor escuchará las conexiones
    private static final int PORT = 35000;

    /**
     * Metodo principal que configura el servidor HTTP y maneja las solicitudes entrantes.
     *
     * @param args Argumentos de línea de comandos (no se utilizan en esta implementación).
     * @throws IOException Si ocurre un error al crear el servidor o aceptar las conexiones de los clientes.
     */
    public static void main(String[] args) throws IOException {
        // Configurar carpeta de archivos estáticos
        WebFramework.staticfiles("src/main/resources/static");

        // Definir rutas REST
        WebFramework.get("/App/hello", (req, res) -> "Hello " + req.getValues("name"));
        WebFramework.get("/App/pi", (req, res) -> String.valueOf(Math.PI));

        // Iniciar servidor
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor escuchando en el puerto " + PORT);

        boolean running = true;
        while (running) {
            // Aceptar una nueva conexión de cliente
            Socket clientSocket = serverSocket.accept();
            // Manejar la solicitud del cliente
            RequestHandler.handleClient(clientSocket);
        }
    }
}
