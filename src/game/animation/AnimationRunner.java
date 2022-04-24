package game.animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * AnimationRunner to run a specific animation with the run method.
 */
public class AnimationRunner {

    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;


    /**
     * Constructor
     * <p>
     * The function create new AnimationRunner.
     *
     * @param gui             type GUI.
     * @param framesPerSecond type int.
     * @param sleeper         type Sleeper.
     *                        </p>
     */
    public AnimationRunner(GUI gui, int framesPerSecond, Sleeper sleeper) {

        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
        this.sleeper = sleeper;
    }

    /**
     * Run an specific animation loop.
     *
     * @param animation type Animation.
     */
    public void run(Animation animation) {

        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        //run while the stop condition of the animation is false.
        while (!animation.shouldStop()) {

            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();

            //Do one frame for the animation.
            animation.doOneFrame(d);
            this.gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
