package edu.farmingdale.draganddropanim_demo
/**
 * Name: Himal Shrestha
 * Course: BCS 371 - Mobile Appliation Development
 * Prof: Alrajab
 * Week 12
 */
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.farmingdale.draganddropanim_demo.ui.theme.DragAndDropAnim_DemoTheme

// MainActivity serves as the entry point of the application.
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enables edge-to-edge display for the activity.
        enableEdgeToEdge()

        // Sets the content view using Jetpack Compose.
        setContent {
            DragAndDropAnim_DemoTheme {

                // Displays the DragAndDropBoxes composable, which is the core UI for the demo.
                DragAndDropBoxes()
            }
        }
    }
}



// This should be completed in a group setting
// ToDo x: Analyze the requirements for Individual Project 3
// ToDo x: Show the DragAndDropBoxes composable
// ToDo x: Change the circle to a rect
// ToDo x: Replace the command right with a image or icon
// ToDo x: Make this works in landscape mode only
// ToDo x: Rotate the rect around itself
// ToDo x: Move - translate the rect horizontally and vertically
// ToDo x: Add a button to reset the rect to the center of the screen
// ToDo 9: Enable certain animation based on the drop event (like up or down)
// ToDo 10: Make sure to commit for each one of the above and submit this individually


