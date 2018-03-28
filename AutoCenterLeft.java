package org.usfirst.frc.team4277.robot.commands.auto.groups;

import org.usfirst.frc.team4277.robot.commands.OuttakeCubeCommand;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDrive;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveBack;
import org.usfirst.frc.team4277.robot.commands.auto.AutoDriveSideGyro;
import org.usfirst.frc.team4277.robot.commands.auto.AutoSpinLeftR;
import org.usfirst.frc.team4277.robot.commands.auto.AutoStop;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCenterLeft extends CommandGroup {

    public AutoCenterLeft() {
    	addSequential(new AutoDrive(0.5));
    	addSequential(new AutoSpinLeftR(25));
    	addSequential(new AutoDriveSideGyro(295,1.0));
    	addSequential(new AutoSpinLeftR(180));
    	addSequential(new AutoDriveBack(2.0));
    	addSequential(new AutoStop());
    	addSequential(new OuttakeCubeCommand());
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
