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
package compiler.pipeline.instantiate.factory.agent.action;

import control.arguments.Argument;
import control.arguments.IntegerArgument;
import agent.targets.TargetDescriptor;
import java.util.Random;
import layers.LayerManager;
import agent.action.ExpandToDescriptor;
import compiler.pipeline.instantiate.factory.Factory;

public class ExpandToFactory implements Factory<ExpandToDescriptor> {

    private final ExpandToFactoryHelper helper;

    private LayerManager layerManager;
    private TargetDescriptor ruleDescriptor;
    private IntegerArgument selfChannel;
    private IntegerArgument targetChannel;
    private Random random;

    public ExpandToFactory() {
        helper = new ExpandToFactoryHelper();
    }

    public ExpandToFactory(ExpandToFactoryHelper helper) {
        this.helper = helper;
    }

    public void setLayerManager(LayerManager layerManager) {
        this.layerManager = layerManager;
    }

    public void setRuleDescriptor(TargetDescriptor ruleDescriptor) {
        this.ruleDescriptor = ruleDescriptor;
    }

    public void setSelfChannel(IntegerArgument selfChannel) {
        this.selfChannel = selfChannel;
    }

    public void setTargetChannel(IntegerArgument targetChannel) {
        this.targetChannel = targetChannel;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    @Override
    public ExpandToDescriptor build() {
        return helper.build(layerManager, ruleDescriptor, selfChannel, targetChannel, random);
    }
}