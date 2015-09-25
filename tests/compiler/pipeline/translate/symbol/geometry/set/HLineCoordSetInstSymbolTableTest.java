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

package compiler.pipeline.translate.symbol.geometry.set;

import compiler.pipeline.translate.symbol.MapSymbolTable;
import compiler.pipeline.translate.symbol.tables.*;
import control.arguments.IntegerArgument;
import control.identifiers.Coordinate;
import geometry.set.HorizontalLineSet;
import org.junit.*;

public class HLineCoordSetInstSymbolTableTest extends MapSymbolTableTest {

    @Override
    protected MapSymbolTable getQuery() {
        return new HLineCoordSetInstSymbolTable();
    }

    @Override
    protected Class getExpectedClass() {
        return HorizontalLineSet.class;
    }

    @Test
    public void origin() throws Exception {
        verifyReturnSymbol("origin", Coordinate.class);
    }

    @Test
    public void length() throws Exception {
        verifyReturnSymbol("length", IntegerArgument.class);
    }
}