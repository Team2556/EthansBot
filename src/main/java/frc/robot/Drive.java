package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Drive extends TimedRobot{
    //right motors
    WPI_TalonSRX rFMotor = new WPI_TalonSRX(1);
    WPI_TalonSRX rRMotor = new WPI_TalonSRX(4);
    WPI_TalonSRX rDrop1 = new WPI_TalonSRX(2);
    WPI_TalonSRX rDrop2 = new WPI_TalonSRX(3);
    SpeedControllerGroup rDrop = new SpeedControllerGroup(rDrop1, rDrop2);
    SpeedControllerGroup rSide2 = new SpeedControllerGroup(rFMotor, rDrop, rRMotor); // Yes drop

    //left motors
    WPI_TalonSRX lFMotor = new WPI_TalonSRX(5);
    WPI_TalonSRX lRMotor = new WPI_TalonSRX(8);
    WPI_TalonSRX lDrop1 = new WPI_TalonSRX(6);
    WPI_TalonSRX lDrop2 = new WPI_TalonSRX(7);
    SpeedControllerGroup lDrop = new SpeedControllerGroup(lDrop1, lDrop2);
    SpeedControllerGroup lSide2 = new SpeedControllerGroup(lFMotor, lDrop, lRMotor); //Yes drop

    //accessories
    Compressor compressor = new Compressor();
    DoubleSolenoid pistons = new DoubleSolenoid(0, 1);
    XboxController Xbox1 = new XboxController(0);

    //drivebase init
    MecanumDrive driveMecanum = new MecanumDrive(lFMotor, lRMotor, rFMotor, rRMotor);
    DifferentialDrive driveDrop = new DifferentialDrive(lSide2, rSide2);

    public void doubleDrive(){
    //piston status
        if (pistons.get() == kForward) {
            SmartDashboard.putBoolean("Pistons Down", true);
        }
        else {
            SmartDashboard.putBoolean("Pistons Down", false);
        }

    //activate pistons
        if (Xbox1.getAButtonPressed())
        {
            if (pistons.get() == kForward) {
                pistons.set(kReverse);
            }
            else {
                pistons.set(kForward);
            }
        }

        //drop drive check
        //If the middle wheels are down, switches to tank drive.
        if (pistons.get() == kForward)
        {   
            driveDrop.tankDrive(Xbox1.getY(Hand.kLeft), Xbox1.getY(Hand.kRight));
        }
        // drives classic mecanum otherwise
        else {
            driveMecanum.driveCartesian(-Xbox1.getX(Hand.kLeft), Xbox1.getY(Hand.kLeft), Xbox1.getX(Hand.kRight));
        }
    }
}