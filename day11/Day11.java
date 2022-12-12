import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.function.Function;

class Monkey {
    ArrayList<Long> items = new ArrayList<>();
    Function<Long, Long> method;
    int test;
    Monkey throwTrue;
    Monkey throwFalse;
    long inspections = 0;
    int number;

    void round() {
        while (!items.isEmpty()) {
            inspections++;
            items.set(0, items.get(0) % (Day11.gcd));
            items.set(0, method.apply(items.get(0)));

            if (items.get(0) % test == 0) {
                throwTrue.items.add(items.remove(0));
            } else {
                throwFalse.items.add(items.remove(0));
            }
        }
    }

    @Override
    public String toString() {
        return number + "";
    }
}

public class Day11 {

    static long gcd = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day11/input"));
        int size = 8;

        Monkey[] monkeys = new Monkey[size];
        for (int i = 0; i < size; i++) {
            monkeys[i] = new Monkey();
        }
        
        int number = 0;
        while (br.ready()) {
            br.readLine();
            String start = br.readLine();
            String operation = br.readLine();
            String test = br.readLine();
            String ifTrue = br.readLine();
            String ifFalse = br.readLine();
            br.readLine();

            var items = start.split(" ");
            for (int i = 4; i < items.length; i++) {
                items[i] = items[i].replaceAll(",", "");
                monkeys[number].items.add(Long.parseLong(items[i]));
            }

            var op = operation.split(" ");
            var sec = op[7].equals("old");
            if (op[6].equals("*"))
                monkeys[number].method = in -> in * (sec ? in : Integer.parseInt(op[7]));
            if (op[6].equals("+"))
                monkeys[number].method = in -> in + (sec ? in : Integer.parseInt(op[7]));

            var tes = test.split(" ");
            monkeys[number].test = Integer.parseInt(tes[5]);
            gcd *= Integer.parseInt(tes[5]);

            var ifT = ifTrue.split(" ");
            var ifF = ifFalse.split(" ");
            monkeys[number].throwTrue = monkeys[Integer.parseInt(ifT[9])];
            monkeys[number].throwFalse = monkeys[Integer.parseInt(ifF[9])];

            monkeys[number].number = number;
            number++;
        }

        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < size; j++) {
                monkeys[j].round();
            }
        }

        PriorityQueue<Long> greatest = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < size; i++) {
            greatest.add(monkeys[i].inspections);
        }

        System.out.println(greatest.poll() * greatest.poll());
    }
}