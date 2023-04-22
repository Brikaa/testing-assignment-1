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
    public void testUnderLimitYearConstructor() {
        new Quarter(3, MIN_YEAR - 1);
    }

    @Test
    public void testQuarterAndYearObjectConstructor() {
        Quarter quarter = new Quarter(MAX_QUARTER, new Year(MAX_YEAR));
        assertQuarter(quarter, MAX_QUARTER, MAX_YEAR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitYearObjectConstructor() {
        new Quarter(MAX_QUARTER, new Year(MAX_YEAR + 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitYearObjectConstructor() {
        new Quarter(MAX_QUARTER, new Year(MIN_YEAR - 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOverLimitQuarterYearObjectConstructor() {
        new Quarter(MAX_QUARTER + 1, new Year(MAX_YEAR));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnderLimitQuarterYearObjectConstructor() {
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
    public void testUnderLimitDateConstructor() {
        new Quarter(new Date(-2240521139000l));
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
        Quarter quarter = new Quarter(MIN_QUARTER, MIN_YEAR);
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
        Quarter quarter = new Quarter(MIN_QUARTER, MIN_YEAR);
        Assert.assertEquals(lowerLimitQuarter, quarter);
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
        Quarter quarter = new Quarter(MIN_QUARTER, MIN_YEAR);
        Assert.assertEquals(lowerLimitQuarter.hashCode(), quarter.hashCode());
    }

    @Test
    public void testHashCodeNotEqual() {
        Assert.assertNotEquals(lowerLimitQuarter.hashCode(), upperLimitQuarter.hashCode());
    }

    // ---------------------------- getSerialIndex tests ----------------------------

    @Test
    public void testGetSerialIndexEqual() {
        Quarter quarter = new Quarter(MAX_QUARTER, MAX_YEAR);
        Assert.assertEquals(upperLimitQuarter.getSerialIndex(), quarter.getSerialIndex());
    }

    @Test
    public void testGetSerialIndexNotEqual() {
        Assert.assertNotEquals(upperLimitQuarter.getSerialIndex(), lowerLimitQuarter.getSerialIndex());
    }

    // ---------------------------- previous() tests ----------------------------
    @Test
    public void testPrevious() {
        assertQuarter((Quarter) upperLimitQuarter.previous(), MAX_QUARTER - 1, MAX_YEAR);
    }

    @Test
    public void testPreviousLowerLimit() {
        Assert.assertNull(lowerLimitQuarter.previous());
    }

    @Test
    public void testPreviousYear() {
        Quarter firstQuarterInYear = new Quarter(MIN_QUARTER, MAX_YEAR);
        assertQuarter((Quarter) firstQuarterInYear.previous(), MAX_QUARTER, MAX_YEAR - 1);
    }

    // ---------------------------- next() tests ----------------------------
    @Test
    public void testNext() {
        assertQuarter((Quarter) lowerLimitQuarter.next(), MIN_QUARTER + 1, MIN_YEAR);
    }

    @Test
    public void testNextUpperLimit() {
        Assert.assertNull(upperLimitQuarter.next());
    }

    @Test
    public void testNextYear() {
        Quarter lastQuarterInYear = new Quarter(MAX_QUARTER, MIN_YEAR);
        assertQuarter((Quarter) lastQuarterInYear.next(), MIN_QUARTER, MIN_YEAR + 1);
    }
}
