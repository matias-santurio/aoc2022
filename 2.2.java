import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Main2_2 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("2.in"));
        var line = br.readLine();
        var score = 0;
        while (line != null) {
            var play = line.charAt(0);
            var myPlay = line.charAt(2);
            switch (myPlay) {
                case 'Z':
                    switch (play) {
                        case 'A':
                            score += 2;
                            break;
                        case 'B':
                            score += 3;
                            break;
                        case 'C':
                            score += 1;
                            break;
                    }
                    score += 6;
                    break;
                case 'Y':
                    switch (play) {
                        case 'A':
                            score += 1;
                            break;
                        case 'B':
                            score += 2;
                            break;
                        case 'C':
                            score += 3;
                            break;
                    }
                    score += 3;
                    break;
                case 'X':
                    switch (play) {
                        case 'A':
                            score += 3;
                            break;
                        case 'B':
                            score += 1;
                            break;
                        case 'C':
                            score += 2;
                            break;
                    }
                    break;
            }
            line = br.readLine();
        }
        System.out.println("Following the given strategy, the score would be: " + score);
        br.close();
    }
}
