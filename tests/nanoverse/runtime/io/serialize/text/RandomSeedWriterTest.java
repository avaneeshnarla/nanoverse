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

package nanoverse.runtime.io.serialize.text;

import nanoverse.runtime.processes.StepState;
import nanoverse.runtime.structural.MockGeneralParameters;
import org.junit.Test;
import test.*;

public class RandomSeedWriterTest extends LegacyLatticeTest {

    @Test
    public void testLifeCycle() throws Exception {
        makeFiles();
        FileAssertions.assertOutputMatchesFixture("serializations/random.txt", "random.txt", true);
//        assertFilesEqual("serializations/random.txt", "random.txt");
    }

    private void makeFiles() throws Exception {
        MockGeneralParameters p = makeMockGeneralParameters();
        p.initializeRandom(1234567890);
        RandomSeedWriter writer = new RandomSeedWriter(p, layerManager);
        writer.init();
        StepState state = new StepState(0.0, 0);
        state.record(layerManager);
        writer.flush(state);
        writer.dispatchHalt(null);
        writer.close();
    }
}