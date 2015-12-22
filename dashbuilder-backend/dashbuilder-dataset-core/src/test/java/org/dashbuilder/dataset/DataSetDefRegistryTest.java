/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
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
package org.dashbuilder.dataset;

import org.dashbuilder.DataSetCore;
import org.dashbuilder.dataset.def.DataSetDef;
import org.dashbuilder.dataset.def.DataSetDefFactory;
import org.dashbuilder.dataset.def.DataSetDefRegistry;
import org.dashbuilder.dataset.def.DataSetDefRegistryListener;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DataSetDefRegistryTest {

    DataSetDefRegistry dataSetDefRegistry;
    DataSetManager dataSetManager;

    DataSetDef dataSetDef = DataSetDefFactory.newBeanDataSetDef()
            .uuid("sequence")
            .generatorClass("MyClass")
            .generatorParam("from", "1")
            .generatorParam("to", "100")
            .buildDef();

    @Mock
    DataSetDefRegistryListener registryListener;

    @Before
    public void setUp() {
        dataSetDefRegistry = DataSetCore.get().getDataSetDefRegistry();
        dataSetManager = DataSetCore.get().getDataSetManager();

        assertNotNull(dataSetDefRegistry);
        assertNotNull(dataSetManager);
        dataSetDefRegistry.addListener(registryListener);
    }

    @Test
    public void testRegisterLifecycle() {
        dataSetDefRegistry.registerDataSetDef(dataSetDef);
        verify(registryListener).onDataSetDefRegistered(dataSetDef);

        DataSetDef modifiedDef = DataSetDefFactory
                .newBeanDataSetDef()
                .uuid("sequence")
                .buildDef();

        dataSetDefRegistry.registerDataSetDef(modifiedDef);
        verify(registryListener).onDataSetDefStale(dataSetDef);
        verify(registryListener).onDataSetDefModified(dataSetDef, modifiedDef);

        dataSetDefRegistry.removeDataSetDef("sequence");
        verify(registryListener).onDataSetDefRemoved(modifiedDef);
    }
}