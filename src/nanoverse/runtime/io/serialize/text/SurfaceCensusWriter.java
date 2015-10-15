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

package nanoverse.runtime.io.serialize.text;

import nanoverse.runtime.control.GeneralParameters;
import nanoverse.runtime.control.halt.HaltCondition;
import nanoverse.runtime.control.identifiers.Coordinate;
import nanoverse.runtime.io.serialize.Serializer;
import nanoverse.runtime.layers.LayerManager;
import nanoverse.runtime.layers.cell.AgentLayer;
import nanoverse.runtime.processes.StepState;
import nanoverse.runtime.structural.annotations.FactoryTarget;

import java.io.BufferedWriter;
import java.util.*;

/**
 * Writes out the number of each "name" as a function of time.
 *
 * @author dbborens
 */
public class SurfaceCensusWriter extends Serializer {

    private static final String FILENAME = "surface_census.txt";

    // It is necessary to flush all data at the end of each iteration, rather
    // than after each flush event, because a name may appear for the first
    // time in the middle of the simulation, and we want an accurate column
    // for every observed name in the census table.

//    ArrayList<Integer> frames = new ArrayList<>();

    ArrayList<Integer> frames;
    // The keys to this map are FRAMES. The values are a mapping from NAME
    // number to count. If a name number does not appear, that means the
    // count was zero at that time.
    HashMap<Integer, HashMap<String, Integer>> histo;
    //    HashSet<Integer> observedNames = new HashSet<>();
    HashSet<String> observedNames;

    private BufferedWriter bw;

    @FactoryTarget
    public SurfaceCensusWriter(GeneralParameters p, LayerManager lm) {
        super(p, lm);
    }

    @Override
    public void init() {
        super.init();
        histo = new HashMap<>();
        frames = new ArrayList<>();
        observedNames = new HashSet<>();

        String filename = p.getInstancePath() + '/' + FILENAME;
        mkDir(p.getInstancePath(), true);
        bw = makeBufferedWriter(filename);
    }

    public void dispatchHalt(HaltCondition ex) {
        conclude();
        closed = true;
    }

    private void conclude() {
        // Sort the names numerically
        TreeSet<String> sortedNames = new TreeSet<>(observedNames);

        // Write out the header
        StringBuilder line = new StringBuilder();
        line.append("frame");

        for (String name : sortedNames) {
            line.append("\t");
            line.append(name);
        }

        line.append("\n");

        hAppend(bw, line);

        TreeSet<Integer> sortedFrames = new TreeSet<>(histo.keySet());
        for (Integer frame : sortedFrames) {
            HashMap<String, Integer> observations = histo.get(frame);

            line = new StringBuilder();
            line.append(frame);

            for (String name : sortedNames) {
                line.append("\t");

                if (observations.containsKey(name)) {
                    line.append(observations.get(name));
                } else {
                    line.append("0");
                }
            }

            line.append("\n");
            hAppend(bw, line);

        }
        hClose(bw);

    }

    public void close() {
        // Doesn't do anything.
    }

    @Override
    public void flush(StepState stepName) {
        AgentLayer layer = stepName.getRecordedAgentLayer();
        frames.add(stepName.getFrame());

        // Create a bucket for this frame.
        HashMap<String, Integer> observations = new HashMap<>();
        histo.put(stepName.getFrame(), observations);

        // Iterate over all occupied sites.
        for (Coordinate c : layer.getViewer().getOccupiedSites()) {
            // Is it at the front? If so, count it.
            if (isAtFront(c, layer)) {
                String name = layer.getViewer().getName(c);
                increment(observations, name);
            }
        }
    }

    private void increment(HashMap<String, Integer> observations, String name) {
        if (!observations.containsKey(name)) {
            observations.put(name, 0);
        }

        int value = observations.get(name);
        observations.put(name, value + 1);
        observedNames.add(name);
    }

    private boolean isAtFront(Coordinate c, AgentLayer layer) {
        String[] neighborNames = layer.getLookupManager().getNeighborNames(c, false);

        // If any neighbor is 0 (vacant), the point is at the front
        for (String neighborName : neighborNames) {
            if (neighborName == null) {
                return true;
            }
        }
        // If none of the neighbors are vacant, the point is interior
        return false;
    }
}
