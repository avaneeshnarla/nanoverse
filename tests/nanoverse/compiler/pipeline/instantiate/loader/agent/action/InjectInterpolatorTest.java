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

package nanoverse.compiler.pipeline.instantiate.loader.agent.action;

import nanoverse.compiler.pipeline.instantiate.loader.InterpolatorTest;
import nanoverse.runtime.control.arguments.DoubleArgument;
import org.junit.*;

import java.util.function.Supplier;

public class InjectInterpolatorTest extends InterpolatorTest {

    private InjectInterpolator query;

    @Before
    public void before() throws Exception {
        super.before();
        query = new InjectInterpolator(load);
    }

    @Test
    public void layer() throws Exception {
        Supplier<String> trigger = () -> query.layer(node);
        verifyString("layer", trigger);
    }

    @Test
    public void delta() throws Exception {
        Supplier<DoubleArgument> trigger = () -> query.delta(node, random);
        verifyDoubleArgument("delta", trigger);
    }
}