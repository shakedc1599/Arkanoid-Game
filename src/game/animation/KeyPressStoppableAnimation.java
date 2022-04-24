package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * The KeyPressStoppableAnimation is a decorator-class that will wrap an existing
 * animation and add a "waiting-for-key" behavior to it.
 * KeyPressStoppableAnimation implements Animation with doOneFrame and shouldStop methods.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor
     * <p>
     * The function create new KeyPressStoppableAnimation.
     * <p>
     *
     * @param sensor    the sensor
     * @param key       the key
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;

    }

    /**
     * Do one frame of animation.
     *
     * @param d type DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        //Do one frame of the animation.
        animation.doOneFrame(d);

        //Check if the key is press and is already pressed is true.
        if (sensor.isPressed(key) && !isAlreadyPressed) {
            stop = true;
        }

        //If the key is not pressed.
        if (!sensor.isPressed(key)) {
            isAlreadyPressed = false;
        }

    }

    /**
     * Check the stopping conditions.
     *
     * @return if stop or not, type boolean.
     */
    @Override
    public boolean shouldStop() {
        return stop;
    }

}
