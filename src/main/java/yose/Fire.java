package yose;

import com.google.gson.Gson;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;

import java.util.ArrayList;
import java.util.List;

import static com.vtence.molecule.http.MimeTypes.JSON;
import static com.vtence.molecule.http.MimeTypes.TEXT;

public class Fire {
    private final Gson gson;

    public Fire(Gson gson) {
        this.gson = gson;
    }

    public void primeFactors(Request request, Response response) throws Exception {
        String nums = request.parameter("width");
        String map = request.parameter("map");

        response.contentType(TEXT).body("Any moves that will lead your plane (P) to take water (W) and then reach the fire (F)");
    }

    public static  abstract class Result{

    }
}