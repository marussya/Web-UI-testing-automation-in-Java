package ru.gb.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.gb.qa.lesson04.TriangleArea;

public class TriangleTest {
    @Test
    void successCalculationTest() throws Exception {
        Assertions.assertEquals(TriangleArea.calculateArea(10, 10,10), 75);
    }

}
