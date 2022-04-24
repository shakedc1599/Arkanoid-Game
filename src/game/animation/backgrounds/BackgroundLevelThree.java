// id: 209036763

package game.animation.backgrounds;

import biuoop.DrawSurface;
import game.build.calculators.Sprite;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * BackgroundLevelThree object that create backgrand to level two, with building.
 * BackgroundLevelThree implements Sprite with: drawOn and timePassed methods.
 */
public class BackgroundLevelThree implements Sprite {

    private static final int NUMOFWINDOWSROW = 5;
    private static final int NUMOFWINDOWSCOL = 5;

    /**
     * Draw the sprite to the screen.
     *
     * @param d , type DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //Draw the background.
        d.setColor(new Color(42, 130, 21));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Draw the building.
        d.setColor(new Color(46, 42, 41));
        d.fillRectangle(50, 400, 120, 180);

        d.setColor(new Color(62, 58, 57));
        d.fillRectangle(90, 320, 40, 80);

        d.setColor(new Color(78, 74, 73));
        d.fillRectangle(105, 150, 10, 170);

        //Draw the windows.
        d.setColor(Color.white);

        for (int i = 0; i < NUMOFWINDOWSCOL; i++) {
            for (int j = 0; j < NUMOFWINDOWSROW; j++) {

                d.fillRectangle(59 + 23 * j, 407 + 40 * i, 10, 28);
            }
        }

        //Draw the tower light.
        d.setColor(new Color(216, 172, 102));
        d.fillCircle(110, 135, 15);

        d.setColor(new Color(246, 78, 54));
        d.fillCircle(110, 135, 10);

        d.setColor(Color.white);
        d.fillCircle(110, 135, 5);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {
    }
}
