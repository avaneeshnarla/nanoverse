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
package nanoverse.compiler.pipeline.instantiate.factory.io.visual.color;

import nanoverse.compiler.pipeline.instantiate.factory.Factory;
import nanoverse.runtime.io.visual.color.*;

public class SurfaceColorModelFactory implements Factory<SurfaceColorModel> {

    private final SurfaceColorModelFactoryHelper helper;

    private ColorManager base;
    private float luminanceScale;
    private float saturationScale;

    public SurfaceColorModelFactory() {
        helper = new SurfaceColorModelFactoryHelper();
    }

    public SurfaceColorModelFactory(SurfaceColorModelFactoryHelper helper) {
        this.helper = helper;
    }

    public void setBase(ColorManager base) {
        this.base = base;
    }

    public void setLuminanceScale(float luminanceScale) {
        this.luminanceScale = luminanceScale;
    }

    public void setSaturationScale(float saturationScale) {
        this.saturationScale = saturationScale;
    }

    @Override
    public SurfaceColorModel build() {
        return helper.build(base, luminanceScale, saturationScale);
    }
}