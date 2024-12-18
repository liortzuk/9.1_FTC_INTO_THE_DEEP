package org.firstinspires.ftc.teamcode.Elevator;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.PID;

public class Elevator2 {

    ElapsedTime runtime = new ElapsedTime();
    DcMotorEx El, ER;
    CRServo intake_center, intake_left, intake_right, intake_AR, intakeAL, intAR;

    public Elevator2(DcMotorEx EL, DcMotorEx ER, CRServo intake_center, CRServo intake_left, CRServo intake_right, CRServo intake_AR, CRServo intakeAL, CRServo intAR){
        this.El = EL;
        this.ER = ER;
        this.intake_center = intake_center;
        this.intake_left = intake_left;
        this.intake_right = intake_right;
        this.intake_AR = intake_AR;
        this.intakeAL = intakeAL;
        this.intAR = intAR;
    }


    public void Move_Elevator(int x){

        PID pid = new PID(0.5, 0.2, 0.1, 0, 0);

        ER.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        El.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        ER.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        El.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pid.setWanted(x);

        while(x > Math.abs(El.getCurrentPosition())){
            ER.setPower(pid.update(El.getCurrentPosition()));
            El.setPower(pid.update(El.getCurrentPosition()));
        }

        ER.setPower(0);
        El.setPower(0);
    }


    public void spin(double power, double sec){
        runtime.reset();
        while(sec > runtime.seconds()){
            intake_center.setPower(power);
        }
        intake_center.setPower(0);
    }

    public void extend(double power, double sec){
        runtime.reset();
        while(sec > runtime.seconds()){
            intake_right.setPower(power);
            intake_left.setPower(-power);
        }
    }


    public void move_ARM(double power, double sec){
        runtime.reset();
        while(sec > runtime.seconds()){
            intAR.setPower(power);
        }intAR.setPower(0);
    }

}
