import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Tree {
    int visibility;
    int visiblityTop = -1;
    int visibilityBottom = -1;
    int visibilityRight = -1;
    int visibilityLeft = -1;

    Tree(char visibility) {
        this.visibility = visibility - '0';
    }

    boolean isVisible() {
        return visibility > visiblityTop || visibility > visibilityBottom ||
                visibility > visibilityRight
                || visibility > visibilityLeft;
    }
}

class Main_8_1 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("8.in"));
        String line = br.readLine();
        var len = line.length();
        var matrix = new Tree[len][len];
        var charsFstRow = line.toCharArray();
        for (var i = 0; i < len; i++) {
            matrix[0][i] = new Tree(charsFstRow[i]);
        }
        line = br.readLine();
        for (var i = 1; i < len; i++) {
            var charsRow = line.toCharArray();
            matrix[i][0] = new Tree(charsRow[0]);
            matrix[i][len - 1] = new Tree(charsRow[len - 1]);
            for (var j = 1; j < len; j++) {
                matrix[i][j] = new Tree(charsRow[j]);
                matrix[i][j].visibilityLeft = Math.max(matrix[i][j - 1].visibilityLeft,
                        matrix[i][j - 1].visibility);
                matrix[i][j].visiblityTop = Math.max(matrix[i - 1][j].visiblityTop, matrix[i
                        - 1][j].visibility);
                var k = j - 1;
                while (k > 0 && matrix[i][k].visibilityRight < matrix[i][j].visibility) {
                    matrix[i][k].visibilityRight = matrix[i][j].visibility;
                    k--;
                }
                k = i - 1;
                while (k > 0 && matrix[k][j].visibilityBottom < matrix[i][j].visibility) {
                    matrix[k][j].visibilityBottom = matrix[i][j].visibility;
                    k--;
                }
            }
            line = br.readLine();
        }
        var result = Arrays.stream(matrix)
                .flatMap(x -> Arrays.stream(x))
                .filter(x -> x.isVisible())
                .count();
        System.out.println("The number of visible trees is: " + result);
        br.close();
    }
}
