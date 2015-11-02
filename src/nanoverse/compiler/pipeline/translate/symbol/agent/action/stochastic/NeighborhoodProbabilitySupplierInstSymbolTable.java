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

package nanoverse.compiler.pipeline.translate.symbol.agent.action.stochastic;

import nanoverse.compiler.pipeline.instantiate.loader.Loader;
import nanoverse.compiler.pipeline.instantiate.loader.agent.action.stochastic.NeighborhoodProbabilitySupplierLoader;
import nanoverse.compiler.pipeline.translate.symbol.MapSymbolTable;
import nanoverse.compiler.pipeline.translate.symbol.MemberSymbol;
import nanoverse.compiler.pipeline.translate.symbol.ResolvingSymbolTable;
import nanoverse.compiler.pipeline.translate.symbol.primitive.doubles.DoubleClassSymbolTable;
import nanoverse.compiler.pipeline.translate.symbol.primitive.strings.StringClassSymbolTable;
import nanoverse.runtime.agent.action.stochastic.NeighborhoodProbabilitySupplierDescriptor;

import java.util.HashMap;

/**
 * Created by dbborens on 8/25/2015.
 */
public class NeighborhoodProbabilitySupplierInstSymbolTable
    extends MapSymbolTable<NeighborhoodProbabilitySupplierDescriptor> {

    @Override
    public String getDescription() {
        return "The weight of the action being chosen depends on the " +
            "number of occupied neighbors the actor has. The probability " +
            "will be reported as weight = offset + coefficient * neighbors.";
    }

    @Override
    public Loader getLoader() {
        return new NeighborhoodProbabilitySupplierLoader();
    }

    @Override
    protected HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> ret = super.resolveMembers();
        offset(ret);
        coefficient(ret);
        return ret;
    }

    private void coefficient(HashMap<String, MemberSymbol> ret) {
        ResolvingSymbolTable rst = new DoubleClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(rst, "The factor by which to " +
            "multiply the number of agents. See class description.");
        ret.put("coefficient", ms);
    }

    private void offset(HashMap<String, MemberSymbol> ret) {
        ResolvingSymbolTable rst = new DoubleClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(rst, "The weight given that there " +
            "are no occupied neighbors. See class description.");
        ret.put("offset", ms);
    }
}