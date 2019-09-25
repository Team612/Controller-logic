package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.classes.profiles.GameController;
import frc.robot.classes.profiles.JSONMap;
import frc.robot.classes.profiles.Loader;
import frc.robot.classes.profiles.ProfileManager;
import frc.robot.subsystems.Drivetrain;

public class Robot extends TimedRobot 
{

    public static final Drivetrain drivetrain = new Drivetrain();

    public static OI oi;

    private Command autonomousCommand;
    private SendableChooser<Command> chooser = new SendableChooser<>();
    private SendableChooser<String> driverProfiles = new SendableChooser<>();
    private SendableChooser<String> gunnerProfiles = new SendableChooser<>();

    @Override
    public void robotInit() 
    {
        oi = new OI();
        Loader.loadJSON("profiles.json", new GameController(RobotMap.DRIVER_PORT), new GameController(RobotMap.GUNNER_PORT));

        fillChooser(driverProfiles, JSONMap.getDriverProfileNames());
        fillChooser(gunnerProfiles, JSONMap.getGunnerProfileNames());
        SmartDashboard.putData("Driver Profile", driverProfiles);
        SmartDashboard.putData("Gunner Profile", gunnerProfiles);
    }

    private void fillChooser(SendableChooser<String> choose, String[] profiles){
        if(profiles.length==0)return;
        for(String profile:profiles){
            choose.addOption(profile, profile);
        }
        choose.setDefaultOption(profiles[0], profiles[0]);
    }

    @Override
    public void disabledInit() 
    {
        
    }

    @Override
    public void disabledPeriodic() 
    {
        Scheduler.getInstance().run();
        selectProfiles();
    }

    @Override
    public void autonomousInit() 
    {
        autonomousCommand = chooser.getSelected();

        if (autonomousCommand != null) 
        {
            autonomousCommand.start();
        }
    }

    @Override
    public void autonomousPeriodic() 
    {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() 
    {
        Loader.loadJSON("profiles.json", new GameController(RobotMap.DRIVER_PORT), new GameController(RobotMap.GUNNER_PORT));
        
        if (autonomousCommand != null) 
        {
            autonomousCommand.cancel();
        }
    }

    @Override
    public void teleopPeriodic() 
    {
        Scheduler.getInstance().run();

        System.out.println("PRESSED: "+ProfileManager.getDriverButton("A").get());
        selectProfiles();

    }

    @Override
    public void testPeriodic() 
    {
        
    }

    private void selectProfiles(){
            ProfileManager.setCurrentDriver(driverProfiles.getSelected());
            ProfileManager.setCurrentGunner(gunnerProfiles.getSelected());
    }
    
}
