package com.example.hostelreviewapp.ui.theme

/**
 * Data class representing a hostel review
 *
 * @param email Reviewer's email address
 * @param noOfDays Number of days the reviewer stayed
 * @param location Rating for location (1-5 scale)
 * @param valueForMoney Rating for value for money (1-5 scale)
 * @param staff Rating for staff (1-5 scale)
 */
data class Review(
    val email: String,
    val noOfDays: Int,
    val location: Int,
    val valueForMoney: Int,
    val staff: Int
) {
    /**
     * Calculates the overall review score based on weighted ratings
     *
     * Weight distribution (as per assignment requirements):
     * - Location: 40% (0.4)
     * - Value for Money: 30% (0.3)
     * - Staff: 30% (0.3)
     *
     * @return Double value representing the weighted average score
     */
    fun calculateOverallScore(): Double {
        // Define weights according to assignment requirements
        val locationWeight = 0.4  // Location is worth 40%
        val otherWeight = 0.3      // Other ratings (Value and Staff) are worth 30% each

        // Calculate weighted average
        return (location * locationWeight) +
                (valueForMoney * otherWeight) +
                (staff * otherWeight)
    }
}