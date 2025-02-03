package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrainSub;

public class drive extends CommandBase {

    private final DriveTrainSub subDrive;
    double forward;
    double drift;
    double turn;
    double botHeading;

    public drive(DriveTrainSub subsystem, double forward, double drift,double turn,double botHeading) {
        subDrive = subsystem;
        addRequirements(subsystem);

        this.forward = forward;
        this.drift = drift;
        this.turn = turn;
        this.botHeading = botHeading;
    }

    @Override
    public void execute() {
        subDrive.drive(forward, drift, turn, botHeading);
    }
}
