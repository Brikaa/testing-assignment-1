package org.jfree.data.test;

import org.junit.Assert;
import org.junit.Before;

import java.util.Date;
import java.util.TimeZone;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.Year;
import org.junit.Test;

public class QuarterClassTest {
    private final int MIN_YEAR = 1900;
    private final int MAX_YEAR = 9999;
    private final int MIN_QUARTER = 1;
    private final int MAX_QUARTER = 4;
    private final int NORMAL_YEAR = 2023;
    private final int NORMAL_QUARTER = 3;
    private Quarter q1Y1900;
    private Quarter q4Y9999;

    @Before
    public void init() {
        q1Y1900 = new Quarter(MIN_QUARTER, MIN_YEAR);
        q4Y9999 = new Quarter(MAX_QUARTER, MAX_YEAR);
    }

    // ---------------------------- Constructor tests ----------------------------

    private void assertQuarter(Quarter quarter, int quarterInt, int year) {
        Assert.assertEquals(year, quarter.getYear().getYear());
        Assert.assertEquals(quarterInt, quarter.getQuarter());
    }

    @Test
    public void testDefaultConstructor() {
        Quarter quarter = new Quarter();
        assertQuarter(quarter, 2, 2023);
    }

    @Test
    public void testQuarterAndIntYearConstructor() {
        assertQuarter(new Quarter(NORMAL_QUARTER, NORMAL_YEAR), NORMAL_QUARTER, NORMAL_YEAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitQuarterConstructor() {
        new Quarter(MAX_QUARTER + 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitQuarterConstructor() {
        new Quarter(MIN_QUARTER - 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitIntYearConstructor() {
        new Quarter(3, MAX_YEAR + 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitPositiveYearConstructor() {
        new Quarter(3, MIN_YEAR - 1);
    }

    @Test
    public void testQuarterAndActualYearConstructor() {
        Quarter quarter = new Quarter(NORMAL_QUARTER, new Year(NORMAL_YEAR));
        assertQuarter(quarter, NORMAL_QUARTER, NORMAL_YEAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitActualYearConstructor() {
        new Quarter(NORMAL_QUARTER, new Year(MAX_YEAR + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitPositiveActualYearConstructor() {
        new Quarter(NORMAL_QUARTER, new Year(MAX_YEAR + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitQuarterActualYearConstructor() {
        new Quarter(MAX_QUARTER + 1, new Year(NORMAL_YEAR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitQuarterActualYearConstructor() {
        new Quarter(MIN_QUARTER - 1, new Year(NORMAL_YEAR));
    }

    @Test
    public void testDateConstructor() {
        Date date = new Date(1672531200000l);
        Quarter quarter = new Quarter(date);
        assertQuarter(quarter, 1, 2023);
    }

    @Test
    public void testDateAndTimezoneConstructor() {
        TimeZone tz = TimeZone.getTimeZone("Africa/Cairo");
        Date date = new Date(1672531200000l);
        Quarter quarter = new Quarter(date, tz);
        assertQuarter(quarter, 1, 2023);
    }

    // ---------------------------- compareTo tests ----------------------------

    @Test
    public void testCompareToEqual() {
        Quarter quarter = new Quarter(1, 1900);
        Assert.assertEquals(0, q1Y1900.compareTo(quarter));
    }

    @Test
    public void testCompareToEqualToSelf() {
        Assert.assertEquals(0, q1Y1900.compareTo(q1Y1900));
    }

    @Test
    public void testCompareToLessThan() {
        Assert.assertTrue(q1Y1900.compareTo(q4Y9999) < 0);
    }

    @Test
    public void testCompareToGreaterThan() {
        Assert.assertTrue(q4Y9999.compareTo(q1Y1900) > 0);
    }

    @Test // Something the spec does not mention (bug?)
    public void testCompareToNull() {
        Assert.assertTrue(q1Y1900.compareTo(null) > 0);
    }

    @Test // Something the spec does not mention
    public void compareToDifferentClass() {
        Assert.assertTrue(q1Y1900.compareTo(new Object()) > 0);
    }

    // ---------------------------- equals tests ----------------------------

    @Test
    public void testEqualsEqual() {
        Assert.assertEquals(q1Y1900, q1Y1900);
    }

    @Test
    public void testEqualsNotEqual() {
        Assert.assertNotEquals(q1Y1900, q4Y9999);
    }

    @Test
    public void testEqualsNull() {
        Assert.assertNotEquals(q1Y1900, null);
    }

    @Test
    public void testEqualsDifferentClass() {
        Assert.assertNotEquals(q1Y1900, new Object());
    }

    // ---------------------------- hashCode tests ----------------------------

    @Test
    public void testHashCodeEqual() {
        Assert.assertEquals(q1Y1900.hashCode(), q1Y1900.hashCode());
    }

    @Test
    public void testHashCodeNotEqual() {
        Assert.assertNotEquals(q1Y1900.hashCode(), q4Y9999.hashCode());
    }
}
