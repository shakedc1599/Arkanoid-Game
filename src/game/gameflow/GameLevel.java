package game.gameflow;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.animation.Animation;
import game.animation.AnimationRunner;
import game.animation.CountdownAnimation;
import game.animation.KeyPressStoppableAnimation;
import game.animation.PauseScreen;
import game.build.calculators.Collidable;
import game.build.calculators.GameEnvironment;
import game.build.calculators.Sprite;
import game.build.calculators.SpriteCollection;
import game.build.geometric.Ball;
import game.build.geometric.Block;
import game.build.geometric.Point;
import game.build.geometric.Paddle;
import game.build.geometric.Rectangle;
import game.build.ongame.DataDisplay;
import game.listeners.BallRemover;
import game.listeners.BlockRemover;
import game.build.ongame.Counter;
import game.listeners.ScoreTrackingListener;
import game.gameflow.levels.LevelInformation;

import java.awt.Color;
import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Create game.setup.GameLevel object how get level and initialize and run it.
 * with the addCollidable, addSprite, removeCollidable, removeSprite, createBalls,
 * createBlocks, createBlocks, createPaddle, createBorders, initialize, run,
 * doOneFrame, shouldStop, getRemainingBlocks and getRemainingBalls.
 */
public class GameLevel implements Animation {

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter currentScore;
    private int width;
    private int height;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInformation;

    private static final int RADIUS = 6;
    private static final int PADDLEHEIGHT = 20;

    /**
     * Constructor
     * <p>
     * The function create new gameLevel.
     * </p>
     *
     * @param height           type int.
     * @param width            type int.
     * @param levelInformation type LevelInformation.
     * @param currentScore     type Counter.
     * @param keyboard         type KeyboardSensor.
     * @param runner           type AnimationRunner.
     */
    public GameLevel(int width, int height, LevelInformation levelInformation,
                     Counter currentScore, AnimationRunner runner, KeyboardSensor keyboard) {

        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.width = width;
        this.height = height;
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.currentScore = currentScore;
        this.levelInformation = levelInformation;
        this.runner = runner;
        this.keyboard = keyboard;
    }

    /**
     * Add new collidable.
     *
     * @param c , type game.build.calculators.Collidable.
     */
    public void addCollidable(Collidable c) {

        environment.addCollidable(c);
    }

    /**
     * Add new sprite.
     *
     * @param s , type game.build.calculators.Sprite.
     */
    public void addSprite(Sprite s) {

        sprites.addSprite(s);
    }

    /**
     * Remove collidable from the game.
     *
     * @param c , type game.build.calculators.Collidable.
     */
    public void removeCollidable(Collidable c) {

        environment.removeCollidable(c);
    }

    /**
     * Remove sprite from the game.
     *
     * @param s , type game.build.calculators.Sprite.
     */
    public void removeSprite(Sprite s) {

        sprites.removeSprite(s);
    }

    /**
     * Create balls and add them to the game.
     * <p>
     * The function receive LevelInformation with the number of balls and there
     * velocity, and their radius and there and create the Balls and add them to
     * the game.
     * </p>
     *
     * @param levelInfo , type LevelInformation.
     */
    public void createBalls(LevelInformation levelInfo) {

        //Create balls and add them to the game.
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {

            Ball ball = new Ball(this.width / 2, this.height - PADDLEHEIGHT - RADIUS - 40,
                    RADIUS, Color.lightGray, this.environment);

            //Set the ball velocity.
            ball.setVelocity(levelInfo.initialBallVelocities().get(i));

            //Add the ball to the game.
            ball.addToGame(this);

            //Increase the numbers of balls in the game.
            this.remainingBalls.increase(1);

        }
    }

    /**
     * Add the blocks to the game.
     * <p>
     * The function receive levelInfo, and add the Blocks to the game.
     * </p>
     *
     * @param levelInfo , type LevelInformation.
     */
    public void createBlocks(LevelInformation levelInfo) {

        //create PrintingHitListener.
        ScoreTrackingListener scoreTrackingListener =
                new ScoreTrackingListener(currentScore);

        //create PrintingHitListener.
        BlockRemover blockRemover = new BlockRemover(this, remainingBlocks);

        //Add all the block to the game.
        List<Block> blocks = levelInfo.blocks();

        for (Block block : blocks) {

            //Add the listeners to the blocks.
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(blockRemover);
            block.addToGame(this);

            //Increase the numbers of blocks in the game.
            this.remainingBlocks.increase(1);
        }
    }

