package cc.co.evenprime.bukkit.nocheat.checks.blockbreak;

import cc.co.evenprime.bukkit.nocheat.ConfigItem;
import cc.co.evenprime.bukkit.nocheat.actions.types.ActionList;
import cc.co.evenprime.bukkit.nocheat.config.ConfPaths;
import cc.co.evenprime.bukkit.nocheat.config.NoCheatConfiguration;

/**
 * Configurations specific for the "BlockBreak" checks
 * Every world gets one of these assigned to it.
 * 
 */
public class BlockBreakConfig implements ConfigItem {

    public final boolean    check;
    public final boolean    reachCheck;
    public final double     reachDistance;
    public final ActionList reachActions;
    public final boolean    directionCheck;
    public final ActionList directionActions;
    public final double     directionPrecision;
    public final long       directionPenaltyTime;
    public final boolean    noswingCheck;
    public final ActionList noswingActions;

    public BlockBreakConfig(NoCheatConfiguration data) {

        reachCheck = data.getBoolean(ConfPaths.BLOCKBREAK_REACH_CHECK);
        reachDistance = 535D / 100D;
        reachActions = data.getActionList(ConfPaths.BLOCKBREAK_REACH_ACTIONS);
        directionCheck = data.getBoolean(ConfPaths.BLOCKBREAK_DIRECTION_CHECK);
        directionPrecision = ((double) data.getInt(ConfPaths.BLOCKBREAK_DIRECTION_PRECISION)) / 100D;
        directionPenaltyTime = data.getInt(ConfPaths.BLOCKBREAK_DIRECTION_PENALTYTIME);
        directionActions = data.getActionList(ConfPaths.BLOCKBREAK_DIRECTION_ACTIONS);
        noswingCheck = data.getBoolean(ConfPaths.BLOCKBREAK_NOSWING_CHECK);
        noswingActions = data.getActionList(ConfPaths.BLOCKBREAK_NOSWING_ACTIONS);

        check = reachCheck || directionCheck || noswingCheck;

    }
}