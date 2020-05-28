import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // leetcode 5414 - People Whose List of Favorite Companies Is Not a Subset of Another List
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
        String[][] strList = new String[][]{{"leetcode", "google", "facebook"}, {"google", "microsoft"}, {"google", "facebook"}, {"google"}, {"amazon"}};
        List<List<String>> list = new ArrayList<>();
        for (String[] array : strList) {
            List<String> innerList = new ArrayList<>(Arrays.asList(array));
            list.add(innerList);
        }
        System.out.println(this.peopleIndexes(list));

        try {
            this.end();
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Integer> nonSubSetNumbers = new ArrayList<>();

        for (int index = 0; index < favoriteCompanies.size(); index++) {
            boolean isContains = false;
            for (int indexOfNext = 0; indexOfNext < favoriteCompanies.size(); indexOfNext++) {
                if (indexOfNext == index) {
                    continue;
                }
                Set<String> xData = new HashSet<>(favoriteCompanies.get(indexOfNext));
                Set<String> yData = new HashSet<>(favoriteCompanies.get(index));
                if (xData.containsAll(yData)) {
                    isContains = true;
                    break;
                }
            }
            if (!isContains) {
                nonSubSetNumbers.add(index);
            }
        }

        return nonSubSetNumbers;
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
