import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {//4179 fire
    private BufferedReader bufferedReader;
    private StringTokenizer stringTokenizer;

    private int[] arrayOfRow = {1, -1, 0, 0};
    private int[] arrayOfCol = {0, 0, 1, -1};

    private String nextLine() {
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private String next() {
        while (!this.stringTokenizer.hasMoreTokens()) {
            this.stringTokenizer = new StringTokenizer((this.nextLine()));
        }
        return this.stringTokenizer.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.next());
    }

    private void init() {
        this.stringTokenizer = new StringTokenizer("");
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void end() {
        try {
            this.bufferedReader.close();
        } catch (IOException e) {
            return;
        }
    }

    public void solve() {
        this.init();

        int numberOfRow = this.nextInt();
        int numberOfCol = this.nextInt();

        Queue<Point> personQueue = new LinkedList<>();
        Queue<Point> fireQueue = new LinkedList<>();

        char[][] space = new char[numberOfRow][numberOfCol];
        for (int indexOfInputRow = 0; indexOfInputRow < numberOfRow; ++indexOfInputRow) {
            String inputData = this.nextLine();
            char[] datas = inputData.toCharArray();
            for (int indexOfCol = 0; indexOfCol < numberOfCol; ++indexOfCol) {
                space[indexOfInputRow][indexOfCol] = datas[indexOfCol];
                if (datas[indexOfCol] == 'F') {
                    fireQueue.add(new Point(indexOfInputRow, indexOfCol));
                }
                if (datas[indexOfCol] == 'J') {
                    personQueue.add(new Point(indexOfInputRow, indexOfCol));
                }
            }
        }// 입력부

        int pathNumber = 0;
        while (true) {
            ++pathNumber;

            int sizeOfFileWay = fireQueue.size();

            for (int index = 0; index < sizeOfFileWay; ++index) {
                Point nowPointOfFire = fireQueue.poll();
                for (int indexOfArray = 0; indexOfArray < 4; ++indexOfArray) {
                    int changedRow = nowPointOfFire.x + arrayOfRow[indexOfArray];
                    int changedCol = nowPointOfFire.y + arrayOfCol[indexOfArray];
                    if (0 <= changedRow && changedRow < numberOfRow && 0 <= changedCol && changedCol < numberOfCol
                            && (space[changedRow][changedCol] == '.' || space[changedRow][changedCol] == 'J')) {
                        space[changedRow][changedCol] = 'F';
                        fireQueue.add(new Point(changedRow, changedCol));
                    }
                }
            }

            int sizeOfPersonWay = personQueue.size();

            for (int index = 0; index < sizeOfPersonWay; ++index) {
                Point nowPointOfPerson = personQueue.poll();
                for (int indexOfArray = 0; indexOfArray < 4; ++indexOfArray) {
                    int changedRow = nowPointOfPerson.x + arrayOfRow[indexOfArray];
                    int changedCol = nowPointOfPerson.y + arrayOfCol[indexOfArray];

                    if (changedRow < 0 || changedRow >= numberOfRow || changedCol < 0 || changedCol >= numberOfCol) {
                        System.out.println(pathNumber);
                        this.end();
                        return;
                    }
                    if (space[changedRow][changedCol] == '.') {
                        space[changedRow][changedCol] = 'J';
                        personQueue.add(new Point(changedRow, changedCol));
                    }
                }
            }
            if (personQueue.size() == 0) {
                System.out.println("IMPOSSIBLE");
                break;
            }
        }
        this.end();
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.solve();
    }
}
