import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1036번 문제
public class Main {
    StringTokenizer _st;
    BufferedReader _br;

    private void init() {
        this._st = new StringTokenizer("");
        this._br = new BufferedReader(new InputStreamReader(System.in));
    }

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            return null;
        }
    }

    private String nextLine() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    public void run() {
        this.init();
        int firstNum = this.nextInt();
        if (firstNum <= 0 || firstNum > 50) {
            return;// 예외처리
        }

        String[] datas = new String[firstNum];

        for (int i = 0; i < firstNum; i++) {
            datas[i] = this.nextLine();
            if (i != 0 && datas[i].length() != datas[0].length()) {
                return;
            }
        }
        System.out.println(this.findTheFileName(datas));
    }

    private String findTheFileName(String[] array) {
        char[] arrayOfStrToChar = array[0].toCharArray();
        for (int index = 0; index < array.length; index++) {
            char[] temp = array[index].toCharArray();
            for (int i = 0; i < temp.length; i++) {
                if (arrayOfStrToChar[i] != temp[i]) {
                    arrayOfStrToChar[i] = '?';
                }
            }
        }
        String returnStr = new String(arrayOfStrToChar, 0, arrayOfStrToChar.length);
        return returnStr;
    }

    public static void main(String[] args) {
        Main run = new Main();
        run.run();
    }
}