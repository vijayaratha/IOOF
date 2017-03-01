package com.ioof.proj.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ioof.proj.CustomException;
import com.ioof.proj.DateDiff;

public class DateDiffTest {

    /**
     * ErrorCases
     */
    @Test
    public void errorCase_ContainsLetter_Test()  {
        DateDiff diff = new DateDiff("AA 11 1989", "10 12 1989");
        try {
             diff.computeDiff();
        } catch (CustomException e) {
           assertTrue(e.getMessage().contains("numerics only"));
        }
    }

    @Test
    public void errorCase_DifferentFormat_Test()  {
        DateDiff diff = new DateDiff("21:11:1989", "10:12:1989");
        try {
             diff.computeDiff();
        } catch (CustomException e) {
           assertTrue(e.getMessage().contains("DD MM YYYY format"));
        }
    }
    
    @Test
    public void errorCase_LatestDateSmallerThanEarliestDate_Test()  {
        DateDiff diff = new DateDiff("21 11 1989", "10 12 1988");
        try {
             diff.computeDiff();
        } catch (CustomException e) {
           assertTrue(e.getMessage().contains("latestDate as second parameter"));
        }
    }
    
    /**
     * Different Inputs
     */
    
    @Test
    public void dateMonthYearGreaterinLatestDateTest() {
        DateDiff diff = new DateDiff("10 08 1989", "15 10 1990");

        try {
            int days = diff.computeDiff();
            assertTrue(days == 430);
        } catch (CustomException e) {
            e.printStackTrace();

        }

    }
    
    @Test
    public void yearGreaterinLatestDateTest() {
        DateDiff diff = new DateDiff("10 08 1989", "09 07 1990");

        try {
            int days = diff.computeDiff();
            assertTrue(days == 329);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void monthGreaterinLatestDateTest() {
        DateDiff diff = new DateDiff("10 08 1989", "10 09 1989");

        try {
            int days = diff.computeDiff();
            assertTrue(days == 30);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void dayGreaterinLatestDateTest() {
        DateDiff diff = new DateDiff("10 08 1989", "11 08 1989");

        try {
            int days = diff.computeDiff();
            assertTrue(days == 1);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void equalTest() {
        DateDiff diff = new DateDiff("10 08 1989", "10 08 1989");

        try {
            int days = diff.computeDiff();
            assertTrue(days == 0);
        } catch (CustomException e) {
            e.printStackTrace();
        }
    }
}
