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

package nanoverse.compiler.pipeline.instantiate.loader.control;

import nanoverse.compiler.pipeline.instantiate.factory.control.ProcessManagerFactory;
import nanoverse.compiler.pipeline.instantiate.loader.Loader;
import nanoverse.compiler.pipeline.translate.nodes.ListObjectNode;
import nanoverse.runtime.control.*;
import nanoverse.runtime.layers.LayerManager;
import nanoverse.runtime.processes.NanoverseProcess;

import java.util.stream.Stream;

/**
 * Created by dbborens on 8/13/15.
 */
public class ProcessManagerLoader extends Loader<ProcessManager> {

    private final ProcessManagerFactory factory;
    private final ProcessManagerInterpolator interpolator;

    public ProcessManagerLoader() {
        factory = new ProcessManagerFactory();
        interpolator = new ProcessManagerInterpolator();
    }

    public ProcessManagerLoader(ProcessManagerFactory factory,
                                ProcessManagerInterpolator interpolator) {

        this.factory = factory;
        this.interpolator = interpolator;
    }

    public ProcessManager instantiate(LayerManager lm, GeneralParameters p) {
        return instantiate(null, lm, p);
    }

    public ProcessManager instantiate(ListObjectNode node, LayerManager lm, GeneralParameters p) {
        factory.setLayerManager(lm);

        Stream<NanoverseProcess> processes = interpolator.processes(node, lm, p);
        factory.setProcesses(processes);

        return factory.build();
    }
}
