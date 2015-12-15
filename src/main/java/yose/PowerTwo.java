package yose;

import com.google.gson.Gson;
import com.vtence.molecule.Request;
import com.vtence.molecule.Response;

import java.util.ArrayList;
import java.util.List;

import static com.vtence.molecule.http.MimeTypes.JSON;

public class PowerTwo {
    private final Gson gson;

    public PowerTwo(Gson gson) {
        this.gson = gson;
    }

    public void primeFactors(Request request, Response response) throws Exception {
        List<String> nums = request.parameters("number");
        int size = nums.size();

        if(size>1) {
            String results = "[";
            for (int i = 0; i < size; i++) {

                results += calculate(nums.get(i)).toString();
                if(i< size-1){
                    results += ",";
                }
            }
            results += "]";
            response.contentType(JSON).body(gson.toJson(results));
        }
        else if (nums.size()==1){
            response.contentType(JSON).body(gson.toJson(calculate(nums.get(0))));
        }

    }

    public static  abstract class Result{

    }

    public Result calculate (String num){
        Result result;
        try{
            int number = Integer.parseInt(num);

            if(number > 1000000){
                result = new Result3();
                ((Result3)result).number = number;
                ((Result3)result).error = "too big number (>1e6)";
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
                ((Result1) result).number = number;
                ((Result1) result).decomposition = dec;
            }
        }
        catch (Exception ex){
            result = new Result2();
            ((Result2)result).number = num;
            ((Result2)result).error = "not a number";
        }
        return result;
    }

    public static class Results{
        public Result[] results;
        public Results(int num){
            results = new Result[num];
        }
    }

    public  static class Result1 extends Result{
        public int number;
        public ArrayList<Integer> decomposition;
    }

    public static class Result2 extends Result{
        public String number = "";
        public String error = "";
    }

    public static class Result3 extends Result{
        public int number;
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