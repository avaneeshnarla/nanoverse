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

import nanoverse.runtime.control.identifiers.Coordinate2D;
import nanoverse.runtime.layers.cell.*;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by David B Borenstein on 4/10/14.
 */
public class FiniteAgentLayerContentTest extends AgentLayerContentTest {

    @Test
    public void testImaginaryBehavior() {

        boolean thrown = false;
        try {
            query.sanityCheck(new Coordinate2D(-1, 0, 0));
        } catch (Exception ex) {
            thrown = true;
        }

        assertTrue(thrown);

        assertNotNull(query.getImaginarySites());
        assertEquals(0, query.getImaginarySites().count());
    }

    @Override
    public AgentLayerContent makeQuery() {
        return new FiniteAgentLayerContent(geom, indices);
    }
}
