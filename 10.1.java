import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main_10_1 {
    private static int result = 0;
    private static int cycle = 1;
    private static int x = 1;

    private static void incrementCycle() {
        if ((cycle - 20) % 40 == 0) {
            result += x * cycle;
        }
        cycle++;
    }

    private static void executeNoop() {
        incrementCycle();
    }

    private static void executeAddx(int value) {
        incrementCycle();
        incrementCycle();
        x += value;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("10.in"));
        String line;
        while ((line = br.readLine()) != null && cycle < 220) {
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
        System.err.println("The sum of the six signal strengths is: " + result);
        br.close();
    }
}
