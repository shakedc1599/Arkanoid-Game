// id: 209036763

package game.animation.backgrounds;

import biuoop.DrawSurface;
import game.build.calculators.Sprite;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * BackgroundLevelFour object that create backgrand to level four, with clouds and rain.
 * BackgroundLevelFour implements Sprite with: drawOn and timePassed methods.
 */
public class BackgroundLevelFour implements Sprite {

    /**
     * Draw the sprite to the screen.
     *
     * @param d , type DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //Draw the background.
        d.setColor(new Color(23, 136, 208));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Draw the clouds.
        drawCloudAndRain(d, 100, 400);
        drawCloudAndRain(d, 600, 300);

    }

    /**
     * Get start point and draw clouds and rain from there.
     *
     * @param x type int.
     * @param y type int.
     * @param d type DrawSurface.
     */
    public void drawCloudAndRain(DrawSurface d, int x, int y) {

        //Draw the rain.
        for (int i = 0; i < 10; i++) {

            d.setColor(Color.white);
            d.drawLine(x + (i * 7), y, x - 50 + (i * 7), d.getHeight());
        }

        //Draw the cloud.
        d.setColor(new Color(204, 204, 204));
        d.fillCircle(x, y, 25);

        d.setColor(new Color(204, 204, 204));
        d.fillCircle(x + 20, y + 20, 20);

        d.setColor(new Color(187, 187, 187));
        d.fillCircle(x + 40, y, 30);

        d.setColor(new Color(170, 170, 170));
        d.fillCircle(x + 50, y + 20, 25);

        d.setColor(new Color(170, 170, 170));
        d.fillCircle(x + 70, y, 30);

    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
