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

    GenericHID controller;
    String type;

    public GameController(int port) {
        
        type = controller.getType().toString();

        switch (type) {

            case "kHIDJoystick":
                type = "Joystick";
                controller = new Joystick(port);
                break;

            case "kHIDGamepad":
                type = "XboxController";
                controller = new XboxController(port);
                break;

            default:
                type = "Unknown";
                controller = null;
                break;

        }

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
