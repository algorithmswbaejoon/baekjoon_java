import java.util.Scanner;

public class Main{
    public static void main(String[] argc) {
        int[][] stored = new int[41][2];
        stored[0][0] = 1;
        stored[0][1] = 0;
        stored[1][0] = 0;
        stored[1][1] = 1;

        Scanner input = new Scanner(System.in);
        int count = input.nextInt();

        int[] search = new int[count];

        for (int i = 0; i < search.length; ++i) {
            search[i] = input.nextInt();
        }

        for(int i = 2; i <= 40; i++) {
            stored[i][0] = stored[i-2][0] + stored[i-1][0];
            stored[i][1] = stored[i-2][1] + stored[i-1][1];
        }

        for(int i = 0; i < search.length; i++) {
            System.out.println(stored[search[i]][0] + " " + stored[search[i]][1]);
        }
    }
}