package boj_1644;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main { // 소수의 연속합
    private StringTokenizer _st;
    private BufferedReader _br;

    private String readLine() {
        try {
            return this._br.readLine();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private String nextLine() {
        while (!this._st.hasMoreTokens()) {
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    private void init() {
        this._br = new BufferedReader(new InputStreamReader(System.in));
        this._st = new StringTokenizer("");
    }

    private void destory() {
        try {
            if (this._br != null) {
                _br.close();
            }
        } catch (Exception ignored) {

        }
    }

    public void run() {
        init();
        solve();
        destory();
    }

    private void solve() {
        int sum = nextInt();
        int numberOfSum = 0;

        List<Integer> primeNumbers = primes(sum);

        int endIndex = 0;
        int partSum = 0;


        for (int startValue = 0; startValue < primeNumbers.size(); startValue++) {
            while (endIndex < primeNumbers.size() && partSum < sum) {
                partSum += primeNumbers.get(endIndex);
                endIndex++;
            }

            if (partSum == sum) {
                numberOfSum++;
            }

            if (startValue >= endIndex) {
                break;
            }

            partSum -= primeNumbers.get(startValue);
        }

        System.out.println(numberOfSum);
    }

    private List<Integer> primes(int maxVal) {
        List<Integer> primeNumbers = new ArrayList<>();
        for (int i = 2; i <= maxVal; i++) {
            if (isPrime(i)) {
                primeNumbers.add(i);
            }
        }

        return primeNumbers;
    }

    private boolean isPrime(int primeCandidateNumber) {
        if (primeCandidateNumber < 121) {
            if (primeCandidateNumber > 2 && primeCandidateNumber % 2 == 0) {
                return false;
            }

            if (primeCandidateNumber > 3 && primeCandidateNumber % 3 == 0) {
                return false;
            }

            if (primeCandidateNumber > 5 && primeCandidateNumber % 5 == 0) {
                return false;
            }

            return primeCandidateNumber <= 7 || primeCandidateNumber % 7 != 0;
        }

        for (int i = 2; i < (int) Math.sqrt(primeCandidateNumber) + 1; i++) {
            if (primeCandidateNumber % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}