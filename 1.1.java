import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class Main{
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("1.in"));
        var lines = new ArrayList<String>();
        var line = br.readLine();
        while (line != null){
            lines.add(line);
            line = br.readLine();
        }
        int maxCalories = 0, currentCalories = 0;
        for (var l: lines){
            if (l.isBlank()){
                if (currentCalories > maxCalories){
                    maxCalories = currentCalories;
                }
                currentCalories = 0;
            }
            else{
                currentCalories += Integer.parseInt(l);
            }
        }
        System.out.println("Max calories is: " + maxCalories);
        br.close();
    }
}
