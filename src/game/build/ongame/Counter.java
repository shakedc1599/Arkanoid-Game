package game.build.ongame;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Simple class that is used for counting things, with: increase, decrease and
 * getValue methods.
 */
public class Counter {

    private int counter;

    /**
     * constructor
     * <p>
     * Constructor new counter.
     * </p>
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * Add number to current count.
     *
     * @param number type int.
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * Subtract number from current count.
     *
     * @param number type int.
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * @return current count, type int.
     */
    public int getValue() {
        return this.counter;
    }
}