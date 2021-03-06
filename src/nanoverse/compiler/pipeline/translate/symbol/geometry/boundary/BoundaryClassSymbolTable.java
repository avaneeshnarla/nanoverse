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

package nanoverse.compiler.pipeline.translate.symbol.geometry.boundary;

import nanoverse.compiler.pipeline.translate.symbol.*;
import nanoverse.runtime.geometry.boundaries.Boundary;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by dbborens on 7/28/2015.
 */
public class BoundaryClassSymbolTable extends ClassSymbolTable<Boundary> {
    @Override
    public String getDescription() {
        return "The Boundary object specifies what occurs around the " +
            "perimeter of the simulation arena. Each layer can have a different boundary.";
    }

    @Override
    public HashMap<String, Supplier<InstantiableSymbolTable>> resolveSubclasses() {
        HashMap<String, Supplier<InstantiableSymbolTable>> ret = new HashMap<>();
        arena(ret);
        planeRingHard(ret);
        planeRingReflecting(ret);
        absorbing(ret);
        periodic(ret);
        halt(ret);
        tetris(ret);
        tetrisReflecting(ret);
        return ret;
    }

    public void arena(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = ArenaBoundaryInstSymbolTable::new;
        ret.put("Arena", supplier);
    }

    public void planeRingHard(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = PlaneRingHardBoundaryInstSymbolTable::new;
        ret.put("PlaneRingHard", supplier);
    }

    public void planeRingReflecting(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = PlaneRingReflectingInstSymbolTable::new;
        ret.put("PlaneRingReflecting", supplier);
    }

    public void absorbing(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = AbsorbingBoundaryInstSymbolTable::new;
        ret.put("Absorbing", supplier);
    }

    public void periodic(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = PeriodicBoundaryInstSymbolTable::new;
        ret.put("Periodic", supplier);
    }

    public void halt(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = HaltBoundaryInstSymbolTable::new;
        ret.put("Halt", supplier);
    }

    public void tetris(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = TetrisBoundaryInstSymbolTable::new;
        ret.put("Tetris", supplier);
    }

    public void tetrisReflecting(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = TetrisReflectingBoundaryInstSymbolTable::new;
        ret.put("TetrisReflecting", supplier);
    }
}
