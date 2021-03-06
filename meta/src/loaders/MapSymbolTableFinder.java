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

package loaders;

import nanoverse.compiler.pipeline.translate.symbol.*;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Modifier;
import java.util.stream.Stream;

/**
 * Created by dbborens on 8/1/2015.
 */
public class MapSymbolTableFinder {

    public Stream<MapSymbolTable> findTables() {
        String pkgStr = "nanoverse.compiler.pipeline.translate.symbol";
        Reflections r = new Reflections(pkgStr, new SubTypesScanner(false));
        return r.getAllTypes()
                .stream()
                .map(className -> {
                    try {
                        return Class.forName(className);
                    } catch (ClassNotFoundException ex) {
                        throw new IllegalStateException(ex);
                    }
                })
                .filter(clazz -> !clazz.isInterface())
                .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
                .map(clazz -> {
                    try {
                        return clazz.newInstance();
                    } catch (InstantiationException ex) {
                        // isAssignableFrom isn't working for some reason,
                        // so I can't pre-filter for MapSymbolTables (which
                        // should all be instantiable).
                        return null;
                    } catch (IllegalAccessException ex) {
                        throw new IllegalStateException(ex);
                    }
                })
                .filter(obj -> obj instanceof MapSymbolTable)
                .map(obj -> (MapSymbolTable) obj);
    }
}
