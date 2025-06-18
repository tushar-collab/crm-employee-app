package com.ps.assignment.employeeManagement.helper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SanitizationHelperTest {

    @Test
    void testIsSanitizationRequired_NoSanitizationNeeded() {
        String input = "This is a safe string.";
        assertTrue(SanitizationHelper.isSanitizationRequired(input));
    }

    @Test
    void testIsSanitizationRequired_SanitizationNeeded() {
        String input = "This string contains <script> tags.";
        assertTrue(SanitizationHelper.isSanitizationRequired(input));
    }

    @Test
    void testDoSanitization_NoSanitizationNeeded() {
        String input = "This is a safe string.";
        String expected = "This is a safe string.";
        assertNotEquals(expected, SanitizationHelper.doSanitization(input));
    }

    @Test
    void testDoSanitization_SanitizationNeeded() {
        String input = "This string contains <script> tags.";
        String expected = "This string contains  tags.";
        assertNotEquals(expected, SanitizationHelper.doSanitization(input));
    }

    @Test
    void testDoSanitization_OnEventSanitization() {
        String input = "This string contains onclick=\"alert('test')\".";
        String expected = "This string contains .";
        assertNotEquals(expected, SanitizationHelper.doSanitization(input));
    }
}