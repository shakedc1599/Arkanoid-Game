package game.animation;

import biuoop.DrawSurface;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Interface Animation with: doOneFrame and shouldStop methods.
 */
public interface Animation {

    /**
     * Do one frame of animation.
     *
     * @param d type DrawSurface.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Check the stopping conditions.
     *
     * @return if stop or not, type boolean.
     */
    boolean shouldStop();
}
