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

package nanoverse.runtime.agent;

import nanoverse.runtime.agent.action.*;
import nanoverse.runtime.control.identifiers.Coordinate;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by David B Borenstein on 1/21/14.
 */
public class MockBehavior extends CompoundAction {

    private int timesRun;
    private HashMap<Coordinate, Integer> callerCounts;

    private Coordinate lastCaller = null;


    public MockBehavior() {
        super(null, null, null);
        timesRun = 0;
        callerCounts = new HashMap<>();
    }

    public MockBehavior(Agent callback) {
        super(callback, null, null);
        timesRun = 0;
        callerCounts = new HashMap<>();
    }

    public Coordinate getLastCaller() {
        return lastCaller;
    }

    @Override
    public void run(Coordinate caller) {
        timesRun++;

        lastCaller = caller;

        if (caller != null) {
            increment(caller);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MockBehavior;
    }

    public Stream<Action> getActionSequence() {
        return Stream.empty();
    }

    @Override
    public MockBehavior copy(Agent child) {
        return new MockBehavior(child);
    }

    private void increment(Coordinate caller) {
        if (!callerCounts.containsKey(caller)) {
            callerCounts.put(caller, 0);
        }

        int count = callerCounts.get(caller);
        callerCounts.put(caller, count + 1);
    }

    public int getTimesRun() {
        return timesRun;
    }

    public int timesCaller(Coordinate caller) {
        if (!callerCounts.containsKey(caller))
            return 0;

        return callerCounts.get(caller);
    }

}
