package junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsTest {

    @Test
    void assertTest() {
        // 두 값이 동일한지 확인
//        assertEquals(4, 2*2, "곱셈 결과 확인");

        // 조건식이 true
//        assertTrue(5 > 1, "조건이 참인지 확인");

//        assertNotNull(new Object(), "객체가 null이 아님을 확인");

        Exception exception = assertThrows(ArithmeticException.class, () -> {
           int result = 1/0;
        });

        // 예외 메시지 확인
        assertEquals("/ by zero", exception.getMessage());
    }
}
