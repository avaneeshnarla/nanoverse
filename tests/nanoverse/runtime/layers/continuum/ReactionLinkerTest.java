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


import no.uib.cipr.matrix.*;
import no.uib.cipr.matrix.sparse.CompDiagMatrix;
import org.junit.*;
import test.LinearMocks;

import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ReactionLinkerTest extends LinearMocks {

    private Vector capturedVector;
    private Matrix capturedMatrix;

    private AgentToOperatorHelper helper;
    private Stream<RelationshipTuple> stream;

    private ReactionLinker query;

    @Before
    public void init() {
        // For some reason, I can't set up a captor on a consumer, so I am doing this.
        Consumer<DenseVector> vectorCaptor = this::captureVector;
        Consumer<CompDiagMatrix> matrixCaptor = this::captureMatrix;

        helper = mock(AgentToOperatorHelper.class);
        stream = (Stream<RelationshipTuple>) mock(Stream.class);

        query = new ReactionLinker(vectorCaptor, matrixCaptor, helper, true);
    }

    @Test
    public void applyResolvesInjections() throws Exception {
        DenseVector vector = vector(1.0, 2.0, 3.0);
        when(helper.getSource(any())).thenReturn(vector);
        query.apply(stream);
        assertEquals(vector, capturedVector);
    }

    @Test
    public void applyResolvesExponentiations() throws Exception {
        CompDiagMatrix matrix = matrix(1.0, 2.0, 3.0);
        when(helper.getOperator(any())).thenReturn(matrix);
        query.apply(stream);
        assertEquals(matrix, capturedMatrix);
    }

    private void captureVector(Vector vector) {
        capturedVector = vector;
    }

    private void captureMatrix(Matrix matrix) {
        capturedMatrix = matrix;
    }
}