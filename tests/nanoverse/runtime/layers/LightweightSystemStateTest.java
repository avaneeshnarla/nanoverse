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

package nanoverse.runtime.layers;

import nanoverse.runtime.agent.Agent;
import nanoverse.runtime.control.identifiers.Coordinate;
import nanoverse.runtime.geometry.Geometry;
import nanoverse.runtime.io.deserialize.MockCoordinateDeindexer;
import nanoverse.runtime.layers.cell.AgentLayer;
import org.junit.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by dbborens on 3/26/14.
 * <p>
 */
public class LightweightSystemStateTest extends SystemStateTest {

    private LightweightSystemState query;
    private Coordinate[] canonicals;
    private Geometry g;
    private List<String> nameVector;
    @Before
    public void setUp() throws Exception {
        MockCoordinateDeindexer deindexer = new MockCoordinateDeindexer();
        g = buildMockGeometry();
        canonicals = g.getCanonicalSites();
        deindexer.setUnderlying(canonicals);
        query = new LightweightSystemState(g);
        nameVector = Stream.of("1", "0", "2", "3", "2").collect(Collectors.toList());
        query.setAgentNames(nameVector.stream());

    }

    @Override
    @Test
    public void testGetState() throws Exception {
        for (int i = 0; i < 4; i++) {
            Coordinate coord = canonicals[i];
            String expected = nameVector.get(i);
            String actual = query.getLayerManager().getAgentLayer().getViewer().getName(coord);
            assertEquals(expected, actual);
        }
    }

    @Override
    @Test
    public void testGetTime() throws Exception {
        double expected = 0.7;
        query.setTime(expected);
        double actual = query.getTime();
        assertEquals(expected, actual, epsilon);
    }

    @Override
    @Test
    public void testGetFrame() throws Exception {
        int expected = 7;
        query.setFrame(expected);
        int actual = query.getFrame();
        assertEquals(expected, actual);
    }

    @Override
    @Test
    public void testIsHighlighted() throws Exception {
        int channelId = 0;
        Set<Coordinate> expected = new HashSet<>(1);
        expected.add(canonicals[2]);
        query.setHighlights(channelId, expected);

        assertTrue(query.isHighlighted(channelId, canonicals[2]));
        assertFalse(query.isHighlighted(channelId, canonicals[0]));
    }

    @Test
    public void testGetLayerManager() throws Exception {
        MockLayerManager expected = new MockLayerManager();

        AgentLayer cellLayer = new AgentLayer(g);
        expected.setAgentLayer(cellLayer);

        for (int i = 0; i < g.getCanonicalSites().length; i++) {
            Coordinate c = g.getCanonicalSites()[i];
            String name = nameVector.get(i);

            if (name != null) {
                Agent cell = new Agent(expected, name, null);
                cellLayer.getUpdateManager().place(cell, c);
            }
        }

        LayerManager actual = query.getLayerManager();
        assertEquals(expected, actual);
    }
}
