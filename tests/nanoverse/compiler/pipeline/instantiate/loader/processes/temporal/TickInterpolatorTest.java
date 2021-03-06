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

package nanoverse.compiler.pipeline.instantiate.loader.processes.temporal;

import nanoverse.compiler.pipeline.instantiate.loader.InterpolatorTest;
import nanoverse.runtime.control.arguments.DoubleArgument;
import org.junit.*;

import java.util.function.Supplier;

import static org.mockito.Mockito.*;

public class TickInterpolatorTest extends InterpolatorTest {

    private TickDefaults defaults;
    private TickInterpolator query;

    @Before
    public void before() throws Exception {
        super.before();
        defaults = mock(TickDefaults.class);
        query = new TickInterpolator(load, null, defaults);
    }

    @Test
    public void dt() throws Exception {
        Supplier<DoubleArgument> trigger = () -> query.dt(node, random);
        verifyDoubleArgument("dt", trigger);
    }

    @Test
    public void dtDefault() throws Exception {
        DoubleArgument expected = mock(DoubleArgument.class);
        when(defaults.dt()).thenReturn(expected);

        Runnable trigger = () -> query.dt(node, random);
        verifyDoubleArgumentDefault("dt", expected, trigger);
    }
}