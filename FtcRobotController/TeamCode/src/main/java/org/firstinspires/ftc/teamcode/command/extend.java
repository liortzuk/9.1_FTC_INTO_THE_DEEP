package org.firstinspires.ftc.teamcode.command;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Elevator.Elevator_based;

public class extend extends CommandBase {
    private double pow, sec;
    private Elevator_based subSys;

    public extend(Elevator_based subSys, double pow, double sec){
        this.subSys = subSys;
        this.pow = pow;
        this.sec = sec;
    }

    @Override
    public void execute() {
        subSys.extend(pow, sec);
    }
}
