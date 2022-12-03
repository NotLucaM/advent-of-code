import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input"));

        int score = 0;
        while (br.ready()) {
            String line = br.readLine();
            char th = line.charAt(0);
            char yo = line.charAt(2);

            switch (yo) {
                case 'X': // lose
                    switch (th) {
                        case 'A': // rock
                            yo = 'Z';
                            break;
                        case 'B': // paper
                            yo = 'X';
                            break;
                        case 'C': // scissors
                            yo = 'Y';
                            break;
                    }
                    break;
                case 'Y': // lose
                    switch (th) {
                        case 'A': // rock
                            yo = 'X';
                            break;
                        case 'B': // paper
                            yo = 'Y';
                            break;
                        case 'C': // scissors
                            yo = 'Z';
                            break;
                    }
                    break;
                case 'Z': // win
                    switch (th) {
                        case 'A': // rock
                            yo = 'Y';
                            break;
                        case 'B': // paper
                            yo = 'Z';
                            break;
                        case 'C': // scissors
                            yo = 'X';
                            break;
                    }
                    break;
            }

            switch (th) {
                case 'A': // rock
                    switch (yo) {
                        case 'X': // rock
                            score += 4;
                            break;
                        case 'Y': // paper
                            score += 8;
                            break;
                        case 'Z': // scissors
                            score += 3;
                            break;
                    }
                    break;
                case 'B': // p
                    switch (yo) {
                        case 'X': // r
                            score += 1;
                            break;
                        case 'Y': // p
                            score += 5;
                            break;
                        case 'Z': // s
                            score += 9;
                            break;
                    }
                    break;

                case 'C': // s
                    switch (yo) {
                        case 'X': // r
                            score += 7;
                            break;
                        case 'Y': //
                            score += 2;
                            break;
                        case 'Z':
                            score += 6;
                            break;
                    }
                    break;
            }
        }

        System.out.println(score);
    }
}
