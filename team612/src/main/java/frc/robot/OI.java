package frc.robot;

import frc.robot.classes.CommandButton;
import frc.robot.classes.GameController;

public class OI 
{

    // Port Numbers for XboxController
    private static final int XBOX_BUTTON_A    	= 1;
    private static final int XBOX_BUTTON_B 	    = 2;
    private static final int XBOX_BUTTON_X    	= 3;
    private static final int XBOX_BUTTON_Y    	= 4;
    private static final int XBOX_BUTTON_LB   	= 5;
    private static final int XBOX_BUTTON_RB   	= 6;
    private static final int XBOX_BUTTON_BCK  	= 7;
    private static final int XBOX_BUTTON_STRT 	= 8;
    private static final int XBOX_BUTTON_LJ   	= 9;
    private static final int XBOX_BUTTON_RJ   	= 10;

    // TODO: make port definitions for flightstick


    public static GameController driver = new GameController(RobotMap.DRIVER_PORT);
    public static GameController gunner = new GameController(RobotMap.GUNNER_PORT);

    public static CommandButton moveArmBtn = driver.createButton(1, XBOX_BUTTON_A);

    public OI() {

    }

}
