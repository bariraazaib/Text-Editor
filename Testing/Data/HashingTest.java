package Data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import dal.HashCalculator;

class HashingTest {
    
    @Test
    @DisplayName("Hash should change when file content is edited")
    void testHashChangesAfterEdit() {
        try {
            // Arrange: Original content
            String originalContent = "This is the original content of the file";
            String originalHash = HashCalculator.calculateHash(originalContent);
            
            // Act: Edit the content
            String editedContent = "This is the edited content of the file";
            String currentHash = HashCalculator.calculateHash(editedContent);
            
            // Assert: Current hash should be different from original
            assertNotEquals(originalHash, currentHash, 
                            "Hash should change after content modification");
        } catch (Exception e) {
            fail("Hash calculation threw exception: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Same content should always produce same hash")
    void testHashConsistency() {
        try {
            // Arrange
            String content = "Consistent test content";
            
            // Act: Calculate hash twice
            String hash1 = HashCalculator.calculateHash(content);
            String hash2 = HashCalculator.calculateHash(content);
            
            // Assert: Both hashes should be identical
            assertEquals(hash1, hash2, 
                         "Same content should always produce same hash");
        } catch (Exception e) {
            fail("Hash calculation threw exception: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash should handle empty content without error")
    void testHashWithEmptyContent() {
        // Arrange
        String emptyContent = "";
        
        // Assert: Should not throw exception
        assertDoesNotThrow(() -> {
            String hash = HashCalculator.calculateHash(emptyContent);
            assertNotNull(hash, "Hash should not be null for empty content");
        }, "Should handle empty content gracefully");
    }
    
    @Test
    @DisplayName("Hash should be 32 characters long (MD5 format)")
    void testHashLength() {
        try {
            // Arrange
            String content = "Test content";
            
            // Act
            String hash = HashCalculator.calculateHash(content);
            
            // Assert: MD5 hash is always 32 hex characters
            assertEquals(32, hash.length(), 
                         "MD5 hash should be 32 characters long");
        } catch (Exception e) {
            fail("Hash calculation threw exception: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash should only contain hexadecimal characters")
    void testHashFormat() {
        try {
            // Arrange
            String content = "Test content for format check";
            
            // Act
            String hash = HashCalculator.calculateHash(content);
            
            // Assert: Should only contain 0-9 and A-F
            assertTrue(hash.matches("[0-9A-F]+"), 
                       "Hash should only contain hexadecimal characters (0-9, A-F)");
        } catch (Exception e) {
            fail("Hash calculation threw exception: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Hash should handle special characters")
    void testHashWithSpecialCharacters() {
        // Arrange
        String specialContent = "Special chars: @#$%^&*()_+{}|:<>?";
        
        // Assert: Should process without error
        assertDoesNotThrow(() -> {
            String hash = HashCalculator.calculateHash(specialContent);
            assertNotNull(hash, "Hash should not be null");
            assertFalse(hash.isEmpty(), "Hash should not be empty");
            assertEquals(32, hash.length(), "Hash should be 32 characters");
        }, "Should handle special characters");
    }
    
    @Test
    @DisplayName("Hash should handle Arabic/Urdu text")
    void testHashWithArabicText() {
        // Arrange
        String arabicContent = "مرحبا بك في برنامج تحرير النصوص";
        assertDoesNotThrow(() -> {
            String hash = HashCalculator.calculateHash(arabicContent);
            assertNotNull(hash, "Hash should not be null for Arabic text");
            assertEquals(32, hash.length(), "Hash should be 32 characters");
        }, "Should handle Arabic/Urdu text");
    }
    
    @Test
    @DisplayName("Hash should handle very long text")
    void testHashWithLongText() {
        try {
            // Arrange: Create a long string
            StringBuilder longText = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                longText.append("This is a test sentence. ");
            }
            
            // Act
            String hash = HashCalculator.calculateHash(longText.toString());
            
            // Assert: Should still produce valid 32-character hash
            assertNotNull(hash, "Hash should not be null for long text");
            assertEquals(32, hash.length(), "Hash should be 32 characters even for long text");
        } catch (Exception e) {
            fail("Hash calculation threw exception: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Different content should produce different hashes")
    void testDifferentContentProducesDifferentHashes() {
        try {
            // Arrange
            String content1 = "Content A";
            String content2 = "Content B";
            
            // Act
            String hash1 = HashCalculator.calculateHash(content1);
            String hash2 = HashCalculator.calculateHash(content2);
            
            // Assert
            assertNotEquals(hash1, hash2, 
                            "Different content should produce different hashes");
        } catch (Exception e) {
            fail("Hash calculation threw exception: " + e.getMessage());
        }
    }
    
    @Test
    @DisplayName("Small change in content should change entire hash")
    void testSmallChangeChangesHash() {
        try {
            // Arrange
            String content1 = "Hello World";
            String content2 = "Hello world";  // Just case change
            
            // Act
            String hash1 = HashCalculator.calculateHash(content1);
            String hash2 = HashCalculator.calculateHash(content2);
            assertNotEquals(hash1, hash2, 
                            "Even small changes should produce different hashes");
        } catch (Exception e) {
            fail("Hash calculation threw exception: " + e.getMessage());
        }
    }
}