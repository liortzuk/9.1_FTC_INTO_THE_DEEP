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
    Servo intake_AR;
    CRServo intake_center, intake_left, intake_right, intAR;
    Telemetry telemetry;

    public Elevator2(DcMotorEx EL, DcMotorEx ER, CRServo intake_center, CRServo intake_left, CRServo intake_right, Servo intake_AR, CRServo intAR, Telemetry telemetry){
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

        ER.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        El.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pid.setWanted(x);

        while(Math.abs(x) + 320 > Math.abs(El.getCurrentPosition()) && Math.abs(El.getCurrentPosition()) < Math.abs(x) - 320){
            ER.setPower(pid.update(El.getCurrentPosition()));
            El.setPower(pid.update(El.getCurrentPosition()));
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




    public void spin(double power, double sec){
        runtime.reset();
        while(sec > runtime.seconds()){
            intake_center.setPower(power);
        }intake_center.setPower(0);
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
        }
    }

    public void move_all_lol(double power,double sec,double pos, double open_pos, double close_pos){
        move_lift_angle(close_pos);
        extend(power,sec);
        move_ARM(power,sec);
        spin(-power,sec / 2);
        Move_Elevator(pos);
        move_lift_angle(open_pos);
        Move_Elevator(-pos);
        move_lift_angle(close_pos);
    }

    public void move_lift_angle(double pos) {
        intake_AR.setPosition(pos);
    }

    public void Move_out_basket(double power, double sec){
        runtime.reset();
        while(sec > runtime.seconds()){
            intake_left.setPower(-power);
            intake_right.setPower(power);
        }
        intake_left.setPower(0);
        intake_right.setPower(0);
    }


}
