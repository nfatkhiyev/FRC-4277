package org.usfirst.frc.team4277.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearTiltUpDependable extends CommandGroup {

    public GearTiltUpDependable() {
    	addSequential(new GearTiltUp());
    	
    	//adjustment code
    	
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
    	
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
    }
}
