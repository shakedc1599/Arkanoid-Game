package game.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.build.calculators.SpriteCollection;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * The CountdownAnimation will display the given gameScreen, for numOfSeconds
 * seconds, and on top of them it will show a countdown from countFrom back to 1.
 * CountdownAnimation implements Animation with doOneFrame and shouldStop methods.
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int count;

    /**
     * Constructor
     * <p>
     * The function create new CountdownAnimation.
     *
     * @param numOfSeconds type double.
     * @param countFrom    type int.
     * @param gameScreen   type SpriteCollection.
     *                     </p>
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {

        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.count = countFrom;
    }

    /**
     * Do one frame of animation.
     *
     * @param d type DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        Sleeper sleeper = new Sleeper();

        //Draw all the sprite.
        gameScreen.drawAllOn(d);

        //Check if the countdown is 0.
        if (this.count == 0) {

            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2 - 70, d.getHeight() / 2, "GO!", 80);

        } else if (this.count > 0) {

            d.setColor(Color.BLACK);
            d.drawText(d.getWidth() / 2 - 25, d.getHeight() / 2, Integer.toString(this.count), 80);
        }

        //With 2 seconds between every frame except the first.
        if (this.count != countFrom) {
            sleeper.sleepFor((long) (numOfSeconds / countFrom * 1000));
        }

        this.count -= 1;
    }

    /**
     * Check the stopping conditions.
     *
     * @return if stop or not, type boolean.
     */
    @Override
    public boolean shouldStop() {

        //Check if the countdown is less then 0.
        return this.count < -1;
    }
}
