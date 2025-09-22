package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.invalidPartition;
import categories.validPartition;

/**
 * Black box tecniques of test, Equivalence Partition
 */

public class DateUtilsTest {

    @Test
    @Category(validPartition.class)
    public void shouldReturnTrueToFutureDate() {

        LocalDate date = LocalDate.of(2100, 12, 05);
        // here is calling the method of the class of application and doind the assert
        DateUtils.isEqualOrFutureDate(date);

        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));

    }

    @Test
    @Category(validPartition.class)
    public void shouldReturnTrueToPresentDate() {

        LocalDate date = LocalDate.now();
        DateUtils.isEqualOrFutureDate(date);

        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));

    }

    @Test
    @Category(invalidPartition.class)
    public void shouldReturnFalseToPastDate() {

        LocalDate date = LocalDate.of(1999, 12, 05);
        DateUtils.isEqualOrFutureDate(date);

        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));

    }
}
