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
        int number = -1;

        try {
            number = Integer.parseInt(num);
        }
        catch (Exception e){

        }

        if (number != -1){
            number = Integer.parseInt(num);
            int power2 = power2(number);

            ArrayList dec = new ArrayList();
            for(int i=0;i<power2;i++){
                dec.add(2);
            }

            Result1 result = new Result1();
            result.number = number;
            result.decomposition = dec;
            response.contentType(JSON).body(gson.toJson(result));
        }
        else {
            Result2 result2 = new Result2();
            result2.number = num;
            result2.error = "not a number";
            response.contentType(JSON).body(gson.toJson(result2));
        }




    }

    public static abstract class Result{
    }

    public  static class Result1{
        public int number;
        public ArrayList<Integer> decomposition;
    }

    public static class Result2{
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