package nanoverse.runtime.agent.action.displacement;

import nanoverse.runtime.control.halt.HaltCondition;
import nanoverse.runtime.control.identifiers.Coordinate;
import nanoverse.runtime.geometry.Geometry;
import nanoverse.runtime.layers.cell.AgentLayer;
import org.slf4j.*;

import java.util.*;
import java.util.function.BiFunction;

/**
 * ShortestPathShover pushes on a row of agents until the outermost agent has
 * been moved to a terminal (vacant) point).
 * <p>
 * Created by dbborens on 10/20/2015.
 */
public class ShortestPathShover {
    private final Logger logger;

    private final AgentLayer layer;

    private final ShoveHelper shoveHelper;
    private final ShoveOperationManager operationManager;

    public ShortestPathShover(AgentLayer layer, Random random) {
        this.layer = layer;
        shoveHelper = new ShoveHelper(layer, random);
        operationManager = new ShoveOperationManager(shoveHelper, baseCaseFunction());
        logger = LoggerFactory.getLogger(ShortestPathShover.class);
    }

    public ShortestPathShover(AgentLayer layer, ShoveHelper shoveHelper, ShoveOperationManager operationManager) {
        this.layer = layer;
        this.shoveHelper = shoveHelper;
        this.operationManager = operationManager;
        logger = LoggerFactory.getLogger(ShortestPathShover.class);
    }

    private BiFunction<Coordinate, Coordinate, Boolean> baseCaseFunction() {
        return (current, displacement) -> isBaseCase(displacement);
    }

    private boolean isBaseCase(Coordinate displacement) {
        return (displacement.norm() == 0);
    }

    /**
     * Push the row of agents at origin toward target, such that origin
     * winds up vacant. Return a list of affected agents.
     *
     * @param origin The site to become vacant.
     * @param target A currently unoccupied site that will become occupied at
     *               the end of the shove process. The entire line of agents,
     *               including the cell at the origin, will have been pushed
     *               in the direction of the target.
     * @return A set of coordinates that were affected by the shove operation.
     */
    public HashSet<Coordinate> shove(Coordinate origin, Coordinate target) throws HaltCondition {
        HashSet<Coordinate> affectedSites = new HashSet<>();

        Coordinate displacement = layer.getGeometry().
            getDisplacement(origin, target, Geometry.APPLY_BOUNDARIES);

        logger.debug("Preparing to shove {} toward {}. Displacement: {}.", origin, target, displacement);

        operationManager.doShove(origin, displacement, affectedSites);
        return affectedSites;
    }
}
