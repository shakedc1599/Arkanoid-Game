package game.gameflow;

import biuoop.KeyboardSensor;
import game.animation.AnimationRunner;
import game.animation.EndGameScreen;
import game.animation.KeyPressStoppableAnimation;
import game.build.ongame.Counter;
import game.gameflow.levels.LevelInformation;

import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create GameFlow object get lists of levels and initialize and run them.
 * with the runLevels method.
 */
public class GameFlow {

    private int width;
    private int height;
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboard;
    private Counter currentScore;
    private boolean gameState;

    /**
     * Constructor
     * <p>
     * The function create new GameFlow.
     * </p>
     *
     * @param height          type int.
     * @param width           type int.
     * @param animationRunner type AnimationRunner.
     * @param keyboard        type KeyboardSensor.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboard,
                    int width, int height) {

        this.animationRunner = animationRunner;
        this.keyboard = keyboard;
        this.width = width;
        this.height = height;
        this.currentScore = new Counter();
        this.gameState = true;
    }

    /**
     * Run levels from list.
     * <p>
     * The function receive list of levels and run it till the user win/lose
     * the game.
     * </p>
     *
     * @param levels , type List<LevelInformation>.
     */
    public void runLevels(List<LevelInformation> levels) {

        //Run through the levels.
        for (LevelInformation levelInfo : levels) {

            //Create new GameLevel to run the level.
            GameLevel level = new GameLevel(this.width, this.height, levelInfo,
                    this.currentScore, this.animationRunner, this.keyboard);

            //Initialize the level.
            level.initialize(levelInfo);

            //While there is balls and blocks, run the level.
            while (level.getRemainingBlocks() != 0 && level.getRemainingBalls() != 0) {

                level.run();
            }

            //Check if we lose or win (depend if there is balls left).
            if (level.getRemainingBalls() == 0) {

                //Change the game state to lose.
                this.gameState = false;
                break;
            }
        }

        //Run the end screen.
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboard,
                KeyboardSensor.SPACE_KEY, new EndGameScreen(this.gameState,
                this.currentScore.getValue())));
    }
}
