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

            if(number > 1000000){
                result = new Result2();
                result.number = number;
                ((Result2)result).error = "too big number (>1e6)");
            }
            else {
                int power2 = power2(number);


                ArrayList dec = new ArrayList();
                for (int i = 0; i < power2; i++) {
                    dec.add(2);
                }

                ArrayList<Integer> dec2 = primeFactors((long) number);
                if (dec2.size() < dec.size()) {
                    dec = dec2;
                }

                result = new Result1();
                result.number = number;
                ((Result1) result).decomposition = dec;
            }
        }
        catch (Exception ex){
            result = new Result2();
            ((Result2)result).number = num;
            ((Result2)result).error = "not a number";
        }



        response.contentType(JSON).body(gson.toJson(result));
    }

    public static  abstract class Result{
        public int number;
    }

    public  static class Result1 extends Result{
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


    public static ArrayList<Integer> primeFactors(long number) {
        ArrayList<Integer> pF = new ArrayList<Integer>();
        long copyOfInput = number;
        for (int i = 2; i <= copyOfInput; i++) {
            if (copyOfInput % i == 0) {
                pF.add(i); // prime factor
                copyOfInput /= i;
                i--;
            }
        }
        return pF;
    }
}