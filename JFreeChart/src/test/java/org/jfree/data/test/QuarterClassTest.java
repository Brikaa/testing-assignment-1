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
    private Quarter lowerLimitQuarter;
    private Quarter upperLimitQuarter;

    @Before
    public void init() {
        lowerLimitQuarter = new Quarter(MIN_QUARTER, MIN_YEAR);
        upperLimitQuarter = new Quarter(MAX_QUARTER, MAX_YEAR);
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
        assertQuarter(new Quarter(MAX_QUARTER, MAX_YEAR), MAX_QUARTER, MAX_YEAR);
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
        Quarter quarter = new Quarter(MAX_QUARTER, new Year(MAX_YEAR));
        assertQuarter(quarter, MAX_QUARTER, MAX_YEAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitActualYearConstructor() {
        new Quarter(MAX_QUARTER, new Year(MAX_YEAR + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitPositiveActualYearConstructor() {
        new Quarter(MAX_QUARTER, new Year(MAX_YEAR + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitQuarterActualYearConstructor() {
        new Quarter(MAX_QUARTER + 1, new Year(MAX_YEAR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitQuarterActualYearConstructor() {
        new Quarter(MIN_QUARTER - 1, new Year(MAX_YEAR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterAndNullYearConstructor() {
        new Quarter(MAX_QUARTER, null);
    }

    @Test
    public void testDateConstructor() {
        Date date = new Date(1672531200000l);
        Quarter quarter = new Quarter(date);
        assertQuarter(quarter, 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitDateConstructor() {
        new Quarter(new Date(253412011061000l));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDateConstructor() {
        new Quarter(null);
    }

    @Test
    public void testDateAndTimezoneConstructor() {
        TimeZone tz = TimeZone.getTimeZone("Africa/Cairo");
        Date date = new Date(1672531200000l);
        Quarter quarter = new Quarter(date, tz);
        assertQuarter(quarter, 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDateAndNullConstructor() {
        System.out.println(new Quarter(new Date(1672531200000l), null));
    }

    // ---------------------------- compareTo tests ----------------------------

    @Test
    public void testCompareToEqual() {
        Quarter quarter = new Quarter(1, 1900);
        Assert.assertEquals(0, lowerLimitQuarter.compareTo(quarter));
    }

    @Test
    public void testCompareToEqualToSelf() {
        Assert.assertEquals(0, lowerLimitQuarter.compareTo(lowerLimitQuarter));
    }

    @Test
    public void testCompareToLessThan() {
        Assert.assertTrue(lowerLimitQuarter.compareTo(upperLimitQuarter) < 0);
    }

    @Test
    public void testCompareToGreaterThan() {
        Assert.assertTrue(upperLimitQuarter.compareTo(lowerLimitQuarter) > 0);
    }

    @Test // Something the spec does not mention (bug?)
    public void testCompareToNull() {
        Assert.assertTrue(lowerLimitQuarter.compareTo(null) > 0);
    }

    @Test // Something the spec does not mention
    public void compareToDifferentClass() {
        Assert.assertTrue(lowerLimitQuarter.compareTo(new Object()) > 0);
    }

    // ---------------------------- equals tests ----------------------------

    @Test
    public void testEqualsEqual() {
        Assert.assertEquals(lowerLimitQuarter, lowerLimitQuarter);
    }

    @Test
    public void testEqualsNotEqual() {
        Assert.assertNotEquals(lowerLimitQuarter, upperLimitQuarter);
    }

    @Test
    public void testEqualsNull() {
        Assert.assertNotEquals(lowerLimitQuarter, null);
    }

    @Test
    public void testEqualsDifferentClass() {
        Assert.assertNotEquals(lowerLimitQuarter, new Object());
    }

    // ---------------------------- hashCode tests ----------------------------

    @Test
    public void testHashCodeEqual() {
        Assert.assertEquals(lowerLimitQuarter.hashCode(), lowerLimitQuarter.hashCode());
    }

    @Test
    public void testHashCodeNotEqual() {
        Assert.assertNotEquals(lowerLimitQuarter.hashCode(), upperLimitQuarter.hashCode());
    }
}
