package business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.TFIDFCalculator;

public class TFIDFCalculatorTest {

    private TFIDFCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new TFIDFCalculator();
        calculator.addDocumentToCorpus("this is a test document");
        calculator.addDocumentToCorpus("this document is another test");
    }

    // -------------------- Positive Test --------------------
    @Test
    void testCalculateTfIdf_PositiveCase() {
        double result = calculator.calculateDocumentTfIdf("this is a test");

        assertFalse(Double.isNaN(result), "TF-IDF result should not be NaN");
        assertFalse(Double.isInfinite(result), "TF-IDF result should not be infinite");
        assertTrue(Double.isFinite(result), "TF-IDF should be a valid finite number");
    }

    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_SingleWordDocument() {
        double result = calculator.calculateDocumentTfIdf("test");

        assertFalse(Double.isNaN(result), "TF-IDF result should not be NaN for single word");
        assertFalse(Double.isInfinite(result), "TF-IDF result should not be infinite for single word");
        assertTrue(Double.isFinite(result), "Single word should produce valid TF-IDF");
    }

    // -------------------- Negative Test --------------------
    @Test
    void testCalculateTfIdf_EmptyDocument() {
        double result = calculator.calculateDocumentTfIdf("");

        // Empty string is handled gracefully (no crash)
        assertNotNull(result, "Empty document should not return null");
        assertTrue(Double.isFinite(result), "Empty document should return finite number (graceful handling)");
    }

    // -------------------- Negative Test --------------------
    @Test
    void testCalculateTfIdf_SpecialCharacters() {
        double result = calculator.calculateDocumentTfIdf("### $$$ %%%");

        // Special characters are preprocessed/handled gracefully
        assertNotNull(result, "Special characters should not return null");
        assertTrue(Double.isFinite(result), "Special characters should be handled gracefully");
    }
    
    // -------------------- Boundary Test --------------------
    @Test
    void testCalculateTfIdf_WordNotInCorpus() {
        double result = calculator.calculateDocumentTfIdf("xyz");

        // Word not in corpus should still return valid result
        assertTrue(Double.isFinite(result), "Unknown word should return finite TF-IDF");
    }
}