import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Directory {
    String localName;
    Directory parent;
    Map<String, Directory> children;
    private int size;

    Directory(String name, Directory parent) {
        this.localName = name;
        this.parent = parent;
        children = new HashMap<>();
        size = 0;
    }

    Directory addChildren(String dirName) {
        var dir = new Directory(dirName, this);
        children.put(dirName, dir);
        return dir;
    }

    void incrementSize(int size) {
        this.size += size;
        if (parent != null) {
            parent.incrementSize(size);
        }
    }

    int solve() {
        return (size <= 100_000 ? size : 0) +
                children.values()
                        .stream()
                        .mapToInt(x -> x.solve())
                        .sum();
    }
}

class Main_7_1 {
    public static void main(String[] args) throws IOException {
        var br = new BufferedReader(new FileReader("7.in"));
        String line = br.readLine();
        var rootDir = new Directory("/", null);
        var currentDir = rootDir;
        while (line != null) {
            var parts = line.split(" ");
            var cmd = parts[1];
            String arg = null;
            if (parts.length > 2) {
                arg = parts[2];
            }
            switch (cmd) {
                case "cd":
                    if (arg.equals("/")) {
                        currentDir = rootDir;
                    } else if (arg.equals("..")) {
                        currentDir = currentDir.parent;
                    } else {
                        currentDir = currentDir.children.get(arg);
                    }
                    line = br.readLine();
                    break;
                case "ls":
                    line = br.readLine();
                    while (line != null && (!line.startsWith("$"))) {
                        parts = line.split(" ");
                        var pathName = parts[1];
                        if (currentDir.children.get(pathName) == null) {
                            if (parts[0].equals("dir")) {
                                currentDir.addChildren(parts[1]);
                            } else {
                                currentDir.incrementSize(Integer.parseInt(parts[0]));
                            }
                        }
                        line = br.readLine();
                    }
                    break;
            }
        }
        System.out.println("The value is: " + rootDir.solve());
        br.close();
    }
}
