/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.classes.profiles;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.classes.GameController;

/**
 * Add your docs here.
 */
public class Loader {
    private static GameController driver,gunner;
    //private static Joystick joystick;

    static void loadJSON(String src, GameController driver, GameController gunner){
        this.gunner=gunner;
        this.driver=driver;
        Object obj;
        try{
            File f=new File(Loader.class.getResource(src).toURI());
            obj=new JSONParser().parse(new FileReader(f));
        } catch (IOException | ParseException | URISyntaxException e) {
            e.printStackTrace();
            return;
        }
        JSONObject topObj=(JSONObject) obj;
        JSONObject driver=(JSONObject) topObj.get("driver");
        String[] driverProfs= setToString(driver.keySet());
        ControllerProfile[] drivers=new ControllerProfile[driverProfs.length];
        for(int i=0;i<drivers.length;i++){
            drivers[i]=loadProfile((JSONObject)driver.get(driverProfs[i]),driverProfs[i]);
        }
        JSONObject gunner=(JSONObject) topObj.get("gunner");
        String[] gunnerProfs=setToString(gunner.keySet());
        ControllerProfile[] gunners=new ControllerProfile[gunnerProfs.length];
        for(int i=0;i<gunners.length;i++){
            gunners[i]=loadProfile((JSONObject)gunner.get(gunnerProfs[i]),gunnerProfs[i]);
        }
        JSONMap.loadProfiles(drivers,gunners);
    }

    private static String[] setToString(Set source){
        String[] dest = new String[source.size()];
        int iter=0;
        for (Object o : source) {
            dest[iter]=(String)o;
            iter++;
        }
        return dest;
    }

    private static ControllerProfile loadProfile(JSONObject profile,String name){
        HashMap<String, JoystickButton> xboxMaps=new HashMap<>();
        fillMap(profile,xboxMaps,"xbox");
        HashMap<String,JoystickButton> joystickMaps=new HashMap<>();
        fillMap(profile,joystickMaps,"joystick");
        return new ControllerProfile(name,xboxMaps,joystickMaps);
    }

    private static void fillMap(JSONObject source, HashMap<String, JoystickButton> dest, String key){
        JSONObject object=(JSONObject)source.get(key);
        Set keys=object.keySet();
        for(Object o:keys){
            JSONArray arr=(JSONArray)object.get(o);
            dest.put((String)arr.get(0),new JoystickButton(key.equals("joystick")?joystick:xbox ,(int)arr.get(1)));
        }
    }
}
