import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Main _main = new Main();
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        String[] array = _main.makeThePostFix(str);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
        }
    }

    private int setThePriority(String str) { // 기호들의 연산 우선 순위 정하는 것
        int priority = 0;
        switch (str) {
            case "+":
            case "-":
                priority = 1;
                break;
            case "*":
            case "/":
                priority = 2;
                break;
            default:
                priority = -1;
                break;
        }
        return priority;
    }

    private String[] makeTheToken(String input) {
        Queue<Character> queue = new LinkedList<Character>();
        char[] str = input.toCharArray();
        int indexOfString = 0;
        String[] strQueue = new String[str.length];
        StringBuffer forQue = new StringBuffer();

        for (int index = 0; index < str.length; index++) {
            switch (str[index]) {
                case '/':
                case '*':
                case '+':
                case '-':
                case '(':
                case ')':
                    while (!queue.isEmpty()) {
                        forQue.append(Character.toString(queue.remove()));
                    }
                    if (!forQue.toString().equals("")) {
                        strQueue[indexOfString++] = forQue.toString();// 배열 추가
                        forQue = new StringBuffer();// 다시 버퍼 초기화
                    }
                    strQueue[indexOfString++] = Character.toString(str[index]);// 연산문 추가
                    break;
                case ' ':
                    continue;
                default:
                    queue.add(str[index]);
            }
        }
        while (!queue.isEmpty()) {// 마지막 부분처리
            forQue.append(Character.toString(queue.remove()));
        }
        if (str.length > indexOfString && !forQue.toString().equals("")) {
            strQueue[indexOfString++] = forQue.toString();
        }
        String[] returnArray = new String[indexOfString];
        System.arraycopy(strQueue, 0, returnArray, 0, indexOfString);
        return returnArray;
    }

    public String[] makeThePostFix(String expression) {
        String[] token = this.makeTheToken(expression);// 문자열 쪼개 주는 메소드
        String[] priorityStr = new String[token.length];// 결과 여기에 저장
        Stack<String> stack = new Stack<String>();
        int indexOfTheFinalStr = 0;
        for (int index = 0; index < token.length; index++) {
            if ("+-/*".contains(token[index])) {
                if (stack.isEmpty()) {
                    stack.push(token[index]);
                } else if (this.setThePriority(stack.peek()) >= this.setThePriority(token[index])) {
                    while (!stack.isEmpty() && this.setThePriority(stack.peek()) >= this.setThePriority(token[index])) {// pop
                        priorityStr[indexOfTheFinalStr++] = stack.pop();
                    }
                    stack.push(token[index]);// 마지막에 넣어주기
                } else {
                    stack.push(token[index]);
                }
            } else if (token[index].equals("(")) {
                stack.push(token[index]);
            } else if (token[index].equals(")")) {
                while (!stack.peek().equals("(")) {
                    priorityStr[indexOfTheFinalStr++] = stack.pop();
                }
                stack.pop();// 마지막으로 괄호 제거
            } else {
                priorityStr[indexOfTheFinalStr++] = token[index];
            }
        }

        while (!stack.isEmpty()) {
            priorityStr[indexOfTheFinalStr++] = stack.pop();
        }

        int count = 0;
        for (int i = 0; i < priorityStr.length; i++) {
            if (priorityStr[i] == null) {
                break;
            }
            count++;
        }

        String[] finalString = new String[count];
        System.arraycopy(priorityStr, 0, finalString, 0, finalString.length);

        return finalString;
    }
}