package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Drive extends TimedRobot{
//piston moves
//compressor air thing
//solenoid controls those later
    //solenoid is plugged into ports 0 and 1
    WPI_TalonSRX rFMotor = new WPI_TalonSRX(1);
    WPI_TalonSRX rRMotor = new WPI_TalonSRX(4);
    WPI_TalonSRX rDrop1 = new WPI_TalonSRX(2);
    WPI_TalonSRX rDrop2 = new WPI_TalonSRX(3);
    SpeedControllerGroup rDrop = new SpeedControllerGroup(rDrop1, rDrop2);
    SpeedControllerGroup rSide2 = new SpeedControllerGroup(rFMotor, rDrop, rRMotor); // Yes drop




    WPI_TalonSRX lFMotor = new WPI_TalonSRX(5);
    WPI_TalonSRX lRMotor = new WPI_TalonSRX(8);
    WPI_TalonSRX lDrop1 = new WPI_TalonSRX(6);
    WPI_TalonSRX lDrop2 = new WPI_TalonSRX(7);
    SpeedControllerGroup lDrop = new SpeedControllerGroup(lDrop1, lDrop2);
    SpeedControllerGroup lSide2 = new SpeedControllerGroup(lFMotor, lDrop, lRMotor); //Yes drop

    MecanumDrive driveMecanum = new MecanumDrive(lFMotor, lRMotor, rFMotor, rRMotor);
    DifferentialDrive driveDrop = new DifferentialDrive(lSide2, rSide2);


    Compressor compressor = new Compressor(0);
    XboxController Xbox1 = new XboxController(0);


    //Boolean compressorEnabled = true;

    public void testDrive(double speed) {
        driveMecanum.driveCartesian(0.0, speed, 0.0);
        SmartDashboard.getNumber("left front motor drive", lFMotor.get());
    }

    public void doubleDrive(){
        // double drive = Xbox1.getY(Hand.kLeft);
        // double turn = Xbox1.getX(Hand.kRight);
        
        // if (Xbox1.getAButton())
        // {
        //    compressorEnabled = !compressorEnabled;
        //    if (compressorEnabled = true){
        //         compressor.start();
        //    }
        //    else{
        //        compressor.stop();
        //    }
        // }


        // if (compressor.enabled())
        // {   //If the middle wheels are down, switches to tank drive.
        //     driveDrop.tankDrive(Xbox1.getY(Hand.kLeft), Xbox1.getY(Hand.kRight));
        // }
        //     driveMecanum.driveCartesian(Xbox1.getY(Hand.kLeft), Xbox1.getX(Hand.kLeft), Xbox1.getX(Hand.kRight));
        //     // Drives classic mecanum otherwise.
    }
}