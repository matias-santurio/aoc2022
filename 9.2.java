import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

class Knot {
    int x;
    int y;

    boolean isAdjacent(Knot k) {
        return Math.abs(x - k.x) + Math.abs(y - k.y) <= 1 || (Math.abs(x - k.x) == 1 && Math.abs(y - k.y) == 1);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class Main_9_2 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("9.in"));
        var visited = new HashSet<Integer>();
        var knots = new Knot[10];
        for (var i = 0; i < 10; i++) {
            knots[i] = new Knot();
        }
        visited.add(0);
        String line;
        while ((line = br.readLine()) != null) {
            var parts = line.split(" ");
            var dir = parts[0];
            var steps = Integer.parseInt(parts[1]);
            while (steps > 0) {
                switch (dir) {
                    case "U":
                        knots[0].x++;
                        break;
                    case "D":
                        knots[0].x--;
                        break;
                    case "L":
                        knots[0].y--;
                        break;
                    case "R":
                        knots[0].y++;
                        break;
                }
                steps--;
                for (var i = 1; i < 10 && !knots[i].isAdjacent(knots[i - 1]); i++) {
                    if (Math.abs(knots[i - 1].x - knots[i].x) == 2 && knots[i - 1].y == knots[i].y) {
                        if (knots[i - 1].x > knots[i].x) {
                            knots[i].x = knots[i - 1].x - 1;
                        } else {
                            knots[i].x = knots[i - 1].x + 1;
                        }
                    } else if (Math.abs(knots[i - 1].y - knots[i].y) == 2 && knots[i - 1].x == knots[i].x) {
                        if (knots[i - 1].y > knots[i].y) {
                            knots[i].y = knots[i - 1].y - 1;
                        } else {
                            knots[i].y = knots[i - 1].y + 1;
                        }
                    } else {
                        if (knots[i - 1].x > knots[i].x) {
                            knots[i].x++;
                        } else {
                            knots[i].x--;
                        }
                        if (knots[i - 1].y > knots[i].y) {
                            knots[i].y++;
                        } else {
                            knots[i].y--;
                        }
                    }
                    if (i == 9) {
                        visited.add((knots[i].x << 16) + knots[i].y);
                    }
                }
            }
        }
        System.err.println("Visited cells: " + visited.size());
        br.close();
    }
}
