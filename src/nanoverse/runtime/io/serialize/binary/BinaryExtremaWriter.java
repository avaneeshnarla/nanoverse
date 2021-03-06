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

package nanoverse.runtime.io.serialize.binary;

import nanoverse.runtime.control.identifiers.*;

import java.io.*;

/**
 * Created by dbborens on 6/1/2015.
 */
public class BinaryExtremaWriter {


    /**
     * Format the metadata and push to the writer.
     *
     * @param extrema
     */
    public void write(DataOutputStream writer, Extrema extrema) {
        try {
//            writer.writeInt(fieldName.length());
//            writer.writeChars(fieldName);
            writer.writeDouble(extrema.min());
            writeArgument(writer, extrema.argMin());
            writer.writeDouble(extrema.max());
            writeArgument(writer, extrema.argMax());
            writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void writeArgument(DataOutputStream writer, TemporalCoordinate c) throws IOException {
        writer.writeInt(c.flags());
        writer.writeInt(c.x());
        writer.writeInt(c.y());
        if (!c.hasFlag(Flags.PLANAR)) {
            writer.writeInt(c.z());
        }
        writer.writeDouble(c.t());
    }
}
