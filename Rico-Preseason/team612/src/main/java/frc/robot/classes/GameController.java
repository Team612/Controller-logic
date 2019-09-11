/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.classes;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class GameController {

    private GenericHID controller;
    private String type;

    // Reference controller objects
    private Joystick emptyJoystick;
    private XboxController emptyXboxController;

    private ButtonMap buttonMap=new ButtonMap("/");

    public GameController(int port) {

        // Create two new controller objects in memory
        emptyJoystick = new Joystick(port);
        emptyXboxController = new XboxController(port);

        updateType();

    }

    public void updateType() {

        switch (emptyJoystick.getType().toString()) {

            case "kHIDJoystick":
                type = "Joystick";  // More readable string format for type
                controller = emptyJoystick;  // Switch the reference adress of "controller" to the joystick object
                break;

            case "kXInputGamepad":
                type = "XboxController";  // More readable string format for type
                controller = emptyXboxController;  // Switch the reference adress of "controller" to the xbox controller object
                break;

            default:
                type = "Unknown";
                controller = emptyJoystick;
                break;

        }

    }

    public String getType() {
        return type;
    }

    public double getX() {
        return type == "Joystick" ? controller.getX() : controller.getX(Hand.kLeft);
    }

    public double getY() {
        return type == "Joystick" ? controller.getY() : controller.getY(Hand.kLeft);
    }

    public double getZ() {
        return type == "Joystick" ? controller.getRawAxis(2) : controller.getX(Hand.kRight);
    }

    public boolean isPressed(String key){
        return buttonMap.isPressed(type, key);
    }
    
    public JoystickButton getButton(String key){
        return buttonMap.getButton(type, key);
    }

}