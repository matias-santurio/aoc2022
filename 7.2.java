import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Directory {
    String localName;
    Directory parent;
    Map<String, Directory> children;
    int size;

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

    int solve(int targetSize) {
        var recVal = children.values()
                .stream()
                .mapToInt(x -> x.solve(targetSize))
                .min()
                .orElse(Integer.MAX_VALUE);
        if (size < targetSize) {
            return recVal;
        } else {
            return Math.min(size, recVal);
        }
    }
}

class Main_7_2 {
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
        var targetsize = 30_000_000 - (70_000_000 - rootDir.size);
        System.out.println("The value is: " + rootDir.solve(targetsize));
        br.close();
    }
}
