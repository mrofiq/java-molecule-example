package yose;

import com.google.gson.Gson;
import com.vtence.molecule.WebServer;
import com.vtence.molecule.routing.DynamicRoutes;
import com.vtence.molecule.templating.Template;
import com.vtence.molecule.templating.Templates;

import java.io.IOException;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;

import com.vtence.molecule.templating.JMustacheRenderer;
import com.vtence.molecule.templating.Template;
import com.vtence.molecule.templating.Templates;

public class Yose {

    private final WebServer server;

    public Yose(int port) {
        this.server = WebServer.create(port);
    }

    public void start() throws IOException {
        final Templates templates = new Templates(
                new JMustacheRenderer().fromDir(new File("templates")).extension("html"));
        final Gson gson = new Gson();

        server.start(new DynamicRoutes() {{
            get("/").to((request, response) -> {
                response.contentType("text/html");
                response.body(
                        "<html><body>Hello Yose <a id=\"contact-me-link\" href=\"/aboutme\">Contact Me</a>" +
                                "<a id=\"ping-challenge-link\" href=\"/ping\">Ping</a>" +
                                "<a id=\"repository-link\" href=\"https://github.com/mrofiq/java-molecule-example\">Link</a>"+
                                "<div id=\"readme\">YoseTheGame</div>"+
                                "</body></html>");
            });
            get("/test").to((request, response) -> {
                response.contentType("text/html");
                response.body(templates.named("test").render(null));
            });
            get("/readme").to((request, response) -> {
                response.contentType("text/html");
                response.body(
                        "<html><body>"+
                                "<div id=\"readme\">YoseTheGame</div>"+
                                "</body></html>");
            });
            get("/ping").to(new Ping(gson)::pong);
            get("/astroport").to((request, response) -> {
                String htmlBody = "<html><body>Hello Astroport <div id=\"astroport-name\">Astroport</div>" +
                        "<div id=\"gate-1\">gate 1"+
                        "<div id=\"ship-1\">ship 1</div></div>"+
                        "<div id=\"gate-2\">gate 2"+
                        "<div id=\"ship-2\">ship 2</div></div>"+
                        "<div id=\"gate-3\">gate 3"+
                        "<div id=\"ship-3\">ship 3</div></div>"+
                        "Ship <form action=\"/astroport\"><input type=\"text\" id=\"ship\" name=\"ship\"/> <button type=\"submit\" id=\"dock\">Dock</button></form>"+
                        "</body></html>";

                String shipParam = request.parameter("ship");
                if(shipParam!=null && !shipParam.isEmpty())
                    htmlBody = htmlBody.replace("ship 1", shipParam);
                response.contentType("text/html");
                response.body(htmlBody);
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