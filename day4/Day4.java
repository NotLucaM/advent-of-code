import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("day4/input"));

        int ans1 = 0;
        int ans2 = 0;
        while (br.ready()) {
            String line = br.readLine();

            String[] p = line.split(",");
            String[] e1 = p[0].split("-");
            String[] e2 = p[1].split("-");
            int e1s = Integer.parseInt(e1[0]);
            int e1e = Integer.parseInt(e1[1]);
            int e2s = Integer.parseInt(e2[0]);
            int e2e = Integer.parseInt(e2[1]);

            if (e1s <= e2s && e1e >= e2e) {
                ans1++;
            } else if (e2s <= e1s && e2e >= e1e) {
                ans1++;
            }

            if (!(e1s > e2e || e1e < e2s)) {
                ans2++;
            }
        }

        System.out.println(ans1);
        System.out.println(ans2);
    }
}
