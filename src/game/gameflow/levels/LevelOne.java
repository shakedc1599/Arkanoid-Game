package game.gameflow.levels;

import game.build.calculators.Sprite;
import game.build.calculators.Velocity;
import game.build.geometric.Block;
import game.build.geometric.Point;
import game.build.geometric.Rectangle;
import game.animation.backgrounds.BackgroundLevelOne;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Level one implements LevelInformation with: numberOfBalls, initialBallVelocities,
 * paddleSpeed, paddleWidth, levelName, getBackground, blocks and
 * numberOfBlocksToRemove methods.
 */
public class LevelOne implements LevelInformation {

    /**
     * @return Number of balls int.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * Add velocities to the balls.
     *
     * @return the list of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> balls = new ArrayList<>();

        //Add the velocity to the ball.
        balls.add(Velocity.fromAngleAndSpeed(0, 10));

        return balls;
    }

    /**
     * @return return the paddle speed.
     */
    @Override
    public int paddleSpeed() {
        return 10;
    }

    /**
     * @return return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return 100;
    }

    /**
     * @return return the level name.
     */
    @Override
    public String levelName() {
        return "Direct hit";
    }

    /**
     * @return return the level background.
     */
    @Override
    public Sprite getBackground() {
        return new BackgroundLevelOne();
    }

    /**
     * Create the Blocks.
     *
     * @return the list of the Blocks.
     */
    @Override
    public List<Block> blocks() {

        List<Block> blocks = new ArrayList<>();

        //Create the block.
        blocks.add(new Block(new Rectangle(new Point(375, 200), 50, 50), Color.RED));

        return blocks;
    }

    /**
     * @return return the level background.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

}
