import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Day5 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day5/input"));

        Stack<Character>[] stacks = new Stack[9];
        String[] input = new String[8];
        for (int i = 0; i < 8; i++) {
            input[i] = br.readLine();
        }

        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 9; j++) {
                if (stacks[j] == null) {
                    stacks[j] = new Stack<>();
                }
                char c = input[i].charAt(1 + 4 * j);
                if (c != ' ') {
                    stacks[j].push(c);
                }
            }
        }

        br.readLine();
        br.readLine();

        while (br.ready()) {
            String line = br.readLine();
            var elems = line.split(" ");
            int num = Integer.parseInt(elems[1]);
            int start = Integer.parseInt(elems[3]) - 1;
            int end = Integer.parseInt(elems[5]) - 1;

            Stack<Character> popper = new Stack<>();
            for (int i = 0; i < num; i++) {
                char popped = stacks[start].pop();
                popper.push(popped);
            }
            for (int i = 0; i < num; i++) {
                char popped = popper.pop();
                stacks[end].push(popped);
            }

        }

        StringBuilder ans1 = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            ans1.append(stacks[i].pop());
        }

        System.out.println(ans1.toString());
    }
}
