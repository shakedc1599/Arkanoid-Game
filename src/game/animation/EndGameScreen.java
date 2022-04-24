package game.animation;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * The EndGameScreen when the user win or lose the game, the EndGameScreen pop up.
 * The EndGameScreen suitable to the user status.
 * EndGameScreen implements Animation with doOneFrame and shouldStop methods.
 */
public class EndGameScreen implements Animation {

    private boolean gameState;
    private int score;

    /**
     * Constructor
     * <p>
     * The function create new EndGameScreen.
     *
     * @param gameState type boolean.
     * @param score     type int.
     *                  </p>
     */
    public EndGameScreen(boolean gameState, int score) {

        this.gameState = gameState;
        this.score = score;
    }

    /**
     * Do one frame of animation.
     *
     * @param d type DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        //Check if the screen is win or lose.
        String msg = gameState ? "You Win! Your score is " : "Game Over. Your score is ";

        //Draw the background.
        d.setColor(new Color(26, 198, 198));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        //Draw the text.
        d.setColor(new Color(0, 0, 0));
        d.drawText(180, d.getHeight() / 2, msg + this.score, 32);
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
