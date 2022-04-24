// id: 209036763

package game.animation.backgrounds;

import biuoop.DrawSurface;
import game.build.calculators.Sprite;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * BackgroundLevelTwo object that create backgrand to level two, with sun and sunlights.
 * BackgroundLevelTwo implements Sprite with: drawOn and timePassed methods.
 */
public class BackgroundLevelTwo implements Sprite {

    private static final int SUNLINES = 80;

    /**
     * Draw the sprite to the screen.
     *
     * @param d , type DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //Draw the background.
        d.setColor(Color.white);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Draw the sun lines.
        for (int i = 0; i < SUNLINES; i++) {

            d.setColor(new Color(239, 231, 176));
            d.drawLine(120, 120, i * 700 / SUNLINES, 250);
        }

        //Draw the sun.
        d.setColor(new Color(239, 231, 176));
        d.fillCircle(120, 120, 70);

        d.setColor(new Color(236, 215, 73));
        d.fillCircle(120, 120, 60);

        d.setColor(new Color(255, 225, 24));
        d.fillCircle(120, 120, 50);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
