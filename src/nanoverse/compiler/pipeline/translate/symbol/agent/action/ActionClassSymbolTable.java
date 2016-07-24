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

package nanoverse.compiler.pipeline.translate.symbol.agent.action;

import nanoverse.compiler.pipeline.instantiate.loader.agent.action.CompoundActionLoader;
import nanoverse.compiler.pipeline.translate.symbol.*;
import nanoverse.runtime.agent.action.ActionDescriptor;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * Created by avaneesh on 7/22/2016.
 */
public class ActionClassSymbolTable extends ClassSymbolTable<ActionDescriptor> {

    @Override
    public String getDescription() {
        return "Actions are local events defined with respect to a " +
            "particular nanoverse.runtime.agent.";
    }

    @Override
    public HashMap<String, Supplier<InstantiableSymbolTable>> resolveSubclasses() {
        HashMap<String, Supplier<InstantiableSymbolTable>> ret = new HashMap<>();
        mock(ret);
        die(ret);
        trigger(ret);
        cloneTo(ret);
        compoundAction(ret);
        expand(ret);
        make(ret);
        maketwo(ret);
        expandTo(ret);
        expandRandom(ret);
        expandWeighted(ret);
        stochasticChoice(ret);
        swap(ret);
        inject(ret);
        thresholdDo(ret);
        nullAction(ret);
        return ret;
    }

    private void compoundAction(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {

        Supplier<InstantiableSymbolTable> supplier = () ->
            new ListSymbolTable(this, CompoundActionLoader::new);

        ret.put("CompoundAction", supplier);

    }

    private void nullAction(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = NullActionInstSymbolTable::new;
        ret.put("Null", supplier);
    }

    private void thresholdDo(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = ThresholdDoInstSymbolTable::new;
        ret.put("ThresholdDo", supplier);
    }

    private void inject(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = InjectInstSymbolTable::new;
        ret.put("Inject", supplier);
    }

    private void swap(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = SwapInstSymbolTable::new;
        ret.put("Swap", supplier);
    }

    private void stochasticChoice(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = StochasticChoiceInstSymbolTable::new;
        ret.put("StochasticChoice", supplier);
    }

    private void expandWeighted(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = ExpandWeightedInstSymbolTable::new;
        ret.put("ExpandWeighted", supplier);
    }

    private void expandRandom(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = ExpandRandomInstSymbolTable::new;
        ret.put("ExpandRandom", supplier);
    }

    private void expandTo(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = ExpandToInstSymbolTable::new;
        ret.put("ExpandTo", supplier);
    }

    private void expand(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = ExpandInstSymbolTable::new;
        ret.put("Expand", supplier);
    }

    private void make(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = MakeInstSymbolTable::new;
        ret.put("Make", supplier);
    }

    private void maketwo(HashMap<String, Supplier<InstantiableSymbolTable>>
                                ret) {
        Supplier<InstantiableSymbolTable> supplier =
                MakeTwoInstSymbolTable::new;
        ret.put("MakeTwo", supplier);
    }

    private void cloneTo(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = CloneToInstSymbolTable::new;
        ret.put("CloneTo", supplier);
    }

    private void trigger(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = TriggerActionInstSymbolTable::new;
        ret.put("Trigger", supplier);
    }

    private void die(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = DieInstSymbolTable::new;
        ret.put("Die", supplier);
    }

    private void mock(HashMap<String, Supplier<InstantiableSymbolTable>> ret) {
        Supplier<InstantiableSymbolTable> supplier = MockActionInstSymbolTable::new;
        ret.put("Mock", supplier);
    }

}
