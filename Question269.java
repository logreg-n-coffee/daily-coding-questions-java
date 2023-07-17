/**
 * You are given an string representing the initial conditions of some dominoes.
 * Each element can take one of three values:
 * 
 * L, meaning the domino has just been pushed to the left,
 * R, meaning the domino has just been pushed to the right, or
 * ., meaning the domino is standing still.
 * 
 * Determine the orientation of each tile after the dominoes stop falling. Note
 * that if a domino receives a force from the left and right side
 * simultaneously, it will remain upright.
 * 
 * For example, given the string .L.R....L, you should return LL.RRRLLL.
 * 
 * Given the string ..R...L.L, you should return ..RR.LLLL.
 */

public class Question269 {
    public static void main(String[] args) {
        System.out.println(pushDominoes(".L.R....L")); // should return LL.RRRLLL
        System.out.println(pushDominoes("..R...L.L")); // should return ..RR.LLLL
    }

    /**
     * For each position, it first calculates the net force from the right and then
     * from the left. If the net force is greater than 0, the domino falls to the
     * right (R). If the net force is less than 0, the domino falls to the left (L).
     * If the net force is 0, the domino remains standing (.)
     * 
     * @param dominoes
     * @return Determine the orientation of each tile when the dominoes stop falling
     */
    public static String pushDominoes(String dominoes) {
        int n = dominoes.length();
        int[] forces = new int[n];

        // forces from the right
        // force is used to simulate the domino effect.
        int force = 0;
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == 'R') {
                force = n;
            } else if (dominoes.charAt(i) == 'L') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0); // force is decreasing but will not be negative
            }
            forces[i] += force;
        }

        // forces from the left
        force = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                force = n;
            } else if (dominoes.charAt(i) == 'R') {
                force = 0;
            } else {
                force = Math.max(force - 1, 0); // force is decreasing but will not be negative
            }
            forces[i] -= force;
        }

        StringBuilder sb = new StringBuilder();

        // compare the forces from both sides. If the force from the right ('R') is
        // stronger, that domino will fall to the right, and vice versa. If the forces
        // are equal, the domino remains standing.
        for (int f : forces)
            sb.append(f > 0 ? 'R' : f < 0 ? 'L' : '.');
        return sb.toString();
    }
}