    /**
     * Create paddle and add him to the game.
     * <p>
     * The function receive levelInfo, and create the paddle and add him
     * to the game.
     * </p>
     *
     * @param levelInfo , type LevelInformation.
     */
    public void createPaddle(LevelInformation levelInfo) {

        //Create puddle.
        Paddle paddle = new Paddle(new Rectangle(new Point((
                width / 2 - this.levelInformation.paddleWidth() / 2), this.height - 40),
                levelInfo.paddleWidth(), PADDLEHEIGHT), Color.ORANGE, this.keyboard);

        //Add the paddle to the game.
        paddle.addToGame(this);
    }

    /**
     * Create border blocks and add them to the game.
     * <p>
     * The function create the borders block and add PrintingHitListener to the
     * bottom border, so when balls hit him, they will vanish.
     * </p>
     */
    public void createBorders() {

        //create PrintingHitListener.
        BallRemover ballRemover = new BallRemover(this, remainingBalls);

        //Create the upper border and add to the game.
        Block upperBorder = new Block(new Rectangle(
                new Point(0, 0), 800, 20), Color.BLACK);
        upperBorder.addToGame(this);

        //Create the left border and add to the game.
        Block leftBorder = new Block(new Rectangle(
                new Point(0, 10), 20, 590), Color.BLACK);
        leftBorder.addToGame(this);

        //Create the right border and add to the game.
        Block rightBorder = new Block(new Rectangle(
                new Point(780, 10), 20, 590), Color.BLACK);
        rightBorder.addToGame(this);

        //Create the lower border and add to the game.
        Block lowerBorder = new Block(new Rectangle(
                new Point(10, 580), 780, 20), Color.BLACK);
        lowerBorder.addToGame(this);

        //Add to the block the listener.
        lowerBorder.addHitListener(ballRemover);

    }

    /**
     * Initialize a new game.
     * <p>
     * The function receive number of balls, there radius and there dx,dy and
     * number of blocks lines and create the Blocks and Balls (and game.build.geometric.Paddle)
     * and add them to the game.
     * </p>
     *
     * @param levelInfo , type LevelInformation.
     */
    public void initialize(LevelInformation levelInfo) {

        //Add the background.
        this.sprites.addSprite(this.levelInformation.getBackground());

        //Create balls and add them to the game.
        createBalls(levelInfo);

        //Create the borders blocks and add them to the game;
        createBorders();

        //Create score indicator for the game.
        DataDisplay dataDisplay = new DataDisplay(this.currentScore, this.levelInformation);

        //Add the score indicator to the game.
        dataDisplay.addToGame(this);

        //Create the blocks in the line.
        createBlocks(levelInfo);

        //Create the paddle.
        createPaddle(levelInfo);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {

        //Reset is running.
        this.running = true;

        //Countdown before turn starts.
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));

        //Run the current animation which is one turn of the game.
        this.runner.run(this);

        //Check if the user remove all blocks.
        if (this.remainingBlocks.getValue() == 0) {

            //Increase the score by 100 points.
            this.currentScore.increase(100);
        }
    }

    ////////////////////////////////////////////////////////////////////////////

    /**
     * Do one frame of animation.
     *
     * @param d type DrawSurface.
     */
    @Override
    public void doOneFrame(DrawSurface d) {

        //Draw all the spites and notify that time passed.
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        //Check if p is pressed if so call KeyPressStoppableAnimation with PauseScreen.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

        //Check if all the ball or all the block remove from the game.
        if (this.remainingBlocks.getValue() <= 0
                || this.remainingBalls.getValue() <= 0) {

            this.running = false;
        }
    }

    /**
     * Check the stopping conditions.
     *
     * @return if stop or not, type boolean.
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * @return return the remaining blocks in the game, type int.
     */
    public int getRemainingBlocks() {

        return remainingBlocks.getValue();
    }

    /**
     * @return return the remaining balls in the game, type int.
     */
    public int getRemainingBalls() {

        return remainingBalls.getValue();
    }

}