package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct inputs (passwords) to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            IntList myList = IntList.of(0, 9, 3, 0, 8);
            b.phase1(myList); // Figure this out too
        }
        if (phase >= 2) {
            String s = "-81201430 ";
            String myPassword = s.repeat(10000);

            b.phase2(myPassword);
        }
    }
}
