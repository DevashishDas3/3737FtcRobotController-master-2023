
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class HardwarePushbot
{
    /* Public OpMode members. */
    public DcMotor  leftFront   = null;
    public DcMotor  rightFront = null;
    public DcMotor  leftBack = null;
    public DcMotor  rightBack = null;


    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    public void init(HardwareMap ahwMap) {

        hwMap = ahwMap;


        leftFront  = hwMap.get(DcMotor.class, "LF");
        leftBack  = hwMap.get(DcMotor.class, "LB");
        rightFront  = hwMap.get(DcMotor.class, "RF");
        rightBack  = hwMap.get(DcMotor.class, "RB");

        // Reversing left motors
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);


        setMotorPowers(0);


        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    public void setMotorPowers(double LFPower, double RFPower, double LBPower, double RBPower) {
        leftFront.setPower(LFPower);
        rightFront.setPower(RFPower);
        leftBack.setPower(LBPower);
        rightBack.setPower(RBPower);
    }

    public void setMotorPowers(double allPower) {
        setMotorPowers(allPower, allPower, allPower, allPower);
    }

    public void autoMove(double LFPower, double RFPower, double LBPower, double RBPower, ElapsedTime runtime, double time, LinearOpMode opMode, Telemetry telemetry){
        setMotorPowers(LFPower, RFPower, LBPower, RBPower);
        runtime.reset();
        while (opMode.opModeIsActive() && runtime.milliseconds() < time){
            telemetry.addData("autoMove runs", "for" + time + " seconds");
            telemetry.addData("LFPower", LFPower);
            telemetry.addData("RFPower", RFPower);
            telemetry.addData("LBPower", LBPower);
            telemetry.addData("RBPower", RBPower);
            telemetry.update();
        }
        setMotorPowers(0);
    }
