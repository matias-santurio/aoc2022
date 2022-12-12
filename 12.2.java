import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

class Main_12_2 {

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("12.in"));
        var lines = new ArrayList<String>();
        String line;
        int sy = 0, sx = 0, ex = 0, ey = 0;
        var x = 0;
        while ((line = br.readLine()) != null) {
            lines.add(line);
            var indexS = line.indexOf('S');
            if (line.indexOf('S') != -1) {
                sy = indexS;
                sx = x;
            }
            var indexE = line.indexOf('E');
            if (line.indexOf('E') != -1) {
                ey = indexE;
                ex = x;
            }
            x++;
        }
        var map = lines.stream().map(s -> s.toCharArray()).toArray(char[][]::new);
        map[sx][sy] = 'a';
        map[ex][ey] = 'z';
        var visited = new HashSet<Integer>();
        var q = new ArrayDeque<Integer>();
        q.add((ex << 16) + ey);
        visited.add((ex << 16) + ey);
        var steps = 1;
        var maxX = map.length - 1;
        var maxY = map[0].length - 1;
        boolean found = false;
        outwhile: while (!found) {
            var newQ = new ArrayDeque<Integer>();
            while (!q.isEmpty()) {
                var elem = q.pop();
                var elemx = elem >> 16;
                var elemy = elem % (1 << 16);
                if (elemx > 0 && map[elemx - 1][elemy] - map[elemx][elemy] >= -1
                        && !visited.contains((elemx - 1 << 16) + elemy)) {
                    newQ.add((elemx - 1 << 16) + elemy);
                    visited.add((elemx - 1 << 16) + elemy);
                    if (map[elemx - 1][elemy] == 'a') {
                        break outwhile;
                    }
                }
                if (elemx < maxX && map[elemx + 1][elemy] - map[elemx][elemy] >= -1
                        && !visited.contains((elemx + 1 << 16) + elemy)) {
                    newQ.add((elemx + 1 << 16) + elemy);
                    visited.add((elemx + 1 << 16) + elemy);
                    if (map[elemx + 1][elemy] == 'a') {
                        break outwhile;
                    }
                }
                if (elemy > 0 && map[elemx][elemy - 1] - map[elemx][elemy] >= -1
                        && !visited.contains((elemx << 16) + elemy - 1)) {
                    newQ.add((elemx << 16) + elemy - 1);
                    visited.add((elemx << 16) + elemy - 1);
                    if (map[elemx][elemy - 1] == 'a') {
                        break outwhile;
                    }
                }
                if (elemy < maxY && map[elemx][elemy + 1] - map[elemx][elemy] >= -1
                        && !visited.contains((elemx << 16) + elemy + 1)) {
                    newQ.add((elemx << 16) + elemy + 1);
                    visited.add((elemx << 16) + elemy + 1);
                    if (map[elemx][elemy + 1] == 'a') {
                        break outwhile;
                    }
                }
            }
            steps++;
            q = newQ;
        }
        System.err.println("The number of neccesary steps is: " + steps);
        br.close();
    }
}
