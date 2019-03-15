import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {//1213_make_palindrome
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private void init() {
        this._st = new StringTokenizer("");
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }

    private void solve() {
        this.init();
        //int how_many = this.nextInt();
        StringBuilder sb = new StringBuilder();
        //for (int index_of_many = 0; index_of_many < how_many; ++index_of_many) {
        sb.append(do_times()).append("\n");
        //}
        System.out.println(sb.toString());
    }

    private String do_times() {
        char[] array_of_str = this.next().toCharArray();
        int[] character_of_index = new int[26];
        for (char now : array_of_str) {
            character_of_index[now - 0x41] += 1;
        }
        LinkedList<Character> aList = new LinkedList<>();

        int count_of_odd_number = 0;
        int index_of_input = 0;
        for (int index_of_char = 0; index_of_char < 26; ++index_of_char) {
            if (character_of_index[index_of_char] != 0) {
                for (int index_of_now = 0; index_of_now < character_of_index[index_of_char] / 2; ++index_of_now) {
                    aList.add(index_of_input++, (char) (index_of_char + 0x41));
                    if (count_of_odd_number > 0) {
                        aList.add(index_of_input + 1, (char) (index_of_char + 0x41));
                    } else {
                        aList.add(index_of_input, (char) (index_of_char + 0x41));
                    }
                }
                if (character_of_index[index_of_char] % 2 == 1) {
                    count_of_odd_number += 1;
                    if (character_of_index[index_of_char] == 1) {
                        aList.add(index_of_input, (char) (index_of_char + 0x41));
                    } else {
                        aList.add(index_of_input + 1, (char) (index_of_char + 0x41));
                    }
                }
            }
            if (count_of_odd_number > 1) {
                return "I'm Sorry Hansoo";
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char now : aList) {
            sb.append(now);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}