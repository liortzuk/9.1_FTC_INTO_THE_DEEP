package org.firstinspires.ftc.teamcode.opMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.DriveTrain.DriveTrainSub;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Elevator.Elevator_based;
import org.firstinspires.ftc.teamcode.command.drive;

public class OPMODE_BASE extends CommandOpMode {

    protected Servo intake_AR, intAR, roni_IA, intake_center_angle, roni2_intake;
    protected CRServo intake_right, intake_left;
    protected DcMotorEx FL, FR, BL, BR, armR, armL;
    protected ElapsedTime runtime = new ElapsedTime();

    protected IMU Imu;

    @Override
    public void initialize() {

        FL = hardwareMap.get(DcMotorEx.class, "FL");
        FL.setDirection(DcMotorEx.Direction.FORWARD);
        FL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        FL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        FL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        FR = hardwareMap.get(DcMotorEx.class, "FR");
        FR.setDirection(DcMotorEx.Direction.REVERSE);
        FR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        FR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        FR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        BL = hardwareMap.get(DcMotorEx.class, "BL");
        BL.setDirection(DcMotorEx.Direction.FORWARD);
        BL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        BL.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        BR = hardwareMap.get(DcMotorEx.class, "BR");
        BR.setDirection(DcMotorEx.Direction.REVERSE);
        BR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        BR.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        armR = hardwareMap.get(DcMotorEx.class,"ER");
        armR.setDirection(DcMotorEx.Direction.REVERSE );
        armR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armR.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armR.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


        armL = hardwareMap.get(DcMotorEx.class,"EL");
        armL.setDirection(DcMotorEx.Direction.REVERSE);
        armL.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        armL.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        armL.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);


        intake_right = hardwareMap.get(CRServo.class, "axonR");
        intake_left = hardwareMap.get(CRServo.class, "axonL");

        intake_AR = hardwareMap.get(Servo.class, "ointR");
        intake_AR.setPosition(0.75);

        roni_IA = hardwareMap.get(Servo.class, "roni");
        roni_IA.setPosition(0.45);

        roni2_intake = hardwareMap.get(Servo.class, "roni2");

        intAR =  hardwareMap.get(Servo.class, "intAR");

        intake_center_angle = hardwareMap.get(Servo.class, "intakeWheel");

        Imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        Imu.initialize(parameters);


        double forward = -gamepad1.left_stick_y; //-1 to 1
        double turn = gamepad1.right_stick_x;
        double drift = gamepad1.left_stick_x;
        double botHeading = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
        double botDeg = Imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);

        DriveTrainSub subDrive = new DriveTrainSub(BR, BL, FR, FL, telemetry, Imu);
        drive dr = new drive(subDrive,forward, drift, turn, botHeading);
        Elevator_based lift_Base = new Elevator_based(armL, armR, intake_center_angle, intake_left, intake_right, intake_AR, intAR, telemetry);

        schedule(dr);

        register(subDrive,lift_Base);
    }

}
