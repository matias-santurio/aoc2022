import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

class Main_3_2 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("3.in"));
        var lines = br.lines().toArray(String[]::new);
        var total = 0;
        for (var i = 0; i < lines.length; i += 3) {
            var first = lines[i]
                    .chars()
                    .boxed()
                    .collect(Collectors.toSet());
            var second = lines[i + 1]
                    .chars()
                    .filter(x -> first.contains(x))
                    .boxed()
                    .collect(Collectors.toSet());
            var third = lines[i + 2]
                    .chars()
                    .filter(x -> second.contains(x))
                    .findAny()
                    .getAsInt();
            if (third >= 'A' && third <= 'Z') {
                total += 27 + third - 'A';
            } else {
                total += 1 + third - 'a';
            }
        }
        System.out.println("The sum of the priorities is: " + total);
        br.close();
    }
}
