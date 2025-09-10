/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tvseriesmanagementapp;

/**
 *
 * @author lucam
 */
import java.util.Scanner;

public class TVSeriesManagementApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Series seriesManager = new Series();
        
        // Initial welcome screen
        System.out.println("LATEST SERIES – 2025");
        System.out.println("************************************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        String initialChoice = scanner.nextLine();
        
        if (!initialChoice.equals("1")) {
            System.out.println("Thank you for using the TV Series Management Application!");
            return;
        }
        
        // Main application loop
        while (true) {
            seriesManager.displayMainMenu();
            System.out.print("Please select an option: ");
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    seriesManager.CaptureSeries();
                    break;
                case "2":
                    seriesManager.SearchSeries();
                    break;
                case "3":
                    seriesManager.UpdateSeries();
                    break;
                case "4":
                    seriesManager.DeleteSeries();
                    break;
                case "5":
                    seriesManager.SeriesReport();
                    break;
                case "6":
                    seriesManager.ExitSeriesApplication();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            
            // Ask user if they want to continue
            System.out.print("Enter (1) to launch menu or any other key to exit: ");
            String continueChoice = scanner.nextLine();
            
            if (!continueChoice.equals("1")) {
                seriesManager.ExitSeriesApplication();
            }
        }
    }
}