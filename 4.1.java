import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main_4_1 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("4.in"));
        var line = br.readLine();
        var total = 0;
        while (line != null) {
            var arr = line.split(",");
            String[] fst = arr[0].split("-"), snd = arr[1].split("-");
            int fstfst = Integer.parseInt(fst[0]), fstsnd = Integer.parseInt(fst[1]);
            int sndfst = Integer.parseInt(snd[0]), sndsnd = Integer.parseInt(snd[1]);
            if ((fstfst <= sndfst && fstsnd >= sndsnd) || (sndfst <= fstfst && sndsnd >= fstsnd)) {
                total++;
            }
            line = br.readLine();
        }
        System.out.println("The sum of the priorities is: " + total);
        br.close();
    }
}
