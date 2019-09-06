/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.classes;

import java.util.Dictionary;
import java.util.HashMap;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Add your docs here.
 */
public class ButtonMap {
    private HashMap<String, JoystickButton> xboxDict = new HashMap<>();
    private HashMap<String, JoystickButton> joystickDict = new HashMap<>();

    public ButtonMap(String json_path){
        parseJSON(json_path);
    }

    private void parseJSON(String json_path){
        
    }

    private void addToDict(int xboxPort, GenericHID xbox, int joystickPort, GenericHID joy, String key){
        xboxDict.put(key,new JoystickButton(xbox, xboxPort));  
        joystickDict.put(key,new JoystickButton(joy, joystickPort));  
    }

    public boolean isPressed(String type, String key){
        return getButton(type, key).get();
    }

    public JoystickButton getButton(String type, String key){
        return type=="Joystick"?joystickDict.get(key):xboxDict.get(key);
    }
}
