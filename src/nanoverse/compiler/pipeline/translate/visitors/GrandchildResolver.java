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

package nanoverse.compiler.pipeline.translate.visitors;

import nanoverse.compiler.error.SyntaxError;
import nanoverse.compiler.pipeline.interpret.nodes.ASTNode;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dbborens on 9/22/2015.
 */
public class GrandchildResolver {

    public ASTNode getChildValue(ASTNode child) {
        List<ASTNode> grandchildren = child
            .getChildren()
            .collect(Collectors.toList());

        if (grandchildren.size() != 1) {
            throw new SyntaxError("Syntax error: unexpected dictionary value on element " + child.getIdentifier() + ". Detail:\n\n" + child.toString());
        }
        return grandchildren.get(0);
    }
}
