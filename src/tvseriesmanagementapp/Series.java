/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tvseriesmanagementapp;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lucam
 */

public class Series {
    private ArrayList<SeriesModel> seriesList;
    private Scanner scanner;
    
    // Constructor
    public Series() {
        seriesList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    // Main menu display
    public void displayMainMenu() {
        System.out.println("\nLATEST SERIES – 2025");
        System.out.println("************************************************");
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1)\tCapture a new series.");
        System.out.println("(2)\tSearch for a series.");
        System.out.println("(3)\tUpdate series age restriction.");
        System.out.println("(4)\tDelete a series.");
        System.out.println("(5)\tPrint series report – 2025.");
        System.out.println("(6)\tExit Application.");
    }
    
    // Method to capture a new series
    public void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("*************************");
        
        System.out.print("Enter the series id: ");
        String seriesId = scanner.nextLine();
        
        System.out.print("Enter the series name: ");
        String seriesName = scanner.nextLine();
        
        // Validate age restriction
        String seriesAge = getValidAgeRestriction();
        
        System.out.print("Enter the number of episodes for " + seriesName + ": ");
        String numberOfEpisodes = scanner.nextLine();
        
        // Create new series and add to list
        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, seriesAge, numberOfEpisodes);
        seriesList.add(newSeries);
        
        System.out.println("Series Processed successfully!!!");
    }
    
    // Method to validate age restriction
    private String getValidAgeRestriction() {
        String ageInput;
        int age;
        
        while (true) {
            System.out.print("Enter the series age restriction: ");
            ageInput = scanner.nextLine();
            
            try {
                age = Integer.parseInt(ageInput);
                if (isValidAge(age)) {
                    return ageInput;
                } else {
                    System.out.println("You have entered an incorrect series age!!!");
                    //System.out.println("Please re-enter the series age >> ");
                    
                }
            } catch (NumberFormatException e) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.println("Please re-enter the series age >> ");
            }
        }
    }
    
    // Method to validate age restriction (for unit testing)
    public boolean isValidAge(int age) {
        return age >= 2 && age <= 18;
    }
    
    // Method to search for a series
    public void SearchSeries() {
        System.out.print("Enter the series id to search: ");
        String searchId = scanner.nextLine();
        
        SeriesModel foundSeries = findSeriesById(searchId);
        
        if (foundSeries != null) {
            displaySeriesDetails(foundSeries);
        } else {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Series with series Id: " + searchId + " was not found!");
            System.out.println("----------------------------------------------------------------------");
        }
    }
    
    // Helper method to find series by ID (for unit testing)
    public SeriesModel findSeriesById(String seriesId) {
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(seriesId)) {
                return series;
            }
        }
        return null;
    }
    
    // Method to display series details
    private void displaySeriesDetails(SeriesModel series) {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Enter the series id: " + series.SeriesId);
        System.out.println("Enter the series name: " + series.SeriesName);
        System.out.println("Enter the series age restriction: " + series.SeriesAge);
        System.out.println("Enter the number of episodes for " + series.SeriesName + ": " + series.SeriesNumberOfEpisodes);
        System.out.println("----------------------------------------------------------------------");
    }
    
    // Method to update a series
    public void UpdateSeries() {
        System.out.print("Enter the series id to update: ");
        String updateId = scanner.nextLine();
        
        SeriesModel seriesToUpdate = findSeriesById(updateId);
        
        if (seriesToUpdate != null) {
            System.out.print("Enter the series name: ");
            seriesToUpdate.SeriesName = scanner.nextLine();
            
            System.out.print("Enter the age restriction: ");
            String newAge = getValidAgeRestriction();
            seriesToUpdate.SeriesAge = newAge;
            
            System.out.print("Enter the number of episodes: ");
            seriesToUpdate.SeriesNumberOfEpisodes = scanner.nextLine();
            
            System.out.println("Series updated successfully!");
        } else {
            System.out.println("Series with ID: " + updateId + " was not found!");
        }
    }
    
    // Method to update series (for unit testing)
    public boolean updateSeriesById(String seriesId, String newName, String newAge, String newEpisodes) {
        SeriesModel seriesToUpdate = findSeriesById(seriesId);
        if (seriesToUpdate != null) {
            seriesToUpdate.SeriesName = newName;
            seriesToUpdate.SeriesAge = newAge;
            seriesToUpdate.SeriesNumberOfEpisodes = newEpisodes;
            return true;
        }
        return false;
    }
    
    // Method to delete a series
    public void DeleteSeries() {
        System.out.print("Enter the series id to delete: ");
        String deleteId = scanner.nextLine();
        
        SeriesModel seriesToDelete = findSeriesById(deleteId);
        
        if (seriesToDelete != null) {
            System.out.print("Are you sure you want to delete series " + deleteId + " from the system? Yes (y) to delete.\n");
            String confirmation = scanner.nextLine();
            
            if (confirmation.equalsIgnoreCase("y") || confirmation.equalsIgnoreCase("yes")) {
                seriesList.remove(seriesToDelete);
                System.out.println("----------------------------------------------------------------------");
                System.out.println("Series with the Id: " + deleteId + " WAS deleted");
                System.out.println("----------------------------------------------------------------------");
            } else {
                System.out.println("Delete operation cancelled.");
            }
        } else {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("Series with series Id: " + deleteId + " was not found!");
            System.out.println("----------------------------------------------------------------------");
        }
    }
    
    // Method to delete series (for unit testing)
    public boolean deleteSeriesById(String seriesId) {
        SeriesModel seriesToDelete = findSeriesById(seriesId);
        if (seriesToDelete != null) {
            seriesList.remove(seriesToDelete);
            return true;
        }
        return false;
    }
    
    // Method to display series report
    public void SeriesReport() {
        if (seriesList.isEmpty()) {
            System.out.println("No series data available to display.");
            return;
        }
        
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("----------------------------------------------------------------------");
            System.out.println("SERIES ID: " + series.SeriesId);
            System.out.println("SERIES NAME:  " + series.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
            System.out.println("NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
            System.out.println("----------------------------------------------------------------------");
        }
    }
    
    // Method to exit application
    public void ExitSeriesApplication() {
        System.out.println("Thank you for using the TV Series Management Application!");
        System.exit(0);
    }
    
    // Method to add series (for unit testing)
    public void addSeries(SeriesModel series) {
        seriesList.add(series);
    }
    
    // Method to get series count (for unit testing)
    public int getSeriesCount() {
        return seriesList.size();
    }
    
    // Method to clear all series (for unit testing)
    public void clearAllSeries() {
        seriesList.clear();
    }
}