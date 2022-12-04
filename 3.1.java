import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

class Main_3_1 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("3.in"));
        var line = br.readLine();
        var total = 0;
        while (line != null) {
            var first = line.substring(0, line.length() / 2)
                    .chars()
                    .boxed()
                    .collect(Collectors.toSet());
            char repeated = (char) line.substring(line.length() / 2, line.length())
                    .chars()
                    .filter(x -> first.contains(x))
                    .findFirst()
                    .getAsInt();
            if (repeated >= 'A' && repeated <= 'Z') {
                total += 27 + repeated - 'A';
            } else {
                total += 1 + repeated - 'a';
            }
            line = br.readLine();
        }
        System.out.println("The sum of the priorities is: " + total);
        br.close();
    }
}
