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

package compiler.pipeline.translate.visitors;

import compiler.pipeline.interpret.nodes.ASTNode;
import compiler.pipeline.translate.helpers.TranslationCallback;
import compiler.pipeline.translate.nodes.*;
import compiler.pipeline.translate.symbol.DictionarySymbolTable;

/**
 * Created by dbborens on 9/22/2015.
 */
public class DictionaryChildLoader {

    private final DictionaryChildTranslator translator;

    public DictionaryChildLoader(TranslationCallback walker) {
        translator = new DictionaryChildTranslator(walker);
    }

    public DictionaryChildLoader(DictionaryChildTranslator translator) {
        this.translator = translator;
    }

    public void loadChild(ASTNode child, DictionarySymbolTable symbolTable, DictionaryObjectNode node) {
        // The child's identifier is a unique name for the element.
        String elementName = child.getIdentifier();

        ObjectNode childNode = translator.translateChild(child, symbolTable);
        node.loadMember(elementName, childNode);
    }

}
