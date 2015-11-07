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
package nanoverse.compiler.pipeline.instantiate.factory.layers.continuum;

import nanoverse.compiler.pipeline.instantiate.factory.Factory;
import nanoverse.runtime.geometry.Geometry;
import nanoverse.runtime.layers.continuum.ScheduledOperations;

public class ScheduledOperationsFactory implements Factory<ScheduledOperations> {

    private final ScheduledOperationsFactoryHelper helper;

    private Geometry geom;
    private boolean operators;

    public ScheduledOperationsFactory() {
        helper = new ScheduledOperationsFactoryHelper();
    }

    public ScheduledOperationsFactory(ScheduledOperationsFactoryHelper helper) {
        this.helper = helper;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    public void setOperators(boolean operators) {
        this.operators = operators;
    }

    @Override
    public ScheduledOperations build() {
        return helper.build(geom, operators);
    }
}