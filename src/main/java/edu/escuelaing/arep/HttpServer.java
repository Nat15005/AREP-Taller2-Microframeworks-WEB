package edu.escuelaing.arep;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final int PORT = 35000;

    public static void main(String[] args) throws IOException {
        // Configurar carpeta de archivos estáticos
        WebFramework.staticfiles("src/main/resources/static");

        //Definir rutas REST
        WebFramework.get("/App/hello", (req, res) -> "Hello " + req.getValues("name"));
        WebFramework.get("/App/pi", (req, res) -> String.valueOf(Math.PI));

        //Iniciar servidor
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Servidor escuchando en el puerto " + PORT);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            RequestHandler.handleClient(clientSocket);
        }
    }
}
