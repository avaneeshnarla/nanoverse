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
package nanoverse.compiler.pipeline.instantiate.factory.processes.discrete;

import nanoverse.compiler.pipeline.instantiate.factory.Factory;
import nanoverse.runtime.control.arguments.*;
import nanoverse.runtime.processes.BaseProcessArguments;
import nanoverse.runtime.processes.discrete.*;
import nanoverse.runtime.processes.discrete.cluster.SeparationStrategyManager;

public class ScatterClustersFactory implements Factory<ScatterClusters> {

    private final ScatterClustersFactoryHelper helper;

    private BaseProcessArguments arguments;
    private AgentProcessArguments cpArguments;
    private IntegerArgument neighborCount;
    private AgentDescriptor cellDescriptor;
    private SeparationStrategyManager clustersHelper;

    public ScatterClustersFactory() {
        helper = new ScatterClustersFactoryHelper();
    }

    public ScatterClustersFactory(ScatterClustersFactoryHelper helper) {
        this.helper = helper;
    }

    public void setArguments(BaseProcessArguments arguments) {
        this.arguments = arguments;
    }

    public void setCpArguments(AgentProcessArguments cpArguments) {
        this.cpArguments = cpArguments;
    }

    public void setNeighborCount(IntegerArgument neighborCount) {
        this.neighborCount = neighborCount;
    }

    public void setAgentDescriptor(AgentDescriptor cellDescriptor) {
        this.cellDescriptor = cellDescriptor;
    }

    public void setClustersHelper(SeparationStrategyManager clustersHelper) {
        this.clustersHelper = clustersHelper;
    }

    @Override
    public ScatterClusters build() {
        return helper.build(arguments, cpArguments, neighborCount, cellDescriptor, clustersHelper);
    }
}