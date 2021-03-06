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

package nanoverse.runtime.geometry.shape;

import nanoverse.runtime.control.identifiers.*;
import nanoverse.runtime.geometry.lattice.*;
import org.junit.*;
import test.LegacyTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class LineTest extends LegacyTest {

    private Shape odd;
    private Shape even;

    private Lattice evenLattice;

    @Before
    public void setUp() {
        Lattice oddLattice = new LinearLattice();
        evenLattice = new LinearLattice();

        even = new Line(evenLattice, 4);
        odd = new Line(oddLattice, 5);
    }

    @Test
    public void testGetCenter() {
        Coordinate expected, actual;

        // Even -- we round down
        expected = new Coordinate2D(0, 1, 0);
        actual = even.getCenter();
        assertEquals(expected, actual);

        // Odd
        expected = new Coordinate2D(0, 2, 0);
        actual = odd.getCenter();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBoundaries() {
        Coordinate[] expected, actual;

        // Even
        expected = new Coordinate[]{
            new Coordinate2D(0, 0, 0),
            new Coordinate2D(0, 3, 0),
        };
        actual = even.getBoundaries();
        assertArraysEqual(expected, actual, true);

        // Odd
        expected = new Coordinate[]{
            new Coordinate2D(0, 0, 0),
            new Coordinate2D(0, 4, 0),
        };
        actual = odd.getBoundaries();
        assertArraysEqual(expected, actual, true);
    }

    @Test
    public void testCanonicalSites() {
        Coordinate[] expected, actual;

        expected = new Coordinate[]{
            new Coordinate2D(0, 0, 0),
            new Coordinate2D(0, 1, 0),
            new Coordinate2D(0, 2, 0),
            new Coordinate2D(0, 3, 0),
        };

        actual = even.getCanonicalSites();
        assertArraysEqual(expected, actual, true);
    }

    @Test
    public void testOverbounds() {
        Coordinate expected, actual;

        // Test coordinates -- in bounds
        Coordinate a, b;

        a = new Coordinate2D(0, 0, 0);
        b = new Coordinate2D(0, 2, 0);

        // Test coordinates -- out of bounds
        Coordinate q, r;
        q = new Coordinate2D(0, 6, 0);
        r = new Coordinate2D(0, -1, 0);

        // Even
        expected = new Coordinate2D(0, 0, Flags.VECTOR);
        actual = even.getOverbounds(a);
        assertEquals(expected, actual);

        expected = new Coordinate2D(0, 0, Flags.VECTOR);
        actual = even.getOverbounds(b);
        assertEquals(expected, actual);


        expected = new Coordinate2D(0, 3, Flags.VECTOR);
        actual = even.getOverbounds(q);
        assertEquals(expected, actual);

        expected = new Coordinate2D(0, -1, Flags.VECTOR);
        actual = even.getOverbounds(r);
        assertEquals(expected, actual);

        // Odd
        expected = new Coordinate2D(0, 0, Flags.VECTOR);
        actual = odd.getOverbounds(a);
        assertEquals(expected, actual);

        expected = new Coordinate2D(0, 0, Flags.VECTOR);
        actual = odd.getOverbounds(b);
        assertEquals(expected, actual);

        expected = new Coordinate2D(0, 2, Flags.VECTOR);
        actual = odd.getOverbounds(q);
        assertEquals(expected, actual);

        expected = new Coordinate2D(0, -1, Flags.VECTOR);
        actual = odd.getOverbounds(r);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetDistanceOverBoundary() {
        int expected, actual;

        // Test coordinates -- in bounds
        Coordinate a, b;

        a = new Coordinate2D(0, 0, 0);
        b = new Coordinate2D(0, 2, 0);

        // Test coordinates -- out of bounds
        Coordinate q, r;
        q = new Coordinate2D(0, 6, 0);
        r = new Coordinate2D(0, -1, 0);

        // Even
        expected = 0;
        actual = even.getDistanceOverBoundary(a);
        assertEquals(expected, actual);

        expected = 0;
        actual = even.getDistanceOverBoundary(b);
        assertEquals(expected, actual);


        expected = 3;
        actual = even.getDistanceOverBoundary(q);
        assertEquals(expected, actual);

        expected = 1;
        actual = even.getDistanceOverBoundary(r);
        assertEquals(expected, actual);

        // Odd
        expected = 0;
        actual = odd.getDistanceOverBoundary(a);
        assertEquals(expected, actual);

        expected = 0;
        actual = odd.getDistanceOverBoundary(b);
        assertEquals(expected, actual);

        expected = 2;
        actual = odd.getDistanceOverBoundary(q);
        assertEquals(expected, actual);

        expected = 1;
        actual = odd.getDistanceOverBoundary(r);
        assertEquals(expected, actual);
    }

    @Test
    public void testDimensions() {
        int[] expected, actual;

        // Odd
        expected = new int[]{5};
        actual = odd.getDimensions();
        assertArraysEqual(expected, actual, false);

        // Even
        expected = new int[]{4};
        actual = even.getDimensions();
        assertArraysEqual(expected, actual, false);
    }

    @Test
    public void testCloneAtScale() {
        Lattice clonedLattice = evenLattice.clone();
        Shape cloned = even.cloneAtScale(clonedLattice, 2.0);

        assertEquals(even.getClass(), cloned.getClass());
        assertEquals(4, even.getCanonicalSites().length);
        assertEquals(8, cloned.getCanonicalSites().length);
    }

}