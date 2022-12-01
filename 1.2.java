import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

class Main{
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("1.in"));
        var line = br.readLine();
        var gnomesCalories = new ArrayList<Integer>();
        var currentCalories = 0;
        while (line != null){
            line = br.readLine();
            if (line == null || line.isBlank()){
                gnomesCalories.add(currentCalories);
                currentCalories = 0;
            }
            else{
                currentCalories += Integer.parseInt(line);
            }
        }
        Collections.sort(gnomesCalories);
        Collections.reverse(gnomesCalories);
        var total = 0;
        for (var i = 0; i < 3; i++){
            total += gnomesCalories.get(i);
        }
        System.out.println("Total calories is: " + total);
        br.close();
    }
}
