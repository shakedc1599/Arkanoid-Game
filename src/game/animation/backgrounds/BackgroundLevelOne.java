// id: 209036763

package game.animation.backgrounds;

import biuoop.DrawSurface;
import game.build.calculators.Sprite;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * BackgroundLevelOne object that create backgrand to level one, with target on the block.
 * BackgroundLevelOne implements Sprite with: drawOn and timePassed methods.
 */
public class BackgroundLevelOne implements Sprite {

    /**
     * Draw the sprite to the screen.
     *
     * @param d , type DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //Draw the background.
        d.setColor(new Color(42, 45, 76));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Draw the target circles.
        d.setColor(Color.red);
        d.drawCircle(400, 225, 60);
        d.drawCircle(400, 225, 90);
        d.drawCircle(400, 225, 120);

        //Draw the target lines.
        d.drawLine(400, 190, 400, 50);
        d.drawLine(400, 260, 400, 400);
        d.drawLine(365, 225, 225, 225);
        d.drawLine(435, 225, 575, 225);




    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
