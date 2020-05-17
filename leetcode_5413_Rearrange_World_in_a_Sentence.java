import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // leetcode 5413 - Rearrange World in a Sentence
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
        String text = this.nextLine();
        System.out.println(this.arrangeWords(text));

        try {
            this.end();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public String arrangeWords(String text) {
        String lowerCaseStr = text.toLowerCase();
        StringTokenizer stringTokenizer = new StringTokenizer(lowerCaseStr);
        List<StrData> queueOfStr = new ArrayList<>();
        while (stringTokenizer.hasMoreTokens()) {
            queueOfStr.add(new StrData(stringTokenizer.nextToken().toLowerCase()));
        }

        Collections.sort(queueOfStr);

        StringBuilder stringBuilder = new StringBuilder();

        if (queueOfStr.isEmpty()) {
            return "";
        }

        StrData firstData = queueOfStr.remove(0);
        if (firstData != null) {
            String firstStr = firstData.str;
            if (firstStr.length() > 0) {
                String changeChar = firstStr.substring(0, 1);
                firstStr = firstStr.replaceFirst(changeChar, changeChar.toUpperCase());
            }
            stringBuilder.append(firstStr);
        }
        while (!queueOfStr.isEmpty()) {
            stringBuilder.append(" ").append(queueOfStr.remove(0).str);
        }

        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        new Main().run();
    }
}

class StrData implements Comparable<StrData> {
    String str;

    public StrData(String text) {
        this.str = text;
    }

    @Override
    public int compareTo(StrData o) {
        if (this.str.length() > o.str.length()) {
            return 1;
        } else if (this.str.length() == o.str.length()) {
            return 0;
        }
        return -1;
    }
}
