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

package nanoverse.compiler.pipeline.translate.symbol.processes;

import nanoverse.compiler.pipeline.translate.symbol.MapSymbolTable;
import nanoverse.runtime.control.arguments.*;
import nanoverse.runtime.processes.MockProcess;
import org.junit.Test;

public class MockProcessInstSymbolTableTest extends ProcessInstSymbolTableTest {

    @Override
    protected MapSymbolTable getQuery() {
        return new MockProcessInstSymbolTable();
    }

    @Override
    protected Class getExpectedClass() {
        return MockProcess.class;
    }

    @Test
    public void weight() {
        verifyReturnSymbol("weight", DoubleArgument.class);
    }

    @Test
    public void identifier() {
        verifyReturnSymbol("identifier", StringArgument.class);
    }

    @Test
    public void count() {
        verifyReturnSymbol("count", IntegerArgument.class);
    }
}