package com.ioof.proj;

import java.io.InputStream;
import java.util.Scanner;


/**
 * Sample class to accept two dates in "DD MM YYYY" format and find the
 * difference in Days.
 * 
 * @author ratha
 */

public class DateDiff {

    private String earliestDate;
    private String latestDate;

    public DateDiff() {

    }

    public DateDiff(String earliest, String latest) {
        this.earliestDate = earliest;
        this.latestDate = latest;
    }

    private void validateDates() throws CustomException {

        earliestDate = earliestDate.replace(" ", "");
        latestDate = latestDate.replace(" ", "");

        if (earliestDate.length() > 8 || latestDate.length() > 8) {
            throw new CustomException(
                "Date format error, please pass the dates in DD MM YYYY format. ");
        }

        if (!earliestDate.matches("[0-9]+") || !latestDate.matches("[0-9]+")) {
            throw new CustomException(
                "Date format error, please pass the dates in DD MM YYYY format with numerics only. ");
        }

        if (Integer.parseInt(latestDate.substring(4)) < Integer
            .parseInt(earliestDate.substring(4))) {
            throw new CustomException(
                "Variables error.Plaese pass latestDate as second parameter");
        }
        if (Integer.parseInt(latestDate.substring(4)) == Integer
            .parseInt(earliestDate.substring(4))) {

            if (Integer.parseInt(latestDate.substring(2, 4)) < Integer
                .parseInt(earliestDate.substring(2, 4))) {
                throw new CustomException(
                    "Variables error.Plaese pass latestDate as second parameter");
            } else if ((Integer.parseInt(latestDate.substring(2, 4)) == Integer
                .parseInt(earliestDate.substring(2, 4)))
                && (Integer.parseInt(latestDate.substring(0, 2)) < Integer
                    .parseInt(earliestDate.substring(0, 2)))) {
                throw new CustomException(
                    "Variables error.Plaese pass latestDate as second parameter");
            }
        }

    }

    /**
     * Compute difference between two dates
     * @return
     * @throws CustomException
     */
    public int computeDiff() throws CustomException {
        int year, month, day, tempDays = 0;

        validateDates();

        year = Integer.parseInt(latestDate.substring(4))
            - Integer.parseInt(earliestDate.substring(4));

        month = Integer.parseInt(latestDate.substring(2, 4))
            - Integer.parseInt(earliestDate.substring(2, 4));

        day = Integer.parseInt(latestDate.substring(0, 2))
            - Integer.parseInt(earliestDate.substring(0, 2));

        if (month < 0) {
            month = 12 + month;
            year = year - 1;
        }

        if (day < 0) {
            if (month > 0) {
                day = 30 + day;
                month = month - 1;
            } else {
                day = 365 + day;
                year = year - 1;
            }
        }

        if (year > 0) {
            tempDays += year * 365;
        }
        if (month > 0) {
            tempDays += month * 30;
        }
        if (day > 0) {
            tempDays += day;
        }
        return tempDays;
    }

    public static void main(String... args) throws Exception {

        InputStream inputStream = new DateDiff().getClass().getClassLoader().getResourceAsStream("sampledata");
        
        try (Scanner scanner = new Scanner(inputStream)) {

            while (scanner.hasNextLine()) {
                String[] variables = scanner.nextLine().split(",");
                DateDiff diff = new DateDiff(variables[0], variables[1]);
                int days = diff.computeDiff();
                System.out.println("Earliest Date : "+variables[0] + " Latest Date : " + variables[1]
                    + " Difference : " + days + " days");
            }

            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
