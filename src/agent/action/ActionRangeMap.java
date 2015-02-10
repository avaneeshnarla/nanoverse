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

package agent.action;

import cells.BehaviorCell;
import structural.RangeMap;

/**
 * Created by dbborens on 4/27/14.
 */
public class ActionRangeMap extends RangeMap<Action> {
    public ActionRangeMap(int initialSize) {
        super(initialSize);
    }

    public ActionRangeMap() {
        super();
    }

    @Override
    public RangeMap<Action> clone() {
        throw new UnsupportedOperationException("Clone using the replicate(BehaviorCell child) method.");
    }

    public ActionRangeMap clone(BehaviorCell child) {
        int n = keys.size();
        ActionRangeMap cloned = new ActionRangeMap(n);

        for (int i = 1; i < floors.size(); i++) {
            Action key = keys.get(i - 1);
            Action clonedKey = key.clone(child);
            Double weight = floors.get(i) - floors.get(i - 1);

            cloned.add(clonedKey, weight);
        }

        return cloned;
    }
}
