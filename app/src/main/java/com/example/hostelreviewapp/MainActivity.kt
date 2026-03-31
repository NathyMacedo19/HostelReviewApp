package com.example.hostelreviewapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hostelreviewapp.ui.theme.HostelReviewAppTheme
import com.example.hostelreviewapp.ui.theme.Review
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HostelReviewAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ReviewForm(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
/**
 * Main Composable function that displays the hostel review form
 * @param modifier Modifier for styling and layout
 */
@Composable
fun ReviewForm(modifier: Modifier = Modifier) {
    // --- State variables to hold user input --
    var email by remember { mutableStateOf("") } // Email field
    var noOfDays by remember { mutableStateOf("") } // Number of days stay
    var locationRating by remember { mutableStateOf("") } // Location rating (1-5)
    var valueForMoneyRating by remember { mutableStateOf("") } // Value for money rating (1-5)
    var staffRating by remember { mutableStateOf("") } // Staff rating (1-5)

    // --- State variables for validation errors ---
    var emailError by remember { mutableStateOf(false) } // Email validation error
    var daysError by remember { mutableStateOf(false) }     // Days validation error
    var locationError by remember { mutableStateOf(false) } // Location rating error
    var valueError by remember { mutableStateOf(false) } // Value for money error
    var staffError by remember { mutableStateOf(false) } // Staff rating error

    // --- State variable to display the calculated score ---
    var finalScore by remember { mutableStateOf<Double?>(null) }

    // --- UI Layout using Column to arrange elements vertically ---
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // --- TITLE SECTION with different color ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF6200EE) // Purple color
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "HOSTEL REVIEW",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(16.dp)
            )
        }

        // --- PERSONAL INFORMATION SECTION (Email and Days) ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFE1F5FE) // Light blue background
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                // Section title
                Text(
                    text = "Personal Information",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF01579B), // Dark blue
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Email input field
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = false
                    },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = emailError,
                    supportingText = {
                        if (emailError) Text(
                            "Please enter a valid email",
                            color = Color.Red
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6200EE),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFF6200EE)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Number of Days Stay input field
                OutlinedTextField(
                    value = noOfDays,
                    onValueChange = {
                        noOfDays = it
                        daysError = false
                    },
                    label = { Text("No of Days Stay") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = daysError,
                    supportingText = {
                        if (daysError) Text(
                            "Please enter a valid number",
                            color = Color.Red
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF6200EE),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFF6200EE)
                    )
                )
            }
        }

        // --- RATINGS SECTION (Location, Value, Staff) ---
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFF3E5F5) // Light purple background
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                // Section title with weight information
                Text(
                    text = "Ratings (1-5 scale)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A148C), // Dark purple
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Weight information
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Surface(
                        color = Color(0xFFB39DDB), // Light purple
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Location: 40%",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Surface(
                        color = Color(0xFFB39DDB),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Value: 30%",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Surface(
                        color = Color(0xFFB39DDB),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Staff: 30%",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Location rating input field
                OutlinedTextField(
                    value = locationRating,
                    onValueChange = {
                        locationRating = it
                        locationError = false
                    },
                    label = { Text("Location (1-5)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = locationError,
                    supportingText = {
                        if (locationError) Text(
                            "Please enter a number 1-5",
                            color = Color.Red
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4A148C),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFF4A148C)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Value for Money rating input field
                OutlinedTextField(
                    value = valueForMoneyRating,
                    onValueChange = {
                        valueForMoneyRating = it
                        valueError = false
                    },
                    label = { Text("Value for Money (1-5)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = valueError,
                    supportingText = {
                        if (valueError) Text(
                            "Please enter a number 1-5",
                            color = Color.Red
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4A148C),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFF4A148C)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Staff rating input field
                OutlinedTextField(
                    value = staffRating,
                    onValueChange = {
                        staffRating = it
                        staffError = false
                    },
                    label = { Text("Staff (1-5)") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = staffError,
                    supportingText = {
                        if (staffError) Text(
                            "Please enter a number 1-5",
                            color = Color.Red
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF4A148C),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFF4A148C)
                    )
                )
            }
        }

        // --- BUTTON SECTION ---
        Button(
            onClick = {
                // --- VALIDATION LOGIC ---
                // Check if email contains @ and . (simple email validation)
                val isValidEmail = email.contains("@") && email.contains(".")
                emailError = !isValidEmail

                // Check if days is a positive number
                val daysNum = noOfDays.toIntOrNull()
                daysError = daysNum == null || daysNum <= 0

                // Check if location rating is a number between 1-5
                val locationNum = locationRating.toIntOrNull()
                locationError = locationNum == null || locationNum !in 1..5

                // Check if value for money rating is a number between 1-5
                val valueNum = valueForMoneyRating.toIntOrNull()
                valueError = valueNum == null || valueNum !in 1..5

                // Check if staff rating is a number between 1-5
                val staffNum = staffRating.toIntOrNull()
                staffError = staffNum == null || staffNum !in 1..5

                // If there are no validation errors
                if (!emailError && !daysError && !locationError && !valueError && !staffError) {
                    // Create a Review object with the validated data
                    val review = Review(
                        email = email,
                        noOfDays = daysNum!!,
                        location = locationNum!!,
                        valueForMoney = valueNum!!,
                        staff = staffNum!!
                    )
                    // Calculate and store the overall score
                    finalScore = review.calculateOverallScore()

                    // Reset all form fields to empty
                    email = ""
                    noOfDays = ""
                    locationRating = ""
                    valueForMoneyRating = ""
                    staffRating = ""
                } else {
                    // Hide previous score if new submission has errors
                    finalScore = null
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE), // Purple
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "SEND REVIEW",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // --- RESULTS SECTION (Display the calculated score) ---
        if (finalScore != null) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFC8E6C9) // Light green
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "REVIEW SUBMITTED!",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20) // Dark green
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Display score with different color based on value
                    val scoreColor = when {
                        finalScore!! >= 4.5 -> Color(0xFF2E7D32) // Dark green for excellent
                        finalScore!! >= 3.5 -> Color(0xFFF57C00) // Orange for good
                        else -> Color(0xFFC62828) // Red for poor
                    }

                    Text(
                        text = "Overall Score: %.2f".format(finalScore!!),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = scoreColor
                    )

                    // Visual representation of score
                    LinearProgressIndicator(
                        progress = { (finalScore!! / 5f).toFloat() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp),
                        color = scoreColor,
                        trackColor = Color.LightGray
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReviewPreview() {
    HostelReviewAppTheme {
        ReviewForm()
    }
}