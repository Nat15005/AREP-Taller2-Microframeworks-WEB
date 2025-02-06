package edu.escuelaing.arep;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

class HttpServerTest {

    private static final int PORT = 35000;
    private static Thread serverThread;

    @BeforeAll
    static void startServer() {
        serverThread = new Thread(() -> {
            try {
                HttpServer.main(new String[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


    @Test
    void testHelloEndpoint() throws IOException {
        URL url = new URL("http://localhost:" + PORT + "/App/hello?name=Pedro");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode(), "El código de respuesta debería ser 200 OK");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = in.readLine();
        in.close();

        assertEquals("Hello Pedro", response, "La respuesta debería ser 'Hello Pedro'");
    }

    @Test
    void testPiEndpoint() throws IOException {
        URL url = new URL("http://localhost:" + PORT + "/App/pi");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode(), "El código de respuesta debería ser 200 OK");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = in.readLine();
        in.close();

        assertEquals(String.valueOf(Math.PI), response, "La respuesta debería ser el valor de PI");
    }

    @Test
    void testStaticFile() throws IOException {
        URL url = new URL("http://localhost:" + PORT + "/index.html"); // Asegúrate de que este archivo exista
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(200, connection.getResponseCode(), "El código de respuesta debería ser 200 OK");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String response = in.readLine();
        in.close();

        assertNotNull(response, "La respuesta no debería ser nula");
    }

    @Test
    void testNonExistentRoute() throws IOException {
        URL url = new URL("http://localhost:" + PORT + "/nonexistent");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        assertEquals(404, connection.getResponseCode(), "El código de respuesta debería ser 404 Not Found");
    }
}