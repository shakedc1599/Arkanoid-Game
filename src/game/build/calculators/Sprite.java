package game.build.calculators;

import biuoop.DrawSurface;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Interface game.build.calculators.Sprite with: drawOn and timePassed methods.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     *
     * @param d , type DrawSurface.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}