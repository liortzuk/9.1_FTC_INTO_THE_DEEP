package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.AI.Arava;
import org.firstinspires.ftc.teamcode.OpMode;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator;

@Autonomous
public class Twitter_blue extends OpMode {

    @Override
    protected void run() {
        Elevator elevator = new Elevator(armL, armR, intake, ANGLE, LeftServo, RightServo, trigger, angle);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);
        Arava ai = new Arava();;
        runtime.reset();

        if (left_middle_right_blue == 2){
            driveTrain.driveTo(20);
            sleep(5);

            driveTrain.turnToGyro(180);
            sleep(5);
            elevator.Ching_chung();
            sleep(100);

            driveTrain.driveTo(-50);
            sleep(100);
            driveTrain.driveTo(-40);
            sleep(100);
            driveTrain.driveTo(36);
            sleep(100);
            intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.IntakePower(500,1);
            sleep(5);

            elevator.AngleLift(800,-1);
            sleep(5);

            driveTrain.driveTo(15);
            sleep(100);

            driveTrain.turnToGyro(180);
            sleep(200);
            driveTrain.turnToGyro(75);
            sleep(100);
            driveTrain.driveTo(-115);
            sleep(50);
            driveTrain.SideWalk(-60);
            sleep(100);
            driveTrain.driveTo(-10);
            sleep(5);

            elevator.servo_R(1,1);
            sleep(100);

            driveTrain.driveTo(20);
            sleep(100);

            driveTrain.SideWalk(-60);
            sleep(100);

            driveTrain.turnToGyro(85);
            sleep(2);
            driveTrain.driveTo(-10);
            sleep(2);
            ANGLE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.AngleLift(-800,1);
            sleep(5);
            elevator.Elevator_function_down(10);
            sleep(5);


        }
        else if (left_middle_right_blue  == 3){
            driveTrain.SideWalk(-20);
            sleep(200);
            driveTrain.driveTo(10);
            sleep(5);
            driveTrain.turnToGyro(180);
            sleep(100);
            driveTrain.turnToGyro(35);
            sleep(100);
            elevator.Ching_chung();
            sleep(5);
            elevator.Elevator_function_down(980);
            sleep(100);

            driveTrain.driveTo(-45);
            sleep(5);

            intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.IntakePower(500,1);
            sleep(5);
            driveTrain.driveTo(35);
            sleep(100);

            elevator.AngleLift(800,-1);
            sleep(5);

            driveTrain.turnToGyro(180);
            sleep(100);
            driveTrain.turnToGyro(27);
            sleep(100);
            elevator.Elevator_function(1450);
            sleep(100);
            driveTrain.SideWalk(-75);
            sleep(100);

            driveTrain.driveTo(-100);
            sleep(100);
            driveTrain.SideWalk(-25);
            sleep(100);
            driveTrain.driveTo(-30);
            sleep(100);

            elevator.servo_R(1,1);
            sleep(500);

            driveTrain.driveTo(20);
            sleep(500);
            driveTrain.SideWalk(-30);
            sleep(500);

            driveTrain.turnToGyro(180);
            sleep(5);
            driveTrain.turnToGyro(90);
            sleep(5);
            elevator.lihi();
            sleep(5);

        }
        else if (left_middle_right_blue  == 1) {
            driveTrain.driveTo(20);
            sleep(100);

            driveTrain.turnToGyro(90);
            sleep(100);
            driveTrain.SideWalk(-100);
            sleep(100);

            driveTrain.driveTo(14);
            sleep(100);

            elevator.Ching_chung();
            sleep(100);

            intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.IntakePower(500,1);
            sleep(5);

            elevator.AngleLift(800,-1);
            sleep(5);

            driveTrain.SideWalk(90);
            sleep(100);

            driveTrain.driveTo(-60);
            sleep(300);

            driveTrain.driveTo(-58);
            sleep(5);

            elevator.servo_R(1,1);
            sleep(100);
            elevator.Elevator_function(1400);
            sleep(2);

            driveTrain.driveTo(30);
            sleep(200);

            driveTrain.turnToGyro(90);
            sleep(50);
            driveTrain.driveTo(-50);
            sleep(100);
            driveTrain.SideWalk(10);
            sleep(100);
            driveTrain.turnToGyro(165);
            sleep(100);

            elevator.lihi();
            sleep(5);

        }

    }

    @Override
    protected void end() {

    }

}
