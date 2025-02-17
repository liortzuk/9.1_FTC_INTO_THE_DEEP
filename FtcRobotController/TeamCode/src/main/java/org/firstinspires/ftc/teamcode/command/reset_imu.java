package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrainSub;

public class reset_imu extends CommandBase {

    DriveTrainSub subDrive;

    public reset_imu(DriveTrainSub subsystem) {
        subDrive = subsystem;
    }

    public void reset(){
        subDrive.Imu.resetYaw();
    }

}
