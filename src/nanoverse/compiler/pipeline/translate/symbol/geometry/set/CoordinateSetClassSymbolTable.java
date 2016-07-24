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

package nanoverse.compiler.pipeline.translate.symbol.geometry.set;

import nanoverse.compiler.pipeline.instantiate.loader.geometry.set.CustomCoordinateSetLoader;
import nanoverse.compiler.pipeline.translate.symbol.*;
import nanoverse.compiler.pipeline.translate.symbol.control.identifiers.CoordinateClassSymbolTable;
import nanoverse.runtime.control.identifiers.Coordinate;
import nanoverse.runtime.geometry.set.*;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 7/21/2015.
 */
public class CoordinateSetClassSymbolTable extends ClassSymbolTable<CoordinateSet> {

    @Override
    public String getDescription() {
        return "An unordered set of coordinates.";
    }

    @Override
    public HashMap<String, Supplier<InstantiableSymbolTable>> resolveSubclasses() {
        HashMap<String, Supplier<InstantiableSymbolTable>> ret = new HashMap<>();
        all(ret);
        disc(ret);
        custom(ret);
        hline(ret);
        // vline(ret);
        return ret;
    }

    public void hline(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = HLineCoordSetInstSymbolTable::new;
        ret.put("HLine", supplier);
    }

    public void custom(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = () -> {
            ClassSymbolTable cst = new CoordinateClassSymbolTable();
            return new ListSymbolTable<CustomSet>(cst, CustomCoordinateSetLoader::new);
        };
        ret.put("Custom", supplier);
    }

    public void disc(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = DiscCoordSetInstSymbolTable::new;
        ret.put("Disc", supplier);
    }

    public void all(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = CompleteCoordSetInstSymbolTable::new;
        ret.put("All", supplier);
    }

}
