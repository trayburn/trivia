package com.improving;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TimeoutTests {
    @Test
    public void Constructor_Should_Not_Start_Timeout() {
        // Arrange
        // Act
        var target = new Timeout(30);

        // Assert
        assertFalse(target.isStarted());
    }

    @Test
    public void Start_Should_Start_Timeout() {
        // Arrange
        var target = new Timeout(30);

        // Act
        target.start();

        // Assert
        assertTrue(target.isStarted());
    }

    @Test
    public void Time_Passing_Should_Expire_Timeout() throws InterruptedException {
        // Arrange
        var target = new Timeout(1).start();
        Thread.sleep(1000);

        // Act
        var result = target.isExpired();

        // Assert
        assertTrue(result);
    }


    @Test
    public void IsExpired_Should_Return_False_If_Interval_Not_Exceeded() {
        // Arrange
        var target = new Timeout(60).start();

        // Act
        var result = target.isExpired();

        // Assert
        assertFalse(result);
    }

    @Test
    public void toString_Should_Return_Seconds_Remaining() {
        // Arrange
        var target = new Timeout(60).start();

        // Act
        var result = target.toString();

        // Assert
        assertEquals("60 seconds", result);
    }


    @Test
    public void getRemainingSeconds_Should_Return_Seconds_Remaining() {
        // Arrange
        var target = new Timeout(60).start();

        // Act
        var result = target.getRemainingSeconds();

        // Assert
        assertEquals(60, result);
    }


    @Test
    public void toString_Should_Return_Expired() throws InterruptedException {
        // Arrange
        var target = new Timeout(0).start();
        Thread.sleep(50);

        // Act
        var result = target.toString();

        // Assert
        assertEquals("EXPIRED", result);
    }

}
