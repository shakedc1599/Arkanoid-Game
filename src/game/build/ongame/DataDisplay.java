package game.build.ongame;

import biuoop.DrawSurface;
import game.build.calculators.Sprite;
import game.gameflow.GameLevel;
import game.gameflow.levels.LevelInformation;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * DataDisplay object, get the current score and the level name, and display
 * them on the screen.
 */
public class DataDisplay implements Sprite {

    private Counter currentScore;
    private LevelInformation levelInformation;

    /**
     * Constructor.
     * <p>
     * The function receive counter and levelInformation create DataDisplay.
     * </p>
     *
     * @param scoreCounter type Counter.
     * @param levelInformation type LevelInformation.
     */
    public DataDisplay(Counter scoreCounter, LevelInformation levelInformation) {
        this.currentScore = scoreCounter;
        this.levelInformation = levelInformation;
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Draw the score and the level name on the given DrawSurface.
     * <p>
     * The function receive surface and draw upon it the given score.
     * </p>
     *
     * @param d type DrawSurface.
     */
    @Override
    public void drawOn(DrawSurface d) {

        //Set the scripture color.
        d.setColor(Color.WHITE);

        //Draw the Score.
        d.drawText(370, 18, "Score: " + this.currentScore.getValue(), 20);

        //Draw the Level name.
        d.drawText(540, 18, "Level Name: " + this.levelInformation.levelName(), 20);

    }

    /**
     * Notify the DataDisplay that time has passed.
     */
    @Override
    public void timePassed() {
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Add the DataDisplay to the game.
     *
     * @param g type game.setup.GameLevel.
     */
    public void addToGame(GameLevel g) {

        g.addSprite(this);
    }
}
