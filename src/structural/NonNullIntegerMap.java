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

package structural;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David B Borenstein on 4/11/14.
 */
public class NonNullIntegerMap extends HashMap<Integer, Integer> {
    public NonNullIntegerMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public NonNullIntegerMap(int initialCapacity) {
        super(initialCapacity);
    }

    public NonNullIntegerMap() {
        super();
    }

    public NonNullIntegerMap(Map<? extends Integer, ? extends Integer> m) {
        super(m);
    }

    @Override
    public Integer get(Object key) {
        if (!(key instanceof Integer)) {
            throw new IllegalStateException("Received unexpected key class.");
        }

        if (!containsKey(key)) {
            return 0;
        }

        return super.get(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NonNullIntegerMap other = this;

        if (other.size() != this.size()) {
            return false;
        }

        for (Integer key : keySet()) {
            if (!other.containsKey(key)) {
                return false;
            }

            if (get(key) != other.get(key)) {
                return false;
            }
        }

        return true;
    }
}
