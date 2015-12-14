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

        Result result;

        try{
            int number = Integer.parseInt(num);
            int power2 = power2(number);

            ArrayList dec = new ArrayList();
            for(int i=0;i<power2;i++){
                dec.add(2);
            }

            result = new Result1();
            result.number = number;
            result.decomposition = dec;
        }
        catch (Exception ex){
            result = new Result2();
            result.number = num;
            result.error = "not a number";
        }



        response.contentType(JSON).body(gson.toJson(result));
    }

    public static abstract class Result{
    }

    public  static class Result1 extends Result{
        public int number;
        public ArrayList<Integer> decomposition;
    }

    public static class Result2 extends Result{
        public String number = "";
        public String error = "";
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