/*
 * Nanoverse: a declarative agent-based modeling language for natural and
 * social science.
 *
 * Copyright (c) 2015 David Bruce Borenstein and Nanoverse, LLC.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package nanoverse.compiler.pipeline.instantiate.loader.geometry;

import nanoverse.compiler.pipeline.instantiate.factory.geometry.GeometryDescriptorDefaults;
import nanoverse.compiler.pipeline.instantiate.helpers.LoadHelper;
import nanoverse.compiler.pipeline.instantiate.loader.geometry.lattice.LatticeLoader;
import nanoverse.compiler.pipeline.instantiate.loader.geometry.shape.ShapeLoader;
import nanoverse.compiler.pipeline.translate.nodes.MapObjectNode;
import nanoverse.runtime.control.GeneralParameters;
import nanoverse.runtime.geometry.lattice.Lattice;
import nanoverse.runtime.geometry.shape.Shape;

/**
 * Created by dbborens on 8/19/2015.
 */
public class GeometryDescriptorInterpolator {

    private final LoadHelper load;
    private final GeometryDescriptorDefaults defaults;

    public GeometryDescriptorInterpolator() {
        load = new LoadHelper();
        defaults = new GeometryDescriptorDefaults();
    }

    public GeometryDescriptorInterpolator(LoadHelper load, GeometryDescriptorDefaults defaults) {
        this.load = load;
        this.defaults = defaults;
    }

    public Lattice lattice(MapObjectNode node) {
        LatticeLoader loader = (LatticeLoader)
            load.getLoader(node, "lattice", false);

        if (loader == null) {
            return defaults.lattice();
        }

        Lattice lattice = loader.instantiate();
        return lattice;
    }

    public Shape shape(MapObjectNode node, Lattice lattice, GeneralParameters p) {
        ShapeLoader loader = (ShapeLoader)
            load.getLoader(node, "shape", false);

        if (loader == null) {
            return defaults.shape(lattice, p);
        }

        MapObjectNode childNode = (MapObjectNode) node.getMember("shape");

        Shape shape = loader.instantiate(childNode, lattice, p);
        return shape;
    }
}
