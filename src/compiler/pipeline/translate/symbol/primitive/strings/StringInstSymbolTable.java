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

package compiler.pipeline.translate.symbol.primitive.strings;

import compiler.pipeline.instantiate.loader.Loader;
import compiler.pipeline.instantiate.loader.primitive.strings.StringArgumentLoader;
import compiler.pipeline.translate.nodes.*;
import compiler.pipeline.translate.symbol.primitive.ConstantPrimitiveSymbolTable;
import control.arguments.StringArgument;

/**
 * Created by dbborens on 7/22/2015.
 */
public class StringInstSymbolTable
        extends ConstantPrimitiveSymbolTable<StringArgument, String> {
    @Override
    public String getDescription() {
        return "A string literal.";
    }

    @Override
    public String getValue(String valueStr) {
        return valueStr;
    }

    @Override
    public Loader getLoader() {
        return new StringArgumentLoader();
    }

}