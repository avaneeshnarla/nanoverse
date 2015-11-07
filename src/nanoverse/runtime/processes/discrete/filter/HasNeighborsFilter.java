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

package nanoverse.runtime.processes.discrete.filter;

import nanoverse.runtime.control.identifiers.Coordinate;
import nanoverse.runtime.layers.cell.AgentLayer;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dbborens on 10/21/2015.
 */
public class HasNeighborsFilter extends Filter {

    private final AgentLayer layer;

    public HasNeighborsFilter(AgentLayer layer) {
        this.layer = layer;
    }

    @Override
    public List<Coordinate> apply(List<Coordinate> toFilter) {
        return toFilter.stream()
            .filter(coord -> layer
                .getLookupManager()
                .getNeighborNames(coord, true)
                .count() > 0)
            .collect(Collectors.toList());
    }
}
