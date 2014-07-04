/**
 * Copyright (C) 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dashbuilder.displayer.impl;

import org.dashbuilder.displayer.BarChartBuilder;
import org.dashbuilder.displayer.BarChartDisplayer;
import org.dashbuilder.displayer.DataDisplayer;

public class BarChartBuilderImpl extends AbstractXAxisChartBuilder<BarChartBuilderImpl> implements BarChartBuilder<BarChartBuilderImpl> {

    protected DataDisplayer createDisplayer() {
        return new BarChartDisplayer();
    }

    public BarChartBuilderImpl set3d(boolean b) {
        BarChartDisplayer d = (BarChartDisplayer) dataDisplayer;
        d.set3d(b);
        return this;
    }

    public BarChartBuilderImpl horizontal() {
        BarChartDisplayer d = (BarChartDisplayer) dataDisplayer;
        d.setHorizontal(true);
        return this;
    }

    public BarChartBuilderImpl vertical() {
        BarChartDisplayer d = (BarChartDisplayer) dataDisplayer;
        d.setHorizontal(false);
        return this;
    }
}