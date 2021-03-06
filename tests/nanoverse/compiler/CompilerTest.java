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

package nanoverse.compiler;

import nanoverse.compiler.pipeline.instantiate.loader.control.ProjectLoader;
import nanoverse.compiler.pipeline.interpret.Interpreter;
import nanoverse.compiler.pipeline.interpret.nodes.ASTNode;
import nanoverse.compiler.pipeline.translate.nodes.MapObjectNode;
import nanoverse.compiler.pipeline.translate.symbol.control.run.ProjectSymbolTable;
import nanoverse.compiler.pipeline.translate.visitors.MasterTranslationVisitor;
import nanoverse.runtime.control.run.Runner;
import org.junit.*;
import org.mockito.ArgumentCaptor;

import java.io.File;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CompilerTest {

    private String targetFilename;
    private Interpreter interpreter;
    private ProjectSymbolTable rootIST;
    private MasterTranslationVisitor translator;
    private ProjectLoader instantiator;

    private Compiler query;

    @Before
    public void before() throws Exception {
        targetFilename = "test";
        interpreter = mock(Interpreter.class);
        rootIST = mock(ProjectSymbolTable.class);
        translator = mock(MasterTranslationVisitor.class);
        instantiator = mock(ProjectLoader.class);

        query = new Compiler(targetFilename, interpreter, rootIST,
            translator, instantiator);
    }

    @Test
    public void testLifeCycle() throws Exception {
        ASTNode rootASTNode = mock(ASTNode.class);
        when(interpreter.interpret(any())).thenReturn(rootASTNode);

        MapObjectNode rootObjNode = mock(MapObjectNode.class);
        when(translator.translate(rootASTNode, rootIST)).thenReturn(rootObjNode);

        Runner expected = mock(Runner.class);
        when(instantiator.instantiate(rootObjNode)).thenReturn(expected);

        // First: check that you get the expected outcome
        Runner actual = query.compile();
        assertSame(expected, actual);

        // Next: verify that the one non-mocked argument is correct
        ArgumentCaptor<File> fileCaptor = ArgumentCaptor.forClass(File.class);
        verify(interpreter).interpret(fileCaptor.capture());
        File file = fileCaptor.getValue();
        assertEquals(targetFilename, file.getName());
    }
}