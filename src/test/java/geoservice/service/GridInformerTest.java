package geoservice.service;

import geoservice.model.Cell;
import geoservice.response.ErrorResponse;
import geoservice.response.UserCountResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.google.common.collect.Lists.newArrayList;
import static geoservice.utils.StructureBuilder.createCellsMap;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GridInformerTest {

    @Mock
    private Cell cell;

    private GridInformer gridInformer;

    @Before
    public void setUp() {
        when(cell.getLat()).thenReturn(20);
        when(cell.getLon()).thenReturn(30);
        when(cell.getUserCount()).thenReturn(237);

        gridInformer = new GridInformer(createCellsMap(newArrayList(cell)));
    }

    @Test
    public void shouldGetUserCountFromCell() {
        UserCountResponse response = (UserCountResponse) gridInformer.getCellUserCount(20.3, 30.8);

        assertEquals(237, response.getUserCount());
    }

    @Test
    public void shouldRespondWithErrorIfCellNotFound() {
        ErrorResponse response = (ErrorResponse) gridInformer.getCellUserCount(10.3, 130.8);

        assertThat(response.getError(), is("No cells were found for coordinates: (10.3,130.8)"));
    }
}