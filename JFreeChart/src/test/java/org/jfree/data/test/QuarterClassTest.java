package org.jfree.data.test;

import org.junit.Assert;

import java.util.Date;
import java.util.TimeZone;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.Year;
import org.junit.Test;

public class QuarterClassTest {
    private void assertQuarter(Quarter quarter, int year, int quarterInt) {
        Assert.assertEquals(year, quarter.getYear().getYear());
        Assert.assertEquals(quarterInt, quarter.getQuarter());
    }

    @Test
    public void testDefaultConstructor() {
        Quarter quarter = new Quarter();
        assertQuarter(quarter, 2023, 2);
    }

    @Test
    public void testQuarterAndIntYearConstructor() {
        Quarter quarter = new Quarter(3, 2023);
        assertQuarter(quarter, 2023, 3);
    }

    @Test(expected = IllegalArgumentException.class) // Spec says supported range is from Q1 to Q4
    public void testNegativeQuarterConstructor() {
        new Quarter(-1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitQuarterConstructor() {
        new Quarter(5, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroQuarterConstructor() {
        new Quarter(0, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeYearConstructor() {
        new Quarter(1, -2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitIntYearConstructor() {
        new Quarter(3, 10000);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitPositiveYearConstructor() {
        new Quarter(3, 1000);
    }

    @Test
    public void testQuarterAndActualYearConstructor() {
        Year year = new Year(2023);
        Quarter quarter = new Quarter(3, year);
        assertQuarter(quarter, 2023, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitActualYearConstructor() {
        Year year = new Year(10000);
        new Quarter(3, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitPositiveActualYearConstructor() {
        Year year = new Year(1000);
        new Quarter(3, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeActualYearConstructor() {
        Year year = new Year(-2023);
        new Quarter(3, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeQuarterActualYearConstructor() {
        Year year = new Year(2023);
        new Quarter(-1, year);
    }

    @Test(expected = IllegalArgumentException.class) // Spec says supported range is from Q1 to Q4
    public void testOverLimitQuarterActualYearConstructor() {
        Year year = new Year(2023);
        new Quarter(5, year);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroQuarterActualYearConstructor() {
        Year year = new Year(2023);
        new Quarter(0, year);
    }

    @Test
    public void testDateConstructor() {
        Date date = new Date(1672531200000l);
        Quarter quarter = new Quarter(date);
        assertQuarter(quarter, 2023, 1);
    }

    @Test
    public void testDateAndTimezoneConstructor() {
        TimeZone tz = TimeZone.getTimeZone("Africa/Cairo");
        Date date = new Date(1672531200000l);
        Quarter quarter = new Quarter(date, tz);
        assertQuarter(quarter, 2023, 1);
    }
}
