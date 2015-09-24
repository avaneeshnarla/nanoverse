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

package compiler.pipeline.instantiate.loader.geometry.shape;

import compiler.pipeline.instantiate.factory.geometry.shape.CuboidFactory;
import compiler.pipeline.translate.nodes.MapObjectNode;
import control.GeneralParameters;
import geometry.lattice.Lattice;
import geometry.shape.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by dbborens on 8/4/2015.
 */
public class CuboidLoader extends ShapeLoader<Cuboid> {
    private final CuboidFactory factory;
    private final CuboidInterpolator interpolator;

    public CuboidLoader() {
        factory = new CuboidFactory();
        interpolator = new CuboidInterpolator();
    }

    public CuboidLoader(CuboidFactory factory,
                        CuboidInterpolator interpolator) {

        this.factory = factory;
        this.interpolator = interpolator;
    }

    @Override
    public Shape instantiate(MapObjectNode node, Lattice lattice, GeneralParameters p) {
        factory.setLattice(lattice);

        int height = interpolator.height(node, p.getRandom());
        factory.setHeight(height);

        int width = interpolator.width(node, p.getRandom());
        factory.setWidth(width);

        int depth = interpolator.depth(node, p.getRandom());
        factory.setDepth(depth);

        return factory.build();
    }

    public Shape instantiate(Lattice lattice, GeneralParameters p) {
        return instantiate(null, lattice, p);
    }
}
