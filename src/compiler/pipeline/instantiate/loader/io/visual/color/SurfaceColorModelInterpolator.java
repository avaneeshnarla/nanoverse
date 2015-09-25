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

package compiler.pipeline.instantiate.loader.io.visual.color;

import compiler.pipeline.instantiate.helpers.LoadHelper;
import compiler.pipeline.translate.nodes.MapObjectNode;
import control.arguments.DoubleArgument;

import java.util.Random;

/**
 * Created by dbborens on 8/24/2015.
 */
public class SurfaceColorModelInterpolator {

    private final LoadHelper load;
    private final SurfaceColorModelDefaults defaults;

    public SurfaceColorModelInterpolator() {
        load = new LoadHelper();
        defaults = new SurfaceColorModelDefaults();
    }

    public SurfaceColorModelInterpolator(LoadHelper load,
                                         SurfaceColorModelDefaults defaults) {

        this.load = load;
        this.defaults = defaults;
    }


    public DoubleArgument luminance(MapObjectNode node, Random random) {
        return load.aDoubleArgument(node, "luminance", random, defaults::luminance);
    }

    public DoubleArgument saturation(MapObjectNode node, Random random) {
        return load.aDoubleArgument(node, "saturation", random, defaults::saturation);
    }
}