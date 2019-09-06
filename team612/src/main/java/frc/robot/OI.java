package frc.robot;

import frc.robot.classes.CommandButton;
import frc.robot.classes.GameController;

public class OI 
{

    // Port Numbers for XboxController
    private static int xbox_button_A    	= 1;
    private static int xbox_button_B 	    = 2;
    private static int xbox_button_X    	= 3;
    private static int xbox_button_Y    	= 4;
    private static int xbox_button_LB   	= 5;
    private static int xbox_button_RB   	= 6;
    private static int xbox_button_BCK  	= 7;
    private static int xbox_button_STRT 	= 8;
    private static int xbox_button_LJ   	= 9;
    private static int xbox_button_RJ   	= 10;

    // TODO: make port definitions for flightstick


    public static GameController driver = new GameController(RobotMap.DRIVER_PORT);
    public static GameController gunner = new GameController(RobotMap.GUNNER_PORT);

    public static CommandButton moveArmBtn = driver.createButton(1, xbox_button_A);

    public OI() {

    }

}
