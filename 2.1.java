import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main2_1 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("2.in"));
        var line = br.readLine();
        var score = 0;
        while (line != null) {
            var play = line.charAt(0);
            var myPlay = line.charAt(2);
            switch (myPlay) {
                case 'X':
                    switch (play) {
                        case 'A':
                            score += 3;
                            break;
                        case 'C':
                            score += 6;
                            break;
                    }
                    score += 1;
                    break;
                case 'Y':
                    switch (play) {
                        case 'A':
                            score += 6;
                            break;
                        case 'B':
                            score += 3;
                            break;
                    }
                    score += 2;
                    break;
                case 'Z':
                    switch (play) {
                        case 'B':
                            score += 6;
                            break;
                        case 'C':
                            score += 3;
                            break;
                    }
                    score += 3;
                    break;
            }
            line = br.readLine();
        }
        System.out.println("Following the given strategy, the score would be: " + score);
        br.close();
    }
}
