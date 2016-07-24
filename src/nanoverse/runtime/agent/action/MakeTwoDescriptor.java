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

package nanoverse.runtime.agent.action;

import nanoverse.runtime.agent.Agent;
import nanoverse.runtime.control.arguments.*;
import nanoverse.runtime.layers.LayerManager;
import nanoverse.runtime.structural.annotations.FactoryTarget;

import java.util.Random;
import java.util.function.Function;

/**
 * Created by avaneesh on 7/22/2016.
 */
public class MakeTwoDescriptor extends ActionDescriptor<MakeTwo> {

    private final Function<Agent, MakeTwo> constructor;

    @FactoryTarget(displayName = "MakeTwo")
    public MakeTwoDescriptor(LayerManager layerManager,
                          AgentDescriptor descriptor,
                          IntegerArgument selfChannel,
                          IntegerArgument targetChannel,
                          Random random) {

        constructor = cell -> new MakeTwo(cell, layerManager, descriptor,
                selfChannel, targetChannel, random);
    }

    @Override
    protected Function<Agent, MakeTwo> resolveConstructor() {
        return constructor;
    }
}