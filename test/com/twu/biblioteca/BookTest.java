package com.twu.biblioteca;

import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BookTest {
    private static String successfulCheckOutMessage = "Thank you! Enjoy the book.\n";
    private static String unsuccessfulCheckOutMessage = "That book is not available.\n";
    private static String successfulReturnMessage = "Thank you for returning the book.\n";
    private static String unsuccessfulReturnMessage = "That is not a valid book to return.\n";

    private Book emptyBook = new Book();
    private Book testBookAttribute = new Book("Lord of the Rings", "ME", 1994, false);
    private Book testBookDetail = new Book("Lord of the Rings 2 |Uncle       |2018 |true");

    @Test
    public void testGetTitle() {
        assertEquals(null, emptyBook.getTitle());
        assertEquals("Lord of the Rings", testBookAttribute.getTitle());
        assertEquals("Lord of the Rings 2", testBookDetail.getTitle());
    }

    @Test
    public void testGetAuthor() {
        assertEquals(null, emptyBook.getAuthor());
        assertEquals("ME", testBookAttribute.getAuthor());
        assertEquals("Uncle", testBookDetail.getAuthor());
    }

    @Test
    public void testGetPublishYear() {
        assertEquals(0, emptyBook.getPublishYear());
        assertEquals(1994, testBookAttribute.getPublishYear());
        assertEquals(2018, testBookDetail.getPublishYear());
    }

    @Test
    public void testListBookDetail() {
        assertEquals(null, emptyBook.listBookDetail());
        assertEquals("Lord of the Rings   |ME          |1994", testBookAttribute.listBookDetail());
        assertEquals("Lord of the Rings 2 |Uncle       |2018", testBookDetail.listBookDetail());
    }

    @Test
    public void testGetCheckOutStatus() {
        assertTrue(emptyBook.getCheckOutStatus());
        assertFalse(testBookAttribute.getCheckOutStatus());
        assertTrue(testBookDetail.getCheckOutStatus());
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }


    @Test
    public void testCheckOutBook() {
        assertTrue(emptyBook.getCheckOutStatus());
        emptyBook.checkOutBook();
        assertTrue(emptyBook.getCheckOutStatus());
        assertEquals(unsuccessfulCheckOutMessage, outContent);

        assertFalse(testBookAttribute.getCheckOutStatus());
        testBookAttribute.checkOutBook();
        assertFalse(testBookAttribute.getCheckOutStatus());
        assertEquals(unsuccessfulCheckOutMessage, outContent);

        assertTrue(testBookDetail.getCheckOutStatus());
        testBookDetail.checkOutBook();
        assertFalse(testBookDetail.getCheckOutStatus());
        assertEquals(successfulCheckOutMessage, outContent);
    }

    @Test
    public void returnBook() {
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}