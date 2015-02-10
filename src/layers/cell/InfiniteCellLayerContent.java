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

package layers.cell;

import control.identifiers.Coordinate;
import geometry.Geometry;
import structural.CanonicalCellMap;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by David B Borenstein on 4/10/14.
 */
public class InfiniteCellLayerContent extends CellLayerContent {

    public InfiniteCellLayerContent(Geometry geom, CellLayerIndices indices) {
        super(geom, indices);
    }

    @Override
    public InfiniteCellLayerContent clone() {
        CanonicalCellMap clonedMap = new CanonicalCellMap(map);
        HashSet<Coordinate> clonedSites = new HashSet<>(canonicalSites);
        CellLayerIndices clonedIndices = indices.clone(clonedMap);
        InfiniteCellLayerContent clone = new InfiniteCellLayerContent(geom, clonedIndices);
        clone.map = clonedMap;
        clone.canonicalSites = clonedSites;
        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        return true;
    }

    @Override
    public void sanityCheck(Coordinate coord) {
    }

    @Override
    public Set<Coordinate> getImaginarySites() {
        HashSet<Coordinate> ret = new HashSet<>(getOccupiedSites().size());

        for (Coordinate c : getOccupiedSites()) {
            if (!hasCanonicalForm(c)) {
                ret.add(c);
            }
        }
        return ret;
    }
}
