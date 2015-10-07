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

package nanoverse.compiler.pipeline.instantiate.loader.geometry.set;

import nanoverse.compiler.pipeline.instantiate.helpers.LoadHelper;
import nanoverse.compiler.pipeline.instantiate.loader.control.identifiers.CoordinateLoader;
import nanoverse.compiler.pipeline.translate.nodes.MapObjectNode;
import nanoverse.runtime.control.GeneralParameters;
import nanoverse.runtime.control.identifiers.Coordinate;
import nanoverse.runtime.geometry.Geometry;
import nanoverse.runtime.layers.LayerManager;

import java.util.Random;

/**
 * Created by dbborens on 9/19/2015.
 */
public class HLineCoordinateSetInterpolator {
    private final LoadHelper load;
    private final HLineCoordinateSetDefaults defaults;

    public HLineCoordinateSetInterpolator() {
        load = new LoadHelper();
        defaults = new HLineCoordinateSetDefaults();
    }

    public HLineCoordinateSetInterpolator(LoadHelper load,
                                          HLineCoordinateSetDefaults defaults) {

        this.load = load;
        this.defaults = defaults;
    }

    public int length(MapObjectNode node, Random random) {
        return load.anInteger(node, "length", random, defaults::length);
    }

    public Geometry geometry(LayerManager lm) {
        return lm.getCellLayer().getGeometry();
    }

    public Coordinate origin(MapObjectNode node, LayerManager lm, GeneralParameters p) {
        CoordinateLoader loader = (CoordinateLoader) load.getLoader(node, "origin", false);
        if (loader == null) {
            return defaults.origin(lm);
        }

        MapObjectNode cNode = (MapObjectNode) node.getMember("origin");
        return loader.instantiate(cNode, lm, p);
    }
}