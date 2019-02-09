import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {// 1068
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
            this._st = new StringTokenizer(this.readLine());
        }
        return this._st.nextToken();
    }

    private int nextInt() {
        return Integer.parseInt(this.nextLine());
    }

    private void sovle() {
        this.init();
        Queue<Integer> q1 = new LinkedList<Integer>();
        int numberOfsubTrees = this.nextInt();// 개수 입력
        int parentVertex[] = new int[numberOfsubTrees];// 부모로 가는거 탐색
        int findChild[] = new int[numberOfsubTrees];// 자식 노드 개수를 저장하는곳
        for (int index = 0; index < numberOfsubTrees; index++) {
            q1.add(index);// 모두 다 leaf라고 가정
            findChild[index] = 0;
        }

        for (int count = 0; count < numberOfsubTrees; count++) {
            parentVertex[count] = this.nextInt();
            if (parentVertex[count] != -1) {
                findChild[parentVertex[count]]++;
            }
        }

        for (int index = 0; index < numberOfsubTrees; index++) {
            if (findChild[index] != 0) {
                this.removeQInt(q1, index);
            }
        }

        int deleteNode = this.nextInt();// 제거 해야 하는 노드 찾기
        this.removeQInt(q1, deleteNode);
        if (parentVertex[deleteNode] == -1) {
            System.out.println(0);
            return;
        } else if (findChild[parentVertex[deleteNode]] - 1 == 0) {
            q1.add(parentVertex[deleteNode]);
        }
        parentVertex[deleteNode] = -2;// 부모로 못가게 막음

        Queue<Integer> q2 = new LinkedList<Integer>();

        Iterator<Integer> iterator = q1.iterator();

        while (iterator.hasNext()) {
            q2.add(iterator.next());
        }

        iterator = q2.iterator();

        while (iterator.hasNext()) {
            int removeElement = iterator.next();
            int findElement = this.findTheParentOf(parentVertex, removeElement);
            if (findElement >= 0) {
                this.removeQInt(q1, findElement);
            }
        }
        System.out.println(q1.size());
    }

    private void removeQInt(Queue<Integer> q1, int vertex) {
        try {
            q1.remove(vertex);
        } catch (Exception e) {

        }
    }

    private int findTheParentOf(int[] array, int deleteElement) {
        int temp = deleteElement;
        while (true) {
            if (array[temp] == -1) {
                return -1;
            } else if (array[temp] == -2) {
                return deleteElement;
            } else {
                temp = array[temp];
            }
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Main main = new Main();
        main.sovle();
    }
}