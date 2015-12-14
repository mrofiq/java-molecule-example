package yose;

import com.google.gson.Gson;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;

import java.util.ArrayList;

import static com.vtence.molecule.http.MimeTypes.JSON;

public class PowerTwo {
    private final Gson gson;

    public PowerTwo(Gson gson) {
        this.gson = gson;
    }

    public void primeFactors(Request request, Response response) throws Exception {
        String num = request.parameter("number");

        int number = Integer.valueOf(num);
        int power2 = power2(number);

        ArrayList dec = new ArrayList();
        for(int i=0;i<power2;i++){
            dec.add(2);
        }

        Result result = new Result();
        result.number = number;
        result.decomposition = dec;

        response.contentType(JSON).body(gson.toJson(result));
    }

    public static class Result{
        public int number;
        public ArrayList<Integer> decomposition;
    }

    public static int power2(int number) {
        int counter=0;
        int sisa=number;
        while(sisa>1){
            sisa=sisa/2;
            counter=counter+1;
        }
        return counter;
    }
}