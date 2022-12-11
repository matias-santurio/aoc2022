import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main_10_2 {
    private static StringBuilder result = new StringBuilder(40 * 6);
    private static int cycle = 0;
    private static int x = 0;

    private static void incrementCycle() {
        if (Math.abs((cycle % 40) - x) < 2) {
            result.append('#');
        } else {
            result.append('.');
        }
        cycle++;
    }

    private static void executeNoop() {
        incrementCycle();
    }

    private static void executeAddx(int value) {
        incrementCycle();
        x += value;
        incrementCycle();
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("10.in"));
        String line;
        while ((line = br.readLine()) != null && cycle < 239) {
            var parts = line.split(" ");
            var op = parts[0];
            switch (op) {
                case "noop":
                    executeNoop();
                    break;
                case "addx":
                    executeAddx(Integer.parseInt(parts[1]));
                    break;
            }
        }
        for (var i = 0; i < 6; i++) {
            System.out.println(result.substring(i * 40, (i + 1) * 40 - 1));
        }
        br.close();
    }
}
