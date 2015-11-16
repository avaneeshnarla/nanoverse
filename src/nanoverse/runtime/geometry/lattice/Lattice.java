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

package nanoverse.runtime.geometry.lattice;

import nanoverse.runtime.control.identifiers.*;

public abstract class Lattice {

    /**
     * The number of spatial dimensions of the basis vector for the lattice.
     * Not all geometries use orthogonal bases; for example, a triangular
     * 2D lattice has dimensionality 2 and connectivity 3.
     */
    public abstract int getConnectivity();

    /**
     * The number of spatial dimensions of the lattice.
     *
     * @return
     */
    public abstract int getDimensionality();

    /**
     * Returns the neighbors around the specified coordinate, assuming
     * infinite boundary conditions.
     *
     * @param coord
     * @return
     */
    public Coordinate[] getNeighbors(Coordinate coord) {
        return getAnnulus(coord, 1);
    }

    /**
     * Returns the annulus of radius r around the specified coordinate,
     * assuming infinite boundary conditions.
     */
    public abstract Coordinate[] getAnnulus(Coordinate coord, int r);

    /**
     * Get a displacement vector for the specified coordinates. If basis vectors
     * are non-orthogonal, getOrthoIDisplacement() returns a displacement
     * vector such that all non-zero components are orthogonal. For many
     * geometries, this is identical to getDisplacement().
     *
     * @param pCoord
     * @param qCoord
     * @return
     */
    // TODO DELETE ME ****
    public abstract Coordinate getOrthoDisplacement(Coordinate pCoord,
                                                    Coordinate qCoord);

    /**
     * Traverses the specified displacement from a given coordinate, and
     * gives the resulting location back as a new coordinate.
     */
    public abstract Coordinate rel2abs(Coordinate coord, Coordinate displacement);

    // Get the L1 distance between the specified sites.
    // TODO DELETE ME ****
    public int getL1Distance(Coordinate p, Coordinate q) {

        // Get displacement vector
        Coordinate vec = getDisplacement(p, q);

        // Calculate L1 norm. If basis is only 2D, third component will be zero
        int res = Math.abs(vec.x()) + Math.abs(vec.y());

        if (!vec.hasFlag(Flags.PLANAR)) {
            res += Math.abs(vec.z());
        }

        return (res);
    }

    /**
     * Get the displacement vector between the specified coordinates. The
     * displacement vector is given in units of the lattice's basis vector.
     * The displacement assumes infinite boundary conditions.
     * <p>
     * In the event that the basis of the lattice is linearly dependent, as in
     * the case of the triangular lattice, the displacement vector returned
     * by this method should minimize the L1 distance. Linear independence of
     * components can be enforced using getOrthoDisplacement().
     */
    public abstract Coordinate getDisplacement(Coordinate pCoord, Coordinate qCoord);

    /**
     * Lattice objects are equal if and only if they are of the same
     * class. Since the lattice object describes only the graph
     * connectivity, lattice objects need not be associated with
     * identical space shapes or boundaries in order to be considered
     * equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }

        return true;
    }

    public abstract int getNeighborhoodDistance(Coordinate p, Coordinate q);

    @Override
    public abstract Lattice clone();

    public abstract Coordinate getZeroVector();
}
