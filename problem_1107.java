import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {// 1107
    private BufferedReader _br;
    private StringTokenizer _token;
    private boolean[] brokenBotton = new boolean[10];

    private void init() {
        this._token = new StringTokenizer("");
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
        while (!this._token.hasMoreTokens()) {
            this._token = new StringTokenizer(this.readLine());
        }
        return this._token.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }// 인트형으로 하기

    private int canChangeTheChennel(int channel) { // 가능하면 수의 길이를 리턴, 불가능하면 0을 리턴
        int length = 0;// 체널의 길이

        if (channel == 0) {// 입력된 체널이 0인 경우
            return this.brokenBotton[0] ? 0 : 1;
        }

        while (channel > 0) {
            if (this.brokenBotton[channel % 10]) {
                return 0;// 못하는 숫자는 length를 0으로 return 해준다.
            }
            length += 1;
            channel /= 10;
        }
        return length;
    }

    private void solve() {
        this.init();
        int changeNumber = this.nextInt();
        int numberOfBrokenBottons = this.nextInt();

        for (int i = 0; i < numberOfBrokenBottons; i++) {
            this.brokenBotton[this.nextInt()] = true; // 망가진 버튼을 true로 하고 아닌 것을 false를 return 한다.
        }

        int result = changeNumber - 100;
        if (result < 0) {
            result = -result;
        } // + - 만 눌렀을 때 버튼 누르는 횟수

        int biggerNumber = -10;
        for (int tempChangeNumber = changeNumber; tempChangeNumber <= 1000000; tempChangeNumber++) {
            int chanel = tempChangeNumber;
            int length = this.canChangeTheChennel(chanel);
            if (length > 0) {
                int howManyPressPlus = chanel - changeNumber;// +몇회 니지 구한다
                biggerNumber = howManyPressPlus + length;
                break;
            }
        }
        if (biggerNumber > 0) {
            result = result > biggerNumber ? biggerNumber : result;
        }
        int smallerNumber = -10;
        for (int tempChangeNumber = changeNumber; tempChangeNumber >= 0; tempChangeNumber--) {// 이동할 수 있는 모든 체널
            int chanel = tempChangeNumber;
            int length = this.canChangeTheChennel(chanel); // 길이를 잰다.
            if (length > 0) {
                int press = changeNumber - chanel; // -몇회 인지 구한다
                smallerNumber = press + length;
                break;
            }
        }
        if (smallerNumber > 0) {
            result = result > smallerNumber ? smallerNumber : result;
        }

        System.out.println(result);

    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
