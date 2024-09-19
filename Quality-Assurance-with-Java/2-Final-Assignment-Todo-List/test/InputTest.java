package org.campusmolndal;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The InputTest class contains unit tests for the Input class.
 */
class InputTest {

    private Input sut;
    private InputStream inputStream;
    private Scanner scanner;

    /**
     * Tests the readStringFromUser method of the Input class.
     */
    @Test
    void testReadStringFromUser() {
        // Arrange
        String fakeInput = "Test";
        inputStream = new ByteArrayInputStream(fakeInput.getBytes());
        scanner = new Scanner(inputStream);
        sut = new Input(scanner);

        // Act
        String result = sut.readStringFromUser();

        // Assert
        assertEquals(fakeInput, result);
    }

    /**
     * Tests the readCorrectIntFromUser method of the Input class.
     */
    @Test
    void testReadIntegerFromUser() {
        // Arrange
        String fakeInput = "1";
        inputStream = new ByteArrayInputStream(fakeInput.getBytes());
        scanner = new Scanner(inputStream);
        sut = new Input(scanner);

        // Act
        int actual = sut.readCorrectIntFromUser();

        // Assert
        assertEquals(1, actual);
    }
}
