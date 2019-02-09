import java.util.Scanner;

public class problem_1003_otherway {//1003_other_way
    private int _fiboForOne;
    private int _fiboForZero;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        problem_1003_otherway m1 = new problem_1003_otherway();
        int howMuch = input.nextInt();
        int[] listOfTheIndexes;
        listOfTheIndexes = new int[howMuch];
        for (int i = 0; i < howMuch; i++) {
            int tempNumber = input.nextInt();
            if (tempNumber < 41 && tempNumber >= 0) {
                listOfTheIndexes[i] = tempNumber;
                m1.searchOneAndZero(listOfTheIndexes);
            }
        }
    }

    public problem_1003_otherway() {
        this.setOne(0);
        this.setZero(0);
    }

    private void setOne(int number) {
        this._fiboForOne = number;
    }

    private void setZero(int number) {
        this._fiboForZero = number;
    }

    private int one() {
        return this._fiboForOne;
    }

    private int zero() {
        return this._fiboForZero;
    }

    private int fibo(int number) {
        if (number == 0) {
            this.setZero(this.zero() + 1);
            return 0;
        } else if (number == 1) {
            this.setOne(this.one() + 1);
            return 1;
        }
        return this.fibo(number - 1) + this.fibo(number - 2);
    }

    public void searchOneAndZero(int[] whatNum) {
        for (int index = 0; index < whatNum.length; index++) {
            if (whatNum[index] <= 40 && whatNum[index] >= 0) {
                this.fibo(whatNum[index]);
                System.out.println(this.zero() + " " + this.one());
                this.setOne(0);
                this.setZero(0);
            }
        }
    }
}
