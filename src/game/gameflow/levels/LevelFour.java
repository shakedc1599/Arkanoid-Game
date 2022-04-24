package game.gameflow.levels;

import game.animation.backgrounds.BackgroundLevelFour;
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
 * Level four implements LevelInformation with: numberOfBalls, initialBallVelocities,
 * paddleSpeed, paddleWidth, levelName, getBackground, blocks and
 * numberOfBlocksToRemove methods.
 */
public class LevelFour implements LevelInformation {

    /**
     * @return Number of balls int.
     */
    @Override
    public int numberOfBalls() {
        return 3;
    }

    /**
     * Add velocities to the balls.
     *
     * @return the list of the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> balls = new ArrayList<>();

        for (int i = 315; i <= 405; i += 45) {

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
        return 100;
    }

    /**
     * @return return the level name.
     */
    @Override
    public String levelName() {
        return "Final Four";
    }

    /**
     * @return return the level background.
     */
    @Override
    public Sprite getBackground() {
        return new BackgroundLevelFour();
    }

    /**
     * Create the Blocks.
     *
     * @return the list of the Blocks.
     */
    @Override
    public List<Block> blocks() {

        //Create list of color.
        List<Block> blocks = new ArrayList<>();
        List<Color> colors = new ArrayList<>();
        colors.add(Color.gray);
        colors.add(Color.red);
        colors.add(Color.yellow);
        colors.add(Color.green);
        colors.add(Color.white);
        colors.add(Color.pink);
        colors.add(Color.cyan);

        for (int i = 0; i < 7; i++) {

            for (int j = 0; j < 15; j++) {

                //Create the block.
                blocks.add(new Block(new Rectangle(
                        new Point(725 - (j * 50), 100 + (i * 20)), 50, 20), colors.get(i)));


            }
        }
        return blocks;
    }

    /**
     * @return return the level background.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 90;
    }
}
