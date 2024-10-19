package org.firstinspires.ftc.teamcode.Auto;


import static org.firstinspires.ftc.vision.VisionPortal.easyCreateWithDefaults;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.AI.Arava;
import org.firstinspires.ftc.teamcode.AI.golda;
import org.firstinspires.ftc.teamcode.OpMode;

import com.acmerobotics.dashboard.config.Config;

import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrain;
import org.firstinspires.ftc.teamcode.Elevator.Elevator;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;

@Config
@Autonomous
public class Ido_red extends OpMode {

    @Override
    protected void run() {
        Elevator elevator = new Elevator(armL, armR, intake, ANGLE, LeftServo, RightServo, trigger, angle);
        DriveTrain driveTrain = new DriveTrain(DriveBackRight, DriveBackLeft, DriveFrontRight, DriveFrontLeft, telemetry, Imu);
        Arava ai = new Arava();

        runtime.reset();

        while (runtime.seconds() < 10){
            left_middle_right_red = ai.Run_the_AI();
        }

        DriveFrontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DriveFrontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DriveBackLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        DriveBackRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        runtime.reset();
        if (left_middle_right_red  == 2){
            driveTrain.driveTo(20);
            sleep(5);

            driveTrain.turnToGyro(180);
            sleep(5);
            elevator.Ching_chung();
            sleep(100);

            driveTrain.driveTo(-50);
            sleep(50);
            driveTrain.driveTo(-40);
            sleep(5);
            driveTrain.driveTo(34);
            sleep(100);
            intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.IntakePower(500,1);
            sleep(5);

            driveTrain.driveTo(4);
            sleep(2);

            elevator.AngleLift(800,-1);
            sleep(5);

            driveTrain.turnToGyro(85);
            sleep(200);
            driveTrain.driveTo(-115);
            sleep(5);
            driveTrain.driveTo(-10);
            sleep(5);

            elevator.servo_R(1,1);
            sleep(500);

            driveTrain.driveTo(20);
            sleep(500);

            driveTrain.SideWalk(120);
            sleep(500);

            driveTrain.turnToGyro(90);
            sleep(2);
            driveTrain.turnToGyro(90);
            sleep(2);
            driveTrain.turnToGyro(105);
            sleep(2);
            driveTrain.SideWalk(50);
            sleep(2);
            driveTrain.driveTo(-10);
            sleep(2);
            ANGLE.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.AngleLift(-800,1);
            sleep(5);
            elevator.Elevator_function_down(10);
            sleep(5);


        }
        else if (left_middle_right_red  == 1){
            driveTrain.SideWalk(20);
            sleep(500);
            driveTrain.driveTo(65);
            sleep(5);

            driveTrain.turnToGyro(90);
            elevator.Ching_chung();
            sleep(5);

            intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.IntakePower(500,1);
            sleep(5);

            elevator.AngleLift(800,-1);
            sleep(5);

            driveTrain.driveTo(40);
            sleep(300);

            driveTrain.turnToGyro(170);
            sleep(200);

            driveTrain.driveTo(-46);
            sleep(5);

            driveTrain.driveTo(-54);
            sleep(500);

            elevator.servo_R(1,1);
            sleep(1000);
            elevator.Elevator_function(1400);
            sleep(2);


            driveTrain.driveTo(20);
            sleep(2000);

            driveTrain.turnToGyro(90);
            sleep(5);
            driveTrain.driveTo(30);

            sleep(5);
            elevator.lihi();
            sleep(5);

        }
        else if (left_middle_right_red  == 3) {
            driveTrain.driveTo(10);
            sleep(5);
            driveTrain.turnToGyro(180);
            sleep(100);
            driveTrain.turnToGyro(17);
            sleep(100);
            elevator.Ching_chung();
            sleep(5);

            driveTrain.driveTo(-50);
            sleep(5);

            intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            elevator.IntakePower(500,1);
            sleep(5);
            driveTrain.driveTo(20);
            sleep(100);

            elevator.AngleLift(800,-1);
            sleep(5);

            driveTrain.turnToGyro(60);
            sleep(100);
            driveTrain.SideWalk(35);
            sleep(100);

            driveTrain.driveTo(-90);
            sleep(100);

            driveTrain.SideWalk(-40);
            sleep(100);

            elevator.servo_R(1,1);
            sleep(500);

            driveTrain.turnToGyro(90);

            sleep(5);
            elevator.lihi();
            sleep(5);

        }


    }

    @Override
    protected void end() {

    }
}

