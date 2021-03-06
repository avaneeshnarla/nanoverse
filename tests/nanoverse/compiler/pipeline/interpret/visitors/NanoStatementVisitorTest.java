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

package nanoverse.compiler.pipeline.interpret.visitors;

import nanoverse.compiler.pipeline.interpret.nanosyntax.NanosyntaxParser.*;
import nanoverse.compiler.pipeline.interpret.nodes.ASTNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.*;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

public class NanoStatementVisitorTest {

    private NanoStandaloneIdVisitor idVisitor;
    private NanoPrimitiveVisitor primitiveVisitor;
    private NanoAssignmentVisitor assignmentVisitor;

    private StatementContext ctx;
    private ParseTree child;
    private ASTNode expected;

    private NanoStatementVisitor query;

    @Before
    public void before() throws Exception {
        idVisitor = mock(NanoStandaloneIdVisitor.class);
        primitiveVisitor = mock(NanoPrimitiveVisitor.class);
        assignmentVisitor = mock(NanoAssignmentVisitor.class);

        query = new NanoStatementVisitor(idVisitor, primitiveVisitor, assignmentVisitor);

        ctx = mock(StatementContext.class);
        when(ctx.getChildCount()).thenReturn(1);

        expected = mock(ASTNode.class);
    }

    @Test(expected = IllegalStateException.class)
    public void illegalContextThrows() throws Exception {
        ParseTree child = mock(ParseTree.class);
        when(ctx.getChild(0)).thenReturn(child);
        query.visitStatement(ctx);
    }

    @Test
    public void assignmentCase() throws Exception {
        configureContext(AssignmentContext.class);
        when(child.accept(assignmentVisitor)).thenReturn(expected);
        verifyReturn();
    }

    private void configureContext(Class clazz) {
        child = (ParseTree) mock(clazz);
        ctx = mock(StatementContext.class);
        when(ctx.getChild(0)).thenReturn(child);
    }

    private void verifyReturn() {
        ASTNode actual = query.visitStatement(ctx);
        assertSame(expected, actual);
    }

    @Test
    public void primitiveCase() throws Exception {
        configureContext(PrimitiveContext.class);
        when(child.accept(primitiveVisitor)).thenReturn(expected);
        verifyReturn();
    }

    @Test
    public void standaloneIdCase() throws Exception {
        configureContext(IdContext.class);
        when(child.accept(idVisitor)).thenReturn(expected);
        verifyReturn();
    }
}