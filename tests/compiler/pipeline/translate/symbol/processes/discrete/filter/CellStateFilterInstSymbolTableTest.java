/*
 * Copyright (c) 2014, 2015 David Bruce Borenstein and the
 * Trustees of Princeton University.
 *
 * This file is part of the Nanoverse simulation framework
 * (patent pending).
 *
 * This program is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * This program is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU Affero General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Affero General
 * Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package compiler.pipeline.translate.symbol.processes.discrete.filter;

import compiler.pipeline.translate.symbol.MapSymbolTable;
import compiler.pipeline.translate.symbol.tables.MapSymbolTableTest;
import control.arguments.IntegerArgument;
import org.junit.*;
import processes.discrete.filter.CellStateFilter;

public class CellStateFilterInstSymbolTableTest extends MapSymbolTableTest {

    @Override
    protected MapSymbolTable getQuery() {
        return new CellStateFilterInstSymbolTable();
    }

    @Override
    protected Class getExpectedClass() {
        return CellStateFilter.class;
    }

    @Test
    public void state() throws Exception {
        verifyReturnSymbol("state", IntegerArgument.class);
    }
}