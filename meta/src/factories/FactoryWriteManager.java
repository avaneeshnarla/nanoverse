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

package factories;

import java.lang.reflect.Constructor;

/**
 * Created by dbborens on 8/7/15.
 */
public class FactoryWriteManager {

    private final FactoryWriter fWriter;
    private final FactoryHelperWriter fhWriter;

    public FactoryWriteManager(String basepath) {
        fWriter = new FactoryWriter(basepath);
        fhWriter = new FactoryHelperWriter(basepath);
    }

    public void write(Constructor c) {
        fWriter.write(c);
        fhWriter.write(c);
    }
}
