package edu.escuelaing.arep;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileHandler {
    /**
     * Sirve archivos estáticos desde la carpeta configurada en WebFramework.
     */
    public static void serveFile(String resource, OutputStream out) throws IOException {
        if (resource.equals("/")) {
            resource = "/index.html"; // Redirigir a index.html si se accede a "/"
        }
        Path filePath = Path.of(WebFramework.getStaticFolder() + resource);
        if (!Files.exists(filePath)) {
            sendNotFound(out);
            return;
        }

        String contentType = getContentType(resource);
        byte[] fileBytes = Files.readAllBytes(filePath);

        String responseHeader = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: " + contentType + "\r\n" +
                "Content-Length: " + fileBytes.length + "\r\n" +
                "\r\n";

        out.write(responseHeader.getBytes());
        out.write(fileBytes);
    }

    private static void sendNotFound(OutputStream out) throws IOException {
        String response = "HTTP/1.1 404 Not Found\r\n" +
                "Content-Type: text/plain\r\n" +
                "\r\n" +
                "404 Not Found";
        out.write(response.getBytes());
    }

    static String getContentType(String fileName) {
        HashMap<String, String> mimeTypes = new HashMap<>();
        mimeTypes.put("html", "text/html");
        mimeTypes.put("css", "text/css");
        mimeTypes.put("js", "text/javascript");
        mimeTypes.put("png", "image/png");
        mimeTypes.put("jpg", "image/jpeg");

        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return mimeTypes.getOrDefault(extension, "application/octet-stream");
    }
}
