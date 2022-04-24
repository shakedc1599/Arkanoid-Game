package game.build.calculators;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * game.build.calculators.CompareWithEpsilon class with: static compare method.
 * use to compare two double in the project.
 */
public class CompareWithEpsilon {

    private static final double EPSILON = 0.00001;

    /**
     * Check if two doubles are equaled or return the difference.
     *
     * @param a , type double.
     * @param b , type double.
     * @return the difference between the two numbers, 0 means equal.
     */
    public static double compare(double a, double b) {

        //if equal return 0;
        if (Math.abs(a - b) < EPSILON) {

            return 0;
        }
        //return the difference.
        return a - b;
    }
}
