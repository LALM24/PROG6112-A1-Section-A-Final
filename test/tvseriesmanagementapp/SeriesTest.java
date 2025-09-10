/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package tvseriesmanagementapp;



import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SeriesTest {
    
    private Series series;
    private SeriesModel testSeries1;
    private SeriesModel testSeries2;
    
    @Before
    public void setUp() {
        series = new Series();
        testSeries1 = new SeriesModel("101", "Extreme Sports", "12", "10");
        testSeries2 = new SeriesModel("102", "Bargain Hunters", "10", "10");
        
        // Add test data
        series.addSeries(testSeries1);
        series.addSeries(testSeries2);
    }
    
    @Test
    public void TestSearchSeries() {
        // Test that the correct series data is returned when searching with a valid series ID
        SeriesModel foundSeries = series.findSeriesById("101");
        
        assertNotNull("Series should be found", foundSeries);
        assertEquals("Series ID should match", "101", foundSeries.SeriesId);
        assertEquals("Series name should match", "Extreme Sports", foundSeries.SeriesName);
        assertEquals("Series age should match", "12", foundSeries.SeriesAge);
        assertEquals("Number of episodes should match", "10", foundSeries.SeriesNumberOfEpisodes);
    }
    
    @Test
    public void TestSearchSeries_SeriesNotFound() {
        // Test that no series is found when searching with an incorrect series ID
        SeriesModel foundSeries = series.findSeriesById("999");
        
        assertNull("Series should not be found for invalid ID", foundSeries);
    }
    
    @Test
    public void TestUpdateSeries() {
        // Test that a series is successfully updated
        boolean updateResult = series.updateSeriesById("101", "Extreme Sports 2025", "10", "12");
        
        assertTrue("Series should be successfully updated", updateResult);
        
        // Verify the series was actually updated
        SeriesModel updatedSeries = series.findSeriesById("101");
        assertNotNull("Updated series should still exist", updatedSeries);
        assertEquals("Series name should be updated", "Extreme Sports 2025", updatedSeries.SeriesName);
        assertEquals("Series age should be updated", "10", updatedSeries.SeriesAge);
        assertEquals("Number of episodes should be updated", "12", updatedSeries.SeriesNumberOfEpisodes);
    }
    
    @Test
    public void TestDeleteSeries() {
        // Test that a series is successfully deleted
        int initialCount = series.getSeriesCount();
        boolean deleteResult = series.deleteSeriesById("101");
        
        assertTrue("Series should be successfully deleted", deleteResult);
        assertEquals("Series count should decrease by 1", initialCount - 1, series.getSeriesCount());
        
        // Verify the series no longer exists
        SeriesModel deletedSeries = series.findSeriesById("101");
        assertNull("Deleted series should not be found", deletedSeries);
    }
    
    @Test
    public void TestDeleteSeries_SeriesNotFound() {
        // Test that no series is deleted when trying to delete with an incorrect series ID
        int initialCount = series.getSeriesCount();
        boolean deleteResult = series.deleteSeriesById("999");
        
        assertFalse("Series should not be deleted for invalid ID", deleteResult);
        assertEquals("Series count should remain the same", initialCount, series.getSeriesCount());
    }
    
    @Test
    public void TestSeriesAgeRestriction_AgeValid() {
        // Test that a valid series age restriction is accepted
        assertTrue("Age 2 should be valid", series.isValidAge(2));
        assertTrue("Age 10 should be valid", series.isValidAge(10));
        assertTrue("Age 18 should be valid", series.isValidAge(18));
        assertTrue("Age 12 should be valid", series.isValidAge(12));
    }
    
    @Test
    public void testSeriesAgeRestrictionInvalid() {
        // Test that invalid series age restrictions are rejected
        assertFalse("Age 1 should be invalid", series.isValidAge(1));
        assertFalse("Age 19 should be invalid", series.isValidAge(19));
        assertFalse("Age 0 should be invalid", series.isValidAge(0));
        assertFalse("Age 25 should be invalid", series.isValidAge(25));
        assertFalse("Negative age should be invalid", series.isValidAge(-5));
    }
}
