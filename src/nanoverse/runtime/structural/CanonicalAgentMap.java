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

package nanoverse.runtime.structural;

import nanoverse.runtime.agent.Agent;
import nanoverse.runtime.control.identifiers.Coordinate;

import java.util.*;

/**
 * Created by David B Borenstein on 4/11/14.
 */
public class CanonicalAgentMap extends HashMap<Coordinate, Agent> {
    public CanonicalAgentMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public CanonicalAgentMap(int initialCapacity) {
        super(initialCapacity);
    }

    public CanonicalAgentMap() {
        super();
    }

    public CanonicalAgentMap(Map<? extends Coordinate, ? extends Agent> m) {
        super(m);
    }

    @Override
    public Agent get(Object key) {
        Coordinate canonical = objToCanonicalCoord(key);

        return super.get(canonical);
    }

    @Override
    public boolean containsKey(Object key) {
        Coordinate canonical = objToCanonicalCoord(key);
        return super.containsKey(canonical);
    }

    @Override
    public Agent put(Coordinate key, Agent value) {
        Coordinate canonical = objToCanonicalCoord(key);
        return super.put(canonical, value);
    }

    private Coordinate objToCanonicalCoord(Object key) {
        if (!(key instanceof Coordinate)) {
            throw new ClassCastException();
        }

        Coordinate cKey = (Coordinate) key;
        Coordinate canonical = cKey.canonicalize();
        return canonical;
    }
}
