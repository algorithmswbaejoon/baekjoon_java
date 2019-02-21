import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {//9466
    private StringTokenizer _st;
    private BufferedReader _br;
    private Set _set;//지금까지 방문한 index들을 모두 가지고 있음

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
        StringBuilder sb = new StringBuilder();
        int how_many_times = this.nextInt();
        for (int index = 0; index < how_many_times; index++) {
            this.teamp_project(sb);
        }
        System.out.println(sb.toString());
    }

    private void teamp_project(StringBuilder sb) {
        this._set = new TreeSet();
        int number_of_students = this.nextInt();
        int[] student_choice = new int[number_of_students + 1];
        boolean[] is_cycle = new boolean[number_of_students];//싸이클 여부 판별
        boolean[] visited = new boolean[number_of_students];//방문 여부 판별

        for (int index_student = 1; index_student <= number_of_students; index_student++) {
            student_choice[index_student] = this.nextInt();
        }

        for (int index_student = 1; index_student <= number_of_students; index_student++) {
            this.dfs(index_student, visited, is_cycle, student_choice);
        }

        int count = 0;
        for (int index = 0; index < number_of_students; index++) {
            if (!is_cycle[index]) {
                count++;
            }
        }
        sb.append(count).append("\n");
    }

    private void dfs(int now_student, boolean[] visited, boolean[] is_cycle, int[] student_choice) {
        if (!visited[now_student - 1]) {//방문 유무 판별
            visited[now_student - 1] = true;
            this._set.add(now_student);
            int next_student = student_choice[now_student];
            if (!visited[next_student - 1]) {//미 방문 시 방문
                this.dfs(next_student, visited, is_cycle, student_choice);
            } else {//방문 했을 경우 cycle판별
                if (!is_cycle[next_student - 1] && next_student == student_choice[next_student]) {//자기자신 선택
                    is_cycle[next_student - 1] = true;
                }
                if (!is_cycle[next_student - 1] && this._set.contains(next_student)) {//cycle 존재
                    is_cycle[now_student - 1] = true;
                    for (int index_of_cycle = next_student; index_of_cycle != now_student; index_of_cycle = student_choice[index_of_cycle]) {
                        is_cycle[index_of_cycle - 1] = true;
                    }
                }//size가 0일 경우 cycle 형성 실패
                this._set = new TreeSet();//
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
