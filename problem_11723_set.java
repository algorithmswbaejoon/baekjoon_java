import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main { // problem 11723 set
    private StringTokenizer stringTokenizer;
    private final BufferedReader bufferedReader;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }


    private void end() throws IOException {
        this.bufferedReader.close();
    }

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer(Objects.requireNonNull(this.nextLine()));
        }
        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private void run() {
        Set<Integer> set = new HashSet<>();
        Set<Integer> allSet = new HashSet<>();
        for (int index = 1; index <= 20; index += 1) {
            allSet.add(index);
        }

        int numberOfTime = this.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < numberOfTime; index += 1) {
            String instruction = this.next();
            if (instruction.equals("all")) {
                set = new HashSet<>(allSet);
            } else if (instruction.equals("empty")) {
                set = new HashSet<>();
            } else if (instruction.equals("add")) {
                set.add(this.nextInt());
            } else if (instruction.equals("remove")) {
                set.remove(this.nextInt());
            } else if (instruction.equals("check")) {
                if (set.contains(this.nextInt())) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else {
                int now = this.nextInt();
                if (set.contains(now)) {
                    set.remove(now);
                } else {
                    set.add(now);
                }
            }
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
