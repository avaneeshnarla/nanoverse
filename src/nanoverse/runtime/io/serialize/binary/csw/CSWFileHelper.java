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

package nanoverse.runtime.io.serialize.binary.csw;

import nanoverse.runtime.control.GeneralParameters;
import nanoverse.runtime.processes.StepState;
import nanoverse.runtime.structural.utilities.FileConventions;

import java.io.*;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 * Created by dbborens on 5/22/2015.
 */
public class CSWFileHelper {

    private final HashMap<String, DataOutputStream> streamMap;
    private final CSWLayerProcessor layerProcessor;
    private final GeneralParameters p;

    public CSWFileHelper(GeneralParameters p, Stream<String> idStream) {
        streamMap = new HashMap<>();
        this.p = p;
        idStream.forEach(id -> streamMap.put(id, makeOutputStream(id)));
        layerProcessor = new CSWLayerProcessor(streamMap);
    }

    private DataOutputStream makeOutputStream(String id) {
        String filename = FileConventions.makeContinuumStateFilename(id);
        String filepath = p.getInstancePath() + '/' + filename;
        try {
            File stateFile = new File(filepath);
            FileOutputStream fileOutputStream = new FileOutputStream(stateFile);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            DataOutputStream dataStream = new DataOutputStream(bufferedOutputStream);
            return dataStream;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CSWFileHelper(GeneralParameters p, HashMap<String, DataOutputStream> streamMap, CSWLayerProcessor layerProcessor) {
        this.p = p;
        this.streamMap = streamMap;
        this.layerProcessor = layerProcessor;
    }

    public void push(StepState stepState) {
        streamMap.keySet().forEach(id -> {
            Stream<Double> stateStream = stepState.getRecordedContinuumValues(id);
            double time = stepState.getTime();
            int frame = stepState.getFrame();
            layerProcessor.processLayer(id, stateStream, time, frame);
        });
    }


    public void close() {
        layerProcessor.conclude();
        streamMap.keySet().forEach(id -> {
            DataOutputStream stream = streamMap.get(id);
            try {
                stream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
