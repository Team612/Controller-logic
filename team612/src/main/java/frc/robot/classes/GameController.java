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

public class GameController {

    private GenericHID controller;
    private String type;

    // Reference controller objects
    private Joystick emptyJoystick;
    private XboxController emptyXboxController;

    public GameController(int port) {

        // Create two new controller objects in memory
        emptyJoystick = new Joystick(port);
        emptyXboxController = new XboxController(port);

        updateType();

    }

    public void updateType() {

        switch (controller.getType().toString()) {

            case "kHIDJoystick":
                type = "Joystick";  // More readable string format for type
                controller = emptyJoystick;  // Switch the reference adress of "controller" to the joystick object
                break;

            case "kHIDGamepad":
                type = "XboxController";  // More readable string format for type
                controller = emptyXboxController;  // Switch the reference adress of "controller" to the xbox controller object
                break;

            default:
                type = "Unknown";
                controller = null;
                break;

        }

    }

    public String getType() {
        return type;
    }


    public double getX() {
        return controller.getX(type == "Joystick" ? null : Hand.kLeft);
    }

    public double getY() {
        return controller.getY(type == "Joystick" ? null : Hand.kLeft);
    }

    public double getZ() {
        return controller.getY(type == "Joystick" ? null : Hand.kRight);
    }


}
