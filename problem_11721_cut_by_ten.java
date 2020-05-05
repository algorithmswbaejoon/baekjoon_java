import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main { // problem 11721 cut by ten
    private StringTokenizer stringTokenizer;
    private BufferedReader bufferedReader;

    public Main() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
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

    private boolean end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    public void solve() {
        String strLineData = this.next();
        StringBuilder stringBuilder = new StringBuilder();
        int index = 10;
        while (index < strLineData.length()) {
            stringBuilder.append(strLineData.substring(index - 10, index)).append("\n");
            index += 10;
        }
        int lastIndex = index - 10;
        if (lastIndex < strLineData.length()) {
            stringBuilder.append(strLineData.substring(lastIndex)).append("\n");
        }
        System.out.println(stringBuilder.toString());

        if (!this.end()) {
            System.out.println("program error");
        }
    }

    public static void main(String[] args) {
        new Main().solve();
    }
}
