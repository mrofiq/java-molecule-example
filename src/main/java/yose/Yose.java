package yose;

import com.google.gson.Gson;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.routing.DynamicRoutes;

import java.io.IOException;
import java.net.URI;

public class Yose {

    private final WebServer server;

    public Yose(int port) {
        this.server = WebServer.create(port);
    }

    public void start() throws IOException {
        final Gson gson = new Gson();

        server.start(new DynamicRoutes() {{
            get("/").to((request, response) -> response.body("<html><body>Hello Yose <a id=\"contact-me-link\" href=\"/aboutme\">Contact Me</a></body></html>"));
            get("/ping").to(new Ping(gson)::pong);
            get("/astroport").to((request, response) -> {
                response.contentType("text/html");
                response.body(
                        "<html><body>Hello Astroport <div id=\"astroport-name\">Astroport</div></body></html>"
                );
            });
            get("/primeFactors").to(new PowerTwo(gson)::primeFactors);
        }});
    }

    public URI uri() {
        return server.uri();
    }

    public void stop() throws IOException {
        server.stop();
    }

    private static final int PORT = 0;

    private static int port(String[] args) {
        return args.length > 0 ? Integer.parseInt(args[PORT]) : 8080;
    }

    public static void main(String[] args) throws IOException {
        Yose yose = new Yose(port(args));
        yose.start();
        System.out.print("To play the game visit " + yose.uri());
    }
}