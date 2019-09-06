/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.classes;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */
public class CommandButton {

    private JoystickButton emptyJoystickBtn;
    private JoystickButton emptyXboxBtn;

    private JoystickButton button;  // Current adress for button

    // Local versions of variable transfered by GameController class
    private GameController controller;

    public CommandButton(GameController controller, int joystickBtnPort, int xboxBtnPort) {

        // Assign two buttons in memory for each controller type
        emptyJoystickBtn = new JoystickButton(controller.getCurrentController(), joystickBtnPort);
        emptyXboxBtn = new JoystickButton(controller.getCurrentController(), xboxBtnPort);

        // Store GameController variable locally to class
        this.controller = controller;

    }
    
    public boolean get() {

        // Change reference variable for button depending on controller type
        switch (controller.getType()) {

            case "Joystick":
                button = emptyJoystickBtn;
                break;

            case "XboxController":
                button = emptyXboxBtn;
                break;

        }

        // Return current button state
        return button.get();

    }


}
