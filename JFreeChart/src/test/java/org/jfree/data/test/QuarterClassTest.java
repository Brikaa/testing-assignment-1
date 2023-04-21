package org.jfree.data.test;

import org.junit.Assert;
import org.jfree.data.time.Quarter;
import org.junit.Test;

public class QuarterClassTest {
    Quarter quarter;

    private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }

    private void arrange() {
        quarter = new Quarter();
    }

    @Test
    public void testQuarterDefaultConstructor() {
        arrange();
        Assert.assertEquals(2023, quarter.getYear().getYear());
        Assert.assertEquals(2, quarter.getQuarter());
    }

}
