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

package nanoverse.runtime.layers.continuum;

import org.junit.*;
import test.LinearMocks;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ContinuumAgentManagerTest extends LinearMocks {

    private ContinuumAgentIndex index;
    private ReactionLinker loader;
    private String id;

    private ContinuumAgentManager query;

    @Before
    public void init() {
        id = "test";
        index = mock(ContinuumAgentIndex.class);

        loader = mock(ReactionLinker.class);

        query = new ContinuumAgentManager(loader, index, id);
    }

    @Test
    public void applyLoadsRelationshipsFromIndex() {
        Stream<RelationshipTuple> relationships = (Stream<RelationshipTuple>) mock(Stream.class);
        when(index.getRelationships()).thenReturn(relationships);
        query.apply();
        verify(loader).apply(relationships);
    }


    @Test
    public void resetCallsIndex() {
        query.reset();
        verify(index).reset();
    }

    public void getId() {
        assertEquals(id, query.getId());
    }
}