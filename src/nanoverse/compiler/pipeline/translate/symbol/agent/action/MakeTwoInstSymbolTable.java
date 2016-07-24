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

import nanoverse.compiler.pipeline.instantiate.loader.Loader;
import nanoverse.compiler.pipeline.instantiate.loader.agent.action.MakeTwoLoader;
import nanoverse.compiler.pipeline.translate.symbol.*;
import nanoverse.compiler.pipeline.translate.symbol.agent.AgentDescriptorClassSymbolTable;
import nanoverse.compiler.pipeline.translate.symbol.primitive.integers.IntegerClassSymbolTable;
import nanoverse.runtime.agent.action.MakeTwoDescriptor;

import java.util.HashMap;

/**
 * Created by avaneesh on 7/22/2016.
 */
public class MakeTwoInstSymbolTable extends ActionInstSymbolTable<MakeTwoDescriptor> {
    @Override
    public String getDescription() {
        return "Causes the agent to expand toward the nearest vacant site. " +
                "If there are no adjacent vacancies, the nanoverse.runtime.agent will push a " +
                "line of agents toward the nearest vacancy, then place a " +
                "copy of itself in an adjacent site.";
    }

    @Override
    public HashMap<String, MemberSymbol> resolveMembers() {
        HashMap<String, MemberSymbol> ret = super.resolveMembers();
        targetHighlight(ret);
        selfHighlight(ret);
        agentDescriptor(ret);
        return ret;
    }

    private void targetHighlight(HashMap<String, MemberSymbol> ret) {
        ResolvingSymbolTable rst = new IntegerClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(rst, "Highlight channel on which " +
                "to record the target location. If left null, no " +
                "highlight will be recorded.");
        ret.put("targetHighlight", ms);
    }

    private void selfHighlight(HashMap<String, MemberSymbol> ret) {
        ResolvingSymbolTable rst = new IntegerClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(rst, "Highlight channel on which " +
                "to record the expanding nanoverse.runtime.agent. If left null, no " +
                "highlight will be recorded.");
        ret.put("selfHighlight", ms);
    }

    private void agentDescriptor(HashMap<String, MemberSymbol> ret) {
        ResolvingSymbolTable rst = new AgentDescriptorClassSymbolTable();
        MemberSymbol ms = new MemberSymbol(rst, "A template for the agents to be created by this process.");
        ret.put("description", ms);
    }

    @Override
    public Loader getLoader() {
        return new MakeTwoLoader();
    }
}
