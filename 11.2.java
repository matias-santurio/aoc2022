import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

interface Operator {
    long apply(long a, long b);
}

class SumOperator implements Operator {
    public long apply(long a, long b) {
        return a + b;
    }
}

class MultOperator implements Operator {
    public long apply(long a, long b) {
        return a * b;
    }
}

abstract class Operation {
    Operator op;

    abstract long apply(long value);
}

class OperationWithOld extends Operation {
    OperationWithOld(Operator operator) {
        op = operator;
    }

    long apply(long value) {
        return op.apply(value, value);
    }
}

class OperationWithValue extends Operation {
    private int operand;

    OperationWithValue(Operator operator, int operand) {
        this.op = operator;
        this.operand = operand;
    }

    long apply(long value) {
        return op.apply(value, operand);
    }
}

class Monkey {
    Operation operation;
    List<Long> items;
    int testCondition;
    int testTrue;
    int testFalse;
    int inspectedItems = 0;

    public Monkey(Operation operation, List<Long> items, int testCondition,
            int testTrue, int testFalse) {
        this.operation = operation;
        this.items = items;
        this.testCondition = testCondition;
        this.testTrue = testTrue;
        this.testFalse = testFalse;
    }

    int test(long value) {
        return value % testCondition == 0 ? testTrue : testFalse;
    }
}

class Main_11_1 {

    static List<Long> parseItems(String line) {
        var parts = line.trim().split(" ");
        var list = new ArrayList<Long>();
        for (var i = 2; i < parts.length; i++) {
            list.add(Long.parseLong(parts[i].split(",")[0]));
        }
        return list;
    }

    static Operation parseOperation(String line) {
        var parts = line.trim().split(" ");
        Operator op;
        if (parts[4].equals("*")) {
            op = new MultOperator();
        } else {
            op = new SumOperator();
        }
        if (parts[5].equals("old")) {
            return new OperationWithOld(op);
        } else {
            return new OperationWithValue(op, Integer.parseInt(parts[5]));
        }
    }

    static int parseTestCondition(String line) {
        return Integer.parseInt(line.trim().split(" ")[3]);
    }

    static int parseTest(String line) {
        return Integer.parseInt(line.trim().split(" ")[5]);
    }

    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("11.in"));
        var monkeys = new ArrayList<Monkey>();
        while (br.readLine() != null) {
            var items = parseItems(br.readLine());
            var operation = parseOperation(br.readLine());
            monkeys.add(new Monkey(operation, items, parseTestCondition(br.readLine()),
                    parseTest(br.readLine()),
                    parseTest(br.readLine())));
            br.readLine();
        }
        var modulus = monkeys.stream().mapToInt(x -> x.testCondition).reduce((x, y) -> x * y).getAsInt();
        System.out.println(modulus);
        for (var i = 0; i < 10000; i++) {
            for (var j = 0; j < monkeys.size(); j++) {
                var monkey = monkeys.get(j);
                for (var item : monkey.items) {
                    monkey.inspectedItems++;
                    var newWorryLevel = monkey.operation.apply(item) % modulus;
                    if (newWorryLevel < 0) {
                        System.out.println("aa");
                    }
                    var newMonkey = monkey.test(newWorryLevel);
                    monkeys.get(newMonkey).items.add(newWorryLevel);
                    monkey.items = new ArrayList<>();
                }
            }
        }
        monkeys.sort((x, y) -> y.inspectedItems - x.inspectedItems);
        System.err.println("The level of monkey business is: "
                + monkeys.get(0).inspectedItems + " " + monkeys.get(1).inspectedItems);
        br.close();
    }
}
