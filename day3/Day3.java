import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class Day3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input"));

        int sum = 0;
        int ans = 0;
        int j = 0;
        HashMap<Character, Integer> hs = new HashMap<>();
        while (br.ready()) {
            if (j % 3 == 0) {
                for (var i : hs.entrySet()) {
                    if (i.getValue() == 3) {
                        char ca = i.getKey();
                        ans += ca >= 'a' ? ca - 'a' + 1 : ca - 'A' + 27;
                    }
                }
                hs.clear();
            }

            String line = br.readLine();
            int l = line.length();

            HashSet<Character> c = new HashSet<>();
            HashSet<Character> c2 = new HashSet<>();
            for (int i = 0; i < l / 2; i++) {
                c.add(line.charAt(i));
                c2.add(line.charAt(i));
            }
            for (int i = l / 2; i < l; i++) {
                c2.add(line.charAt(i));
                if (c.contains(line.charAt(i))) {
                    char ca = line.charAt(i);
                    sum += ca >= 'a' ? ca - 'a' + 1 : ca - 'A' + 27;
                    c.remove(ca);
                }
            }

            for (var i : c2) {
                hs.put(i, hs.getOrDefault(i, 0) + 1);
            }

            j++;
        }

        if (j % 3 == 0) {
            for (var i : hs.entrySet()) {
                if (i.getValue() == 3) {
                    char ca = i.getKey();
                    ans += ca >= 'a' ? ca - 'a' + 1 : ca - 'A' + 27;
                }
            }
            hs.clear();
        }

        System.out.println(sum);
        System.out.println(ans);
    }
}
