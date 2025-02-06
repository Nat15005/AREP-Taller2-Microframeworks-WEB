package edu.escuelaing.arep;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class RequestHandler {
    public static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        OutputStream out = clientSocket.getOutputStream();

        String requestLine = in.readLine();
        if (requestLine == null || requestLine.trim().isEmpty()) {
            sendBadRequest(out);
            clientSocket.close();
            return;
        }

        System.out.println("Solicitud recibida: " + requestLine);
        String[] requestParts = requestLine.split(" ");
        if (requestParts.length < 2) {
            sendBadRequest(out);
            clientSocket.close();
            return;
        }

        String method = requestParts[0];  // GET, POST, etc.
        String fullResource = requestParts[1]; // /App/hello?name=Pedro

        // Extraer parámetros de consulta
        String resource = fullResource.split("\\?")[0];
        Map<String, String> queryParams = new HashMap<>();
        if (fullResource.contains("?")) {
            String queryString = fullResource.split("\\?")[1];
            for (String param : queryString.split("&")) {
                String[] keyValue = param.split("=");
                if (keyValue.length == 2) {
                    queryParams.put(URLDecoder.decode(keyValue[0], "UTF-8"), URLDecoder.decode(keyValue[1], "UTF-8"));
                }
            }
        }

        // Manejar la petición con WebFramework
        WebFramework.handleRequest(method, resource, queryParams, out);

        out.close();
        in.close();
        clientSocket.close();
    }

    private static void sendBadRequest(OutputStream out) throws IOException {
        String response = "HTTP/1.1 400 Bad Request\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                "400 Bad Request";
        out.write(response.getBytes());
    }
}
