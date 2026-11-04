package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @ParameterizedTest(name = "score={0}, expected={1}")
    @CsvSource({"75, C", "65, D", "55, F"})
    void gradeOtherValidScoresReturnExpectedLetter(double score, String expected) {
        assertEquals(expected, calculator.letterGrade(score));
    }

    @ParameterizedTest(name = "invalid score={0}")
    @ValueSource(doubles = {-0.01, 100.01})
    void gradeOutOfRangeThrows(double score) {
        assertThrows(IllegalArgumentException.class, () -> calculator.letterGrade(score));
    }

    @Test
    void gpaAllGradeCategoriesReturnsTwo() {
        assertEquals(2.0, calculator.gpa(new double[] {95, 85, 75, 65, 55}), 0.000001);
    }

    @Test
    void gpaNullThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(null));
    }

    @Test
    void gpaEmptyThrows() {
        assertThrows(IllegalArgumentException.class, () -> calculator.gpa(new double[] {}));
    }

    @Test
    void scholarshipNegativeGpaThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(-0.01, 15, false));
    }

    @Test
    void scholarshipGpaAboveFourThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(4.01, 15, false));
    }

    @Test
    void scholarshipNegativeCreditsThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.scholarship(3.5, -1, false));
    }

    @Test
    void scholarshipViolationReturnsZero() {
        assertEquals(0, calculator.scholarship(3.9, 16, true));
    }

    @Test
    void scholarshipHighLowCreditsReturns70() {
        assertEquals(70, calculator.scholarship(3.9, 12, false));
    }

    @Test
    void scholarshipMiddleLowCreditsReturns50() {
        assertEquals(50, calculator.scholarship(3.5, 11, false));
    }

    @Test
    void scholarshipLowGpaReturnsZero() {
        assertEquals(0, calculator.scholarship(2.9, 16, false));
    }

    @Test
    void gpaOneGradeReturnsFour() {
        assertEquals(4.0, calculator.gpa(new double[] {95}), 0.000001);
    }

    @Test
    void gpaFourGradesReturnsTwoPointFive() {
        assertEquals(2.5, calculator.gpa(new double[] {95, 85, 75, 65}), 0.000001);
    }

    @ParameterizedTest(name = "boundary score={0}, expected={1}")
    @CsvSource({
            "60, D",
            "59.99, F",
            "69.99, D",
            "0, F",
            "100, A",
            "89.99, B",
            "90, A"
    })
    void gradeBoundaryReturnsExpectedLetter(double score, String expected) {
        assertEquals(expected, calculator.letterGrade(score));
    }
}
