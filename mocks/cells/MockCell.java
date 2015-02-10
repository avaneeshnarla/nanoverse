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

package cells;

import control.halt.HaltCondition;
import control.identifiers.Coordinate;
import structural.utilities.EpsilonUtil;

/**
 * Mock cell class used for testing. We make it extend from BehaviorCell
 * for compatibility with BehaviorCell-only classes. (BehaviorCell is a
 * subclass of Cell which is capable of engaging in arbitrary behaviors,
 * which can then be used for agent-based modeling.)
 * <p>
 * Created by dbborens on 1/13/14.
 */
public class MockCell extends BehaviorCell {

    private int considerCount;
    private MockCell child;
    private int state = 1;
    private double health = 0.0;
    private double production;
    private String lastTriggeredBehaviorName;
    private Coordinate lastTriggeredCaller;
    private boolean divisible;
    private boolean died;
    private int triggerCount = 0;

    public MockCell() {
        super();
    }

    public MockCell(int state) {
        super();
        this.state = state;
    }

    @Override
    public int consider() {
        considerCount++;
        return considerCount;
    }

    @Override
    public void apply() {
        considerCount = 0;
    }

    @Override
    public MockCell divide() {
        return child;
    }

    @Override
    public MockCell clone(int state) {
        return child;
    }

    @Override
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setProduction(double production) {
        this.production = production;
    }

    public void setChild(MockCell child) {
        this.child = child;
    }

    @Override
    public boolean isDivisible() {
        return divisible;
    }

    @Override
    public void setDivisible(boolean divisible) {
        this.divisible = divisible;
    }

    @Override
    public double getProduction(String solute) {
        return production;
    }

    public Coordinate getLastTriggeredCaller() {
        return lastTriggeredCaller;
    }

    public String getLastTriggeredBehaviorName() {
        return lastTriggeredBehaviorName;
    }

    public int getTriggerCount() {
        return triggerCount;
    }

    @Override
    public void trigger(String behaviorName, Coordinate caller) throws HaltCondition {
        lastTriggeredBehaviorName = behaviorName;
        lastTriggeredCaller = caller;
        triggerCount++;
    }

    @Override
    public void die() {
        died = true;
    }

    public boolean died() {
        return died;
    }

    @Override
    public void adjustHealth(double delta) {

    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MockCell)) {
            return false;
        }

        MockCell other = (MockCell) obj;

        if (other.state != this.state) {
            return false;
        }

        if (!EpsilonUtil.epsilonEquals(other.health, health)) {
            return false;
        }

        return true;
    }
}
