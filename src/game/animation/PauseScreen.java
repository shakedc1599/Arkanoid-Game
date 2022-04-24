package game.animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * The PauseScreen add pause screen to the game, when the user press "p" the
 * game will pause, and then when the user press space the game will unpause.
 * PauseScreen implements Animation with doOneFrame and shouldStop methods.
 */
public class PauseScreen implements Animation {

    /**
     * Do one frame of animation.
     *
     * @param d type DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        //Draw the background.
        d.setColor(new Color(26, 198, 198));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Draw the text.
        d.setColor(new Color(0, 0, 0));
        d.drawText(180, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Check the stopping conditions.
     *
     * @return if stop or not, type boolean.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
