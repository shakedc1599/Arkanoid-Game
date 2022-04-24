package game.build.calculators;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.build.calculators.SpriteCollection object (list of game.build.calculators.Sprite) with: addSprite,
 * notifyAllTimePassed, drawAllOn methods.
 */
public class SpriteCollection {

    private List<Sprite> sprites;

    /**
     * Constructor
     * <p>
     * The function create new ArrayList.
     * </p>
     */
    public SpriteCollection() {

        this.sprites = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Add the given sprites to the sprites list.
     *
     * @param s , type game.build.calculators.Sprite.
     */
    public void addSprite(Sprite s) {

        this.sprites.add(s);
    }

    /**
     * Remove the given sprites from the sprites list.
     *
     * @param s , type game.build.calculators.Sprite.
     */
    public void removeSprite(Sprite s) {

        this.sprites.remove(s);
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {

        //Make a copy of the tempSprites before iterating over them.
        List<Sprite> tempSprites = new ArrayList<Sprite>(this.sprites);

        //Run through all the tempSprites.
        for (Sprite sprite : tempSprites) {

            //Call timePassed.
            sprite.timePassed();
        }
    }

    /**
     * Call drawOn() on all sprites.
     *
     * @param d , type DrawSurface.
     */
    public void drawAllOn(DrawSurface d) {

        //Run through all the sprites.
        for (Sprite sprite : this.sprites) {

            //Call drawOn.
            sprite.drawOn(d);
        }
    }
}