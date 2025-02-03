package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Elevator.Elevator_based;

public class Move_EL extends CommandBase {

    private final Elevator_based EL;
    private double x;

    public Move_EL(Elevator_based subsystem, double x){
        this.EL = subsystem;
        addRequirements(subsystem);
        this.x = x;
    }

    @Override
    public void execute() {
        EL.Move_Elevator(x);
    }

}
