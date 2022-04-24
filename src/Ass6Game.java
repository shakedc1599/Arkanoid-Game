import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.animation.AnimationRunner;
import game.gameflow.GameFlow;
import game.gameflow.levels.LevelInformation;
import game.gameflow.levels.LevelOne;
import game.gameflow.levels.LevelTwo;
import game.gameflow.levels.LevelThree;
import game.gameflow.levels.LevelFour;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaked Cohen <shakedc159@gmail.com>
 * Ass6Game gets from the user which levels he want to play than create game and
 * run it.
 * If the user didnt enter any input we run the standard four level game.
 */
public class Ass6Game {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    /**
     * Create new game with levels.
     *
     * @param args , type String[].
     */
    public static void main(String[] args) {

        //Create the four levels.
        LevelInformation levelOne = new LevelOne();
        LevelInformation levelTwo = new LevelTwo();
        LevelInformation levelThree = new LevelThree();
        LevelInformation levelFour = new LevelFour();

        //Create sleeper.
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;

        //Create the main frame.
        GUI gui = new GUI("Game", WIDTH, HEIGHT);

        //Create the Keyboard Sensor.
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        //Create the Animation Runner.
        AnimationRunner runner = new AnimationRunner(gui, framesPerSecond, sleeper);

        //Create the game 800/600
        GameFlow game = new GameFlow(runner, keyboard, WIDTH, HEIGHT);

        //Create list of levels.
        List<LevelInformation> levels = new ArrayList<>();

        //run through the input and add the levels.
        for (String level : args) {

            switch (level) {
                case "1" -> levels.add(levelOne);
                case "2" -> levels.add(levelTwo);
                case "3" -> levels.add(levelThree);
                case "4" -> levels.add(levelFour);
            }
        }

        System.out.println(args.length);

        //If the user run the program without args.
        if (levels.isEmpty()) {

            levels.add(levelOne);
            levels.add(levelTwo);
            levels.add(levelThree);
            levels.add(levelFour);
        }

        //Run the game.
        game.runLevels(levels);

        //Close the main frame.
        gui.close();
    }
}
