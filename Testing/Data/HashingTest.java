package Data;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import dal.HashCalculator;

class HashingTest {
    
    private HashCalculator hashCalculator;
    
    @BeforeEach
    void setUp() {
        hashCalculator = new HashCalculator();
    }
    
    @Test
    @DisplayName("Hash should change when file content is edited")
    void testHashChangesAfterEdit() {
        // Arrange: Original content
        String originalContent = "This is the original content of the file";
        String originalHash = hashCalculator.getMD5(originalContent);
        
        // Act: Edit the content
        String editedContent = "This is the edited content of the file";
        String currentHash = hashCalculator.getMD5(editedContent);
        
        // Assert: Current hash should be different from original
        assertNotEquals(originalHash, currentHash, 
                        "Hash should change after content modification");
    }
    
    @Test
    @DisplayName("Same content should always produce same hash")
    void testHashConsistency() {
        // Arrange
        String content = "Consistent test content";
        
        // Act: Calculate hash twice
        String hash1 = hashCalculator.getMD5(content);
        String hash2 = hashCalculator.getMD5(content);
        
        // Assert: Both hashes should be identical
        assertEquals(hash1, hash2, 
                     "Same content should always produce same hash");
    }
    
    @Test
    @DisplayName("SHA1 hash should also change on content edit")
    void testSHA1HashChanges() {
        // Arrange
        String content1 = "Content version 1";
        String content2 = "Content version 2";
        
        // Act
        String sha1Hash1 = hashCalculator.getSHA1(content1);
        String sha1Hash2 = hashCalculator.getSHA1(content2);
        
        // Assert
        assertNotEquals(sha1Hash1, sha1Hash2, 
                        "SHA1 hash should differ for different content");
    }
    
    @Test
    @DisplayName("Hash should handle empty content without error")
    void testHashWithEmptyContent() {
        // Arrange
        String emptyContent = "";
        
        // Assert: Should not throw exception
        assertDoesNotThrow(() -> {
            hashCalculator.getMD5(emptyContent);
        }, "Should handle empty content gracefully");
    }
    
    @Test
    @DisplayName("MD5 and SHA1 should produce different hashes for same content")
    void testMD5vsSHA1Difference() {
        // Arrange
        String content = "Test content for hash comparison";
        
        // Act
        String md5Hash = hashCalculator.getMD5(content);
        String sha1Hash = hashCalculator.getSHA1(content);
        
        // Assert: MD5 and SHA1 should be different
        assertNotEquals(md5Hash, sha1Hash, 
                        "MD5 and SHA1 should produce different hashes");
    }
    
    @Test
    @DisplayName("Hash should handle special characters")
    void testHashWithSpecialCharacters() {
        // Arrange
        String specialContent = "Special chars: @#$%^&*()_+{}|:<>?";
        
        // Assert: Should process without error
        assertDoesNotThrow(() -> {
            String hash = hashCalculator.getMD5(specialContent);
            assertNotNull(hash, "Hash should not be null");
            assertFalse(hash.isEmpty(), "Hash should not be empty");
        }, "Should handle special characters");
    }
    
    @Test
    @DisplayName("Hash should handle Arabic/Urdu text")
    void testHashWithArabicText() {
        // Arrange
        String arabicContent = "مرحبا بك في برنامج تحرير النصوص";
        
        // Assert: Should process multilingual text
        assertDoesNotThrow(() -> {
            String hash = hashCalculator.getMD5(arabicContent);
            assertNotNull(hash, "Hash should not be null for Arabic text");
        }, "Should handle Arabic/Urdu text");
    }
}