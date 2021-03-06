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

package nanoverse.compiler.pipeline.instantiate.loader.io.visual.color;

import nanoverse.compiler.pipeline.instantiate.loader.InterpolatorTest;
import nanoverse.runtime.control.arguments.DoubleArgument;
import org.junit.*;

import java.util.function.Supplier;

import static org.mockito.Mockito.*;

public class SurfaceColorModelInterpolatorTest extends InterpolatorTest {

    private SurfaceColorModelDefaults defaults;
    private SurfaceColorModelInterpolator query;

    @Before
    public void before() throws Exception {
        super.before();
        defaults = mock(SurfaceColorModelDefaults.class);
        query = new SurfaceColorModelInterpolator(load, defaults);
    }

    @Test
    public void luminance() throws Exception {
        Supplier<Double> trigger = () -> (double) query.luminance(node, random);
        verifyDouble("luminance", trigger);
    }

    @Test
    public void luminanceDefault() throws Exception {
        double expected = 0.5;
        when(defaults.luminance()).thenReturn(expected);
        Runnable trigger = () -> query.luminance(node, random);
        verifyDoubleDefault("luminance", expected, trigger);
    }

    @Test
    public void saturation() throws Exception {
        Supplier<Double> trigger = () -> (double) query.saturation(node, random);
        verifyDouble("saturation", trigger);
    }

    @Test
    public void saturationDefault() throws Exception {
        double expected = 0.5;
        when(defaults.saturation()).thenReturn(expected);
        Runnable trigger = () -> query.saturation(node, random);
        verifyDoubleDefault("saturation", expected, trigger);
    }
}