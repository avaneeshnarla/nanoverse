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

package nanoverse.runtime.layers.continuum.solvers;

import nanoverse.runtime.layers.continuum.*;
import nanoverse.runtime.structural.utilities.MatrixUtils;
import no.uib.cipr.matrix.*;
import no.uib.cipr.matrix.sparse.CompDiagMatrix;
import org.junit.*;
import org.mockito.ArgumentCaptor;
import test.TestBase;

import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

public class NonEquilibriumSolverTest extends TestBase {

    private static final int RANGE = 10;

    private ContinuumLayerContent content;
    private Vector state, source;
    private CompDiagMatrix operator;
    private ScheduledOperations so;
    private NonEquilibriumSolver query;

    @Before
    public void before() throws Exception {
        state = makeStateVector();
        content = mock(ContinuumLayerContent.class);
        when(content.getState()).thenReturn(state);

        source = makeSourceVector();
        operator = MatrixUtils.compDiagIdentity(RANGE);
        so = mock(ScheduledOperations.class);
        when(so.getOperator()).thenReturn(operator);
        when(so.getSource()).thenReturn(source);
        query = new NonEquilibriumSolver(content, so);
    }

    private Vector makeSourceVector() {
        DenseVector vector = new DenseVector(RANGE);
        vector.set(0, 0.5);
        return vector;
    }

    private Vector makeStateVector() {
        DenseVector vector = new DenseVector(RANGE);
        IntStream.range(0, RANGE)
            .boxed()
            .forEach(i -> vector.set(i, i * 1.0));
        return vector;
    }

    @Test
    public void doSolve() throws Exception {
        query.solve();
        ArgumentCaptor<Vector> ac = ArgumentCaptor.forClass(Vector.class);
        verify(content).setState(ac.capture());

        Vector expected = source.add(state);
        assertVectorsEqual(expected, ac.getValue(), epsilon);
    }
}