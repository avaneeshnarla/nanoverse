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

package control.halt;

public class HaltCondition extends Exception {

    private double gillespie;

    public HaltCondition() {
    }

//    @Deprecated
//    public HaltCondition(double gillespie) {
//        this.gillespie = gillespie;
//    }

    public double getGillespie() {
        return gillespie;
    }

    public void setGillespie(double gillespie) {
        this.gillespie = gillespie;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
