/*
 * Copyright (c) 2014, 2015 David Bruce Borenstein and the
 * Trustees of Princeton University.
 *
 * This file is part of the Nanoverse simulation framework
 * (patent pending).
 *
 * This program is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU Affero General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Affero General
 * Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package agent.targets;

import cells.BehaviorCell;
import control.identifiers.Coordinate;
import geometry.Geometry;
import layers.LayerManager;
import layers.cell.CellLayerViewer;
import processes.discrete.filter.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Targets specify which cells should receive the consequences
 * of an Action.
 * Created by dbborens on 2/7/14.
 */
public class TargetVacantNeighbors extends TargetRule {

    public TargetVacantNeighbors(BehaviorCell callback, LayerManager layerManager, Filter filter, int maximum, Random random) {
        super(callback, layerManager, filter, maximum, random);
    }

    @Override
    public TargetRule clone(BehaviorCell child) {
        return new TargetVacantNeighbors(child, layerManager, filter, maximum, random);
    }

    @Override
    protected List<Coordinate> getCandidates(BehaviorCell caller) {
        // Get geometry
        Geometry geom = layerManager.getCellLayer().getGeometry();

        // Get cell layer viewer
        CellLayerViewer viewer = layerManager.getCellLayer().getViewer();

        // Get self coordinate
        Coordinate self = layerManager.getCellLayer().getLookupManager().getCellLocation(callback);

        // Get coordinates of neighbors from geometry
        Coordinate[] neighbors = geom.getNeighbors(self, Geometry.APPLY_BOUNDARIES);

        // Create an array list of neighbors that are vacant
        ArrayList<Coordinate> vacNeighbors = new ArrayList<>(neighbors.length);

        for (Coordinate neighbor : neighbors) {
            if (!viewer.isOccupied(neighbor)) {
                vacNeighbors.add(neighbor);
            }
        }

        // Return the array
        return vacNeighbors;
    }
}
