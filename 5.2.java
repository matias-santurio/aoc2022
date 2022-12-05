import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;
import java.util.stream.Collectors;

class Main_5_2 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("5.in"));
        var line = br.readLine();
        var stackCount = (line.length() + 1) / 4;
        var stacks = new ArrayList<Stack<Character>>(stackCount);
        for (var i = 0; i < stackCount; i++) {
            stacks.add(new Stack<Character>());
        }
        var linesStack = new ArrayDeque<String>();
        while (line.charAt(1) != '1') {
            linesStack.push(line);
            line = br.readLine();
        }
        for (var lineS : linesStack) {
            for (var i = 0; i < stackCount; i++) {
                var chr = lineS.charAt(i * 4 + 1);
                if (chr != ' ') {
                    stacks.get(i).push(chr);
                }
            }
        }
        br.readLine(); // Line with the stack numbering, which I didn't use
        line = br.readLine(); // Empty line;
        while (line != null) {
            var words = line.split(" ");
            var qty = Integer.parseInt(words[1]);
            var from = stacks.get(Integer.parseInt(words[3]) - 1);
            var to = stacks.get(Integer.parseInt(words[5]) - 1);
            var auxStack = new Stack<Character>();
            for (var i = 0; i < qty && !from.empty(); i++) {
                auxStack.push(from.pop());
            }
            while (!auxStack.isEmpty()) {
                to.push(auxStack.pop());
            }
            line = br.readLine();
        }
        var result = stacks.stream()
                .map(Stack<Character>::pop)
                .map(x -> x.toString())
                .collect(Collectors.joining());
        System.out.println("The top of each stack is: " + result);
        br.close();
    }
}
