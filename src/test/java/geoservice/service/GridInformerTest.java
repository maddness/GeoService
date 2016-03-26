package geoservice.service;

import geoservice.model.Cell;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static geoservice.utils.StructureBuilder.createCellsMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Alexey Ostrikov on 26/03/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class GridInformerTest {

    @Mock
    private Cell cell;

    private GridInformer gridInformer;

    @Before
    public void setUp() throws Exception {
        when(cell.getLat()).thenReturn(20);
        when(cell.getLon()).thenReturn(30);
        when(cell.getUserCount()).thenReturn(237);

        gridInformer = new GridInformer(createCellsMap(newArrayList(cell)));
    }

    @Ignore
    @Test
    public void shouldGetUserCountFromCell() {
        assertEquals(237, gridInformer.getCellUserCount(20.3, 30.8));
    }

    @Ignore
    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfCellNotFound() {
        assertEquals(237, gridInformer.getCellUserCount(10.3, 130.8));
    }
}