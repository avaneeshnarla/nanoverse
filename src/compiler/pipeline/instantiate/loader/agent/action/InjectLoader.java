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

package compiler.pipeline.instantiate.loader.agent.action;

import agent.action.*;
import compiler.pipeline.instantiate.factory.agent.action.InjectFactory;
import compiler.pipeline.translate.nodes.MapObjectNode;
import control.GeneralParameters;
import control.arguments.DoubleArgument;
import layers.LayerManager;

/**
 * Created by dbborens on 8/3/2015.
 */
public class InjectLoader extends ActionLoader<InjectDescriptor> {

    private final InjectFactory factory;
    private final InjectInterpolator interpolator;

    public InjectLoader() {
        factory = new InjectFactory();
        interpolator = new InjectInterpolator();
    }

    public InjectLoader(InjectFactory factory,
                        InjectInterpolator interpolator) {

        this.factory = factory;
        this.interpolator = interpolator;
    }

    @Override
    public InjectDescriptor instantiate(MapObjectNode node, LayerManager lm, GeneralParameters p) {
        factory.setLayerManager(lm);

        String layerId = interpolator.layer(node);
        factory.setLayerId(layerId);

        DoubleArgument delta = interpolator.delta(node, p.getRandom());
        factory.setDeltaArg(delta);

        return factory.build();
    }
}