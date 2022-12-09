import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

class Main_9_1 {

    private static boolean areAdjacent(int hx, int hy, int tx, int ty) {
        if (Math.abs(hx - tx) + Math.abs(hy - ty) <= 1) {
            return true;
        }
        return Math.abs(hx - tx) == 1 && Math.abs(hy - ty) == 1;
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("9.in"));
        var visited = new HashSet<Integer>();
        visited.add(0);
        String line;
        int hx = 0, hy = 0, tx = 0, ty = 0;
        while ((line = br.readLine()) != null) {
            var parts = line.split(" ");
            var dir = parts[0];
            var steps = Integer.parseInt(parts[1]);
            while (steps > 0) {
                switch (dir) {
                    case "U":
                        hx++;
                        if (!areAdjacent(hx, hy, tx, ty)) {
                            tx = hx - 1;
                            ty = hy;
                            visited.add((tx << 16) + ty);
                        }
                        break;
                    case "D":
                        hx--;
                        if (!areAdjacent(hx, hy, tx, ty)) {
                            tx = hx + 1;
                            ty = hy;
                            visited.add((tx << 16) + ty);
                        }
                        break;
                    case "L":
                        hy--;
                        if (!areAdjacent(hx, hy, tx, ty)) {
                            tx = hx;
                            ty = hy + 1;
                            visited.add((tx << 16) + ty);
                        }
                        break;
                    case "R":
                        hy++;
                        if (!areAdjacent(hx, hy, tx, ty)) {
                            tx = hx;
                            ty = hy - 1;
                            visited.add((tx << 16) + ty);
                        }
                        break;
                }
                steps--;
            }
        }
        System.err.println("Visited cells: " + visited.size());
        br.close();
    }
}
