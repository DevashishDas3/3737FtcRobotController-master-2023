

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Autonomous code for programmer training. Will move forward for 5 seconds and turn for 3 seconds.
 */

@Autonomous(name="Pushbot: Auto Drive By Time", group="Pushbot")
public class PushbotAutoDriveByTime_Linear extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     FORWARD_SPEED = 0.5;
    static final double     TURN_SPEED    = 0.5;

    @Override
    public void runOpMode() {


        robot.init(hardwareMap);


        telemetry.addData("Status", "Ready to run");    //
        telemetry.update();

        waitForStart();

        for (int i = 0; i < 4; i++) {
            robot.setMotorPowers(FORWARD_SPEED);
            runtime.reset();
            while (opModeIsActive() && runtime.milliseconds() < 3000) {
                telemetry.addData("moving forward", "");
                telemetry.update();
            }
            robot.setMotorPowers(0);
            sleep(1000);

            robot.setMotorPowers(-TURN_SPEED, -TURN_SPEED, TURN_SPEED, TURN_SPEED);
            runtime.reset();
            while (opModeIsActive() && runtime.milliseconds() < 2000) {
                telemetry.addData("turning", "");
                telemetry.update();
            }
            robot.setMotorPowers(0);
            sleep(1000);
        }

        robot.autoMove(FORWARD_SPEED, runtime, 3, this, telemetry);
        sleep(1000);
        robot.autoMove(-FORWARD_SPEED, FORWARD_SPEED, FORWARD_SPEED, -FORWARD_SPEED, runtime, 3, this, telemetry);
        sleep(1000);
        robot.autoMove(-FORWARD_SPEED, runtime, 3, this, telemetry);
        sleep(1000);
        robot.autoMove(FORWARD_SPEED, -FORWARD_SPEED, -FORWARD_SPEED, FORWARD_SPEED, runtime, 3, this, telemetry);
        sleep(1000);
    }
}