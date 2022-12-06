import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;

class Main_6_2 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("6.in"));
        var line = br.readLine();
        LinkedHashSet<Character> x = new LinkedHashSet<>(4);
        for (var i = 0; i < line.length(); i++) {
            var chr = line.charAt(i);
            if (!x.contains(chr)) {
                x.add(chr);
                if (x.size() == 14) {
                    System.out.println("First marker after: " + (i + 1));
                    break;
                } else if (x.size() > 14) {
                    var iter = x.iterator();
                    iter.next();
                    iter.remove();
                }
            } else {
                var iter = x.iterator();
                do {
                    iter.next();
                    iter.remove();
                } while (x.contains(chr));
                x.add(chr);
            }
        }
        br.close();
    }
}
