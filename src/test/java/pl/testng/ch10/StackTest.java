package pl.testng.ch10;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StackTest {

    Stack<String> stack;

    @BeforeTest
    public void setUp() {
        stack = new Stack<String>();
    }

    @Test
    public void shouldReturnEmpty() {

        assertEquals(stack.size(), 0);

    }
    @Test
    public void shouldPushAndPopNewElement() {

        String inputElement = "Test1";

        stack.push(inputElement);

        assertEquals(stack.size(),1);

        assertEquals(stack.pop(),inputElement);
    }

    @Test
    public void shouldReturnLastAddedElement() {

        stack.push("Element1").push("Element2");


        assertEquals(stack.pop(),"Element2");


    }


    @Test
    public void shouldReturnEmptyElement() {

        assertNull(stack.pop());


    }



}
