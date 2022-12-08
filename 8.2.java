import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Tree {
    int visibility;
    int visibliityTop;
    int visibilityBottom;
    int visibilityRight;
    int visibilityLeft;
    Tree leftBlocker;
    Tree topBlocker;
    Tree bottomBlocker;
    Tree rightBlocker;

    Tree(char visibility) {
        this.visibility = visibility - '0';
    }

    void setLeft(Tree left) {
        visibilityLeft = 1;
        leftBlocker = left;
        while (leftBlocker != null && leftBlocker.visibility < visibility) {
            visibilityLeft += leftBlocker.visibilityLeft;
            leftBlocker = leftBlocker.leftBlocker;
        }
    }

    void setTop(Tree top) {
        visibliityTop = 1;
        topBlocker = top;
        while (topBlocker != null && topBlocker.visibility < visibility) {
            visibliityTop += topBlocker.visibliityTop;
            topBlocker = topBlocker.topBlocker;
        }
    }

    void setBottom(Tree bottom) {
        visibilityBottom = 1;
        bottomBlocker = bottom;
        while (bottomBlocker != null && bottomBlocker.visibility < visibility) {
            visibilityBottom += bottomBlocker.visibilityBottom;
            bottomBlocker = bottomBlocker.bottomBlocker;
        }
    }

    void setRight(Tree right) {
        visibilityRight = 1;
        rightBlocker = right;
        while (rightBlocker != null && rightBlocker.visibility < visibility) {
            visibilityRight += rightBlocker.visibilityRight;
            rightBlocker = rightBlocker.rightBlocker;
        }
    }

    int getVisibility() {
        return visibilityBottom * visibilityLeft * visibilityRight * visibliityTop;
    }
}

class Main_8_2 {
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
                matrix[i][j].setLeft(matrix[i][j - 1]);
                matrix[i][j].setTop(matrix[i - 1][j]);
            }
            line = br.readLine();
        }
        for (var i = len - 2; i >= 0; i--) {
            for (var j = len - 2; j >= 0; j--) {
                matrix[i][j].setRight(matrix[i][j + 1]);
                matrix[i][j].setBottom(matrix[i + 1][j]);
            }
        }
        var result = Arrays.stream(matrix)
                .flatMap(x -> Arrays.stream(x))
                .mapToInt(x -> x.getVisibility())
                .max();
        System.out.println("The best score is: " + result.getAsInt());
        br.close();
    }
}
