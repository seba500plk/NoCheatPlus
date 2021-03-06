package fr.neatmonster.nocheatplus.components;

/**
 * Can be registered with the TickTask.
 * @author mc_dev
 *
 */
public interface TickListener {
	/**
	 * 
	 * @param tick Current tick count. This might start over at 0 if reset in onEnable.
	 * @param timeLast Last time after processing loop. Allows to check how long the tick already took (roughly). No "system time ran backwards" check for this value.
	 */
	public void onTick(int tick, long timeLast);
}
