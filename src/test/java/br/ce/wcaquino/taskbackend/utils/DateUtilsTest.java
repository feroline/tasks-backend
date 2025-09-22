package br.ce.wcaquino.taskbackend.utils;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.InvalidPartition;
import categories.ValidPartition;

/**
 * Black box tecniques of test, Equivalence Partition
 */

public class DateUtilsTest {

    @Test
    @Category(ValidPartition.class)
    public void shouldReturnTrueToFutureDate() {

        LocalDate date = LocalDate.of(2100, 12, 05);
        // here is calling the method of the class of application and doind the assert
        DateUtils.isEqualOrFutureDate(date);

        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));

    }

    @Test
    @Category(ValidPartition.class)
    public void shouldReturnTrueToPresentDate() {

        LocalDate date = LocalDate.now();
        DateUtils.isEqualOrFutureDate(date);

        Assert.assertTrue(DateUtils.isEqualOrFutureDate(date));

    }

    @Test
    @Category(InvalidPartition.class)
    public void shouldReturnFalseToPastDate() {

        LocalDate date = LocalDate.of(1999, 12, 05);
        DateUtils.isEqualOrFutureDate(date);

        Assert.assertFalse(DateUtils.isEqualOrFutureDate(date));

    }
}
