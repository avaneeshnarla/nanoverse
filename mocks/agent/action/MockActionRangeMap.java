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

package agent.action;

import cells.BehaviorCell;

/**
 * Created by dbborens on 4/27/14.
 */
public class MockActionRangeMap extends ActionRangeMap {
    private boolean reportEquality;
    private Action nextTarget;
    private int timesCloned;

    public MockActionRangeMap() {
    }

    /**
     * Specifies whether this object should report itself as equal
     * to whatever it happens to be compared to.
     */
    public void setReportEquality(boolean reportEquality) {
        this.reportEquality = reportEquality;
        timesCloned = 0;
    }

    /**
     * Returns true if and only if reportEquality is set to
     * true.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return reportEquality;
    }

    /**
     * Specifies the next target to return, regardless
     * of what "x" is.
     *
     * @param nextTarget
     */
    public void setNextTarget(Action nextTarget) {
        this.nextTarget = nextTarget;
    }

    @Override
    public MockActionRangeMap clone(BehaviorCell child) {
        timesCloned++;

        MockActionRangeMap ret = new MockActionRangeMap();
        ret.setReportEquality(reportEquality);
        ret.setNextTarget(nextTarget);
        return ret;
    }

    @Override
    public Action selectTarget(double x) {
        return nextTarget;
    }

    public int getTimesCloned() {
        return timesCloned;
    }
}
