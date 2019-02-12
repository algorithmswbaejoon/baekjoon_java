import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {//10989
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[10001];
        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine())]++;
        }

        br.close();

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= 10000; i++) {
            if (arr[i] > 0) {
                for (int j = 0; j < arr[i]; j++) {
                    sb.append(i + "\n");
                }
            }
        }

        System.out.println(sb.toString());
    }

}
