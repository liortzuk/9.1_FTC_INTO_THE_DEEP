package org.firstinspires.ftc.teamcode.Elevator;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.OpMode;
import org.firstinspires.ftc.teamcode.PID;

public class Elevator2 {

    ElapsedTime runtime = new ElapsedTime();
    DcMotorEx El, ER;
    Servo intake_center, intake_AR, intAR;
    CRServo intake_left, intake_right;
    Telemetry telemetry;

    public Elevator2(DcMotorEx EL, DcMotorEx ER, Servo intake_center, CRServo intake_left, CRServo intake_right, Servo intake_AR, Servo intAR, Telemetry telemetry){
        this.El = EL;
        this.ER = ER;
        this.intake_center = intake_center;
        this.intake_left = intake_left;
        this.intake_right = intake_right;
        this.intake_AR = intake_AR;
        this.intAR = intAR;
        this.telemetry = telemetry;
    }


    public void Move_Elevator(double x){

        ER.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        El.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        PID pid = new PID(0.5, 0.2, 0.1, 0, 0);

        ER.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        El.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        pid.setWanted(x);

        while(Math.abs(x) + 320 > Math.abs(El.getCurrentPosition()) && Math.abs(El.getCurrentPosition()) < Math.abs(x) - 320){
            ER.setPower(pid.update(El.getCurrentPosition()));
            El.setPower(-pid.update(El.getCurrentPosition()));
            telemetry.addLine("in");
            telemetry.addData("pos: ", El.getCurrentPosition());
            telemetry.addData("x: ", x);
            telemetry.update();
        }

        ER.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        El.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        ER.setPower(0);
        El.setPower(0);
    }



    public void extend(double pow, double sec){
        runtime.reset();
        while(sec > runtime.seconds()){
            intake_right.setPower(pow);
            intake_left.setPower(-pow);
        }
        intake_left.setPower(0);
        intake_right.setPower(0);
    }


    public void move_intake_AG(double pos){
        intAR.setPosition(pos);
    }


}
