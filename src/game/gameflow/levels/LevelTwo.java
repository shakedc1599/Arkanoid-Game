package game.gameflow.levels;

import game.animation.backgrounds.BackgroundLevelTwo;
import game.build.calculators.Sprite;
import game.build.calculators.Velocity;
import game.build.geometric.Block;
import game.build.geometric.Point;
import game.build.geometric.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Level two implements LevelInformation with: numberOfBalls, initialBallVelocities,
 * paddleSpeed, paddleWidth, levelName, getBackground, blocks and
 * numberOfBlocksToRemove methods.
 */
public class LevelTwo implements LevelInformation {

    /**
     * @return Number of balls int.
     */
    @Override
    public int numberOfBalls() {
        return 10;
    }

    /**
     * Add velocities to the balls.
     *
     * @return the list of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> balls = new ArrayList<>();

        for (int i = 315; i <= 415; i += 10) {

            //Add the velocity to the ball.
            balls.add(Velocity.fromAngleAndSpeed(i, 8));

        }

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
        return 600;
    }

    /**
     * @return return the level name.
     */
    @Override
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * @return return the level background.
     */
    @Override
    public Sprite getBackground() {
        return new BackgroundLevelTwo();
    }

    /**
     * Create the Blocks.
     *
     * @return the list of the Blocks.
     */
    @Override
    public List<Block> blocks() {

        List<Block> blocks = new ArrayList<>();

        //Create list of colors.
        List<Color> colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.orange);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.blue);
        colors.add(Color.magenta);
        colors.add(Color.pink);

        int j = 0;
        for (int i = 25; i < 775; i += 50) {

            //Create the block.
            blocks.add(new Block(new Rectangle(new Point(i, 250), 50, 30), colors.get(j / 10)));

            j += 4;

        }

        return blocks;
    }

    /**
     * @return return the level background.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 19;
    }
}
