package org.jfree.data.test;

import org.junit.Assert;
import org.junit.Before;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.TimePeriod;
import org.jfree.data.time.Year;
import org.jfree.date.MonthConstants;
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

    // ------------------ Constructor tests ------------------

    private void assertQuarter(Quarter quarter, int quarterInt, int year) {
        Assert.assertEquals(year, quarter.getYear().getYear());
        Assert.assertEquals(quarterInt, quarter.getQuarter());
    }

    @Test
    public void testDefaultConstructorAndQuarterYearGetters() {
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

    // ------------------ compareTo tests ------------------

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

    @Test
    public void compareToRegularTimePeriod() {
        Assert.assertTrue(upperLimitQuarter.compareTo(upperLimitQuarter.previous()) > 0);
    }

    // ------------------ equals tests ------------------

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

    // ------------------ hashCode tests ------------------

    @Test
    public void testHashCodeEqual() {
        Quarter quarter = new Quarter(MIN_QUARTER, MIN_YEAR);
        Assert.assertEquals(lowerLimitQuarter.hashCode(), quarter.hashCode());
    }

    @Test
    public void testHashCodeNotEqual() {
        Assert.assertNotEquals(lowerLimitQuarter.hashCode(), upperLimitQuarter.hashCode());
    }

    // ------------------ getSerialIndex tests ------------------

    @Test
    public void testGetSerialIndexEqual() {
        Quarter quarter = new Quarter(MAX_QUARTER, MAX_YEAR);
        Assert.assertEquals(upperLimitQuarter.getSerialIndex(), quarter.getSerialIndex());
    }

    @Test
    public void testGetSerialIndexNotEqual() {
        Assert.assertNotEquals(upperLimitQuarter.getSerialIndex(), lowerLimitQuarter.getSerialIndex());
    }

    // ------------------ previous() tests ------------------
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

    // ------------------ next() tests ------------------
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

    // ------------------ toString() tests ------------------
    public String formQuarterString(Quarter quarter) {
        return "Q" + Integer.toString(quarter.getQuarter()) + "/" + Integer.toString(quarter.getYear().getYear());
    }

    @Test
    public void testToString() {
        Assert.assertEquals(formQuarterString(upperLimitQuarter), upperLimitQuarter.toString());
        Assert.assertEquals(formQuarterString(lowerLimitQuarter), lowerLimitQuarter.toString());
    }

    // ------------------ getFirstMillisecond(Calendar),
    // getLastMillisecond(Calendar) tests ------------------
    private abstract class MillisecondsFunction {
        public long execute(Quarter quarter, String tzid) {
            TimeZone tz = TimeZone.getTimeZone(tzid);
            return getMilliseconds(quarter, tz);
        }

        public abstract long getMilliseconds(Quarter quarter, TimeZone tz);
    }

    private class FirstMilliseconds extends MillisecondsFunction {
        @Override
        public long getMilliseconds(Quarter quarter, TimeZone tz) {
            return quarter.getFirstMillisecond(Calendar.getInstance(tz));
        }
    }

    private class LastMilliseconds extends MillisecondsFunction {
        @Override
        public long getMilliseconds(Quarter quarter, TimeZone tz) {
            return quarter.getLastMillisecond(Calendar.getInstance(tz));
        }

    }

    private void assertMilliseconds(Quarter quarter, long expectedMilliseconds, String tzid, MillisecondsFunction fn) {
        long milliseconds = fn.execute(quarter, tzid);
        Assert.assertEquals(expectedMilliseconds, milliseconds);
    }

    @Test
    public void testGetFirstMillisecond() {
        assertMilliseconds(upperLimitQuarter, 253394341200000l, "Africa/Cairo", new FirstMilliseconds());
    }

    @Test
    public void testGetFirstMillisecondNegative() {
        assertMilliseconds(lowerLimitQuarter, -2208996000000l, "Africa/Cairo", new FirstMilliseconds());
    }

    @Test
    public void testGetLastMillisecond() {
        assertMilliseconds(upperLimitQuarter, 253402293599999l, "Africa/Cairo", new LastMilliseconds());
    }

    @Test
    public void testGetLastMillisecondNegative() {
        assertMilliseconds(lowerLimitQuarter, -2201220309001l, "Africa/Cairo", new LastMilliseconds());
    }

    // ------------------ parseQuarter() tests ------------------
    @Test
    public void testParseQuarterYearFirstSpace() {
        assertQuarter(Quarter.parseQuarter("2023 Q1"), 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstSpaceUpperLimitYear() {
        Quarter.parseQuarter("10000 Q1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstSpaceLowerLimitYear() {
        Quarter.parseQuarter("1000 Q1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstSpaceInvalidQuarter() {
        Quarter.parseQuarter("2023 Q5");
    }

    @Test
    public void testParseQuarterYearFirstDash() {
        assertQuarter(Quarter.parseQuarter("2023-Q1"), 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstDashUpperLimitYear() {
        Quarter.parseQuarter("10000-Q1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstDashLowerLimitYear() {
        Quarter.parseQuarter("1000-Q1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstDashInvalidQuarter() {
        Quarter.parseQuarter("2023-Q5");
    }

    @Test
    public void testParseQuarterYearFirstSlash() {
        assertQuarter(Quarter.parseQuarter("2023/Q1"), 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstSlashUpperLimitYear() {
        Quarter.parseQuarter("10000/Q1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstSlashLowerLimitYear() {
        Quarter.parseQuarter("1000/Q1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterYearFirstSlashInvalidQuarter() {
        Quarter.parseQuarter("2023/Q5");
    }

    @Test
    public void testParseQuarterQuarterFirstSpace() {
        assertQuarter(Quarter.parseQuarter("Q1 2023"), 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstSpaceUpperLimitYear() {
        Quarter.parseQuarter("Q1 10000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstSpaceLowerLimitYear() {
        Quarter.parseQuarter("Q1 1000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstSpaceInvalidQuarter() {
        Quarter.parseQuarter("Q5 2023");
    }

    @Test
    public void testParseQuarterQuarterFirstDash() {
        assertQuarter(Quarter.parseQuarter("Q1-2023"), 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstDashUpperLimitYear() {
        Quarter.parseQuarter("Q1-10000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstDashLowerLimitYear() {
        Quarter.parseQuarter("Q1-1000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstDashInvalidQuarter() {
        Quarter.parseQuarter("Q5-2023");
    }

    @Test
    public void testParseQuarterQuarterFirstSlash() {
        assertQuarter(Quarter.parseQuarter("Q1/2023"), 1, 2023);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstSlashUpperLimitYear() {
        Quarter.parseQuarter("Q1/10000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstSlashLowerLimitYear() {
        Quarter.parseQuarter("Q1/1000");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testParseQuarterQuarterFirstSlashInvalidQuarter() {
        Quarter.parseQuarter("Q5/2023");
    }

    // ------------------ Misc ------------------
    @Test
    public void testIfSerializable() {
        Assert.assertTrue(lowerLimitQuarter instanceof Serializable);
    }

    @Test
    public void testIfTimePeriod() {
        Assert.assertTrue(lowerLimitQuarter instanceof TimePeriod);
    }

    @Test
    public void testIfMonthConstants() {
        Assert.assertTrue(lowerLimitQuarter instanceof MonthConstants);
    }

}
