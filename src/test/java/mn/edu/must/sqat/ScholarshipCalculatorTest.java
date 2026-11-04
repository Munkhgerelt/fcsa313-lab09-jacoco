package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calculator = new ScholarshipCalculator();

    @Test
    void gradeHighScoreReturnsA() {
        assertEquals("A", calculator.letterGrade(95));
    }

    @Test
    void gpaTwoGradesReturnsThreePointFive() {
        assertEquals(3.5, calculator.gpa(new double[] {95, 85}), 0.000001);
    }

    @Test
    void scholarshipHighReturns100() {
        assertEquals(100, calculator.scholarship(3.9, 16, false));
    }

    @Test
    void scholarshipMiddleReturns70() {
        assertEquals(70, calculator.scholarship(3.5, 12, false));
    }

    @Test
    void scholarshipBasicReturns50() {
        assertEquals(50, calculator.scholarship(3.2, 10, false));
    }
}
