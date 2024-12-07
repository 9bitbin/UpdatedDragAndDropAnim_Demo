@file:OptIn(ExperimentalFoundationApi::class)

package edu.farmingdale.draganddropanim_demo

import android.content.ClipData
import android.content.ClipDescription
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.repeatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.mimeTypes
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.temporal.TemporalQueries.offset
@Composable
fun DragAndDropBoxes(modifier: Modifier = Modifier) {
    // Boolean state to toggle playing state for animations
    var isPlaying by remember { mutableStateOf(true) }

    // State to hold the current offset of the draggable object
    var isOffset by remember { mutableStateOf(IntOffset(130, 300)) }

    // Direction state to determine movement (e.g., "up")
    var direction by remember { mutableStateOf("up") }

    // Main container for the UI elements
    Column(modifier = Modifier.fillMaxSize()) {

        // Row containing drag-and-drop target boxes
        Row(
            modifier = modifier
                .fillMaxWidth()
                .weight(0.2f)
        ) {
            val boxCount = 4 // Number of target boxes
            var dragBoxIndex by remember {
                mutableIntStateOf(0) // State to track the active box
            }

            // Create multiple boxes using repeat
            repeat(boxCount) { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(10.dp)
                        .border(1.dp, Color.Black) // Add border to the box
                        .dragAndDropTarget(
                            shouldStartDragAndDrop = { event ->
                                event
                                    .mimeTypes()
                                    .contains(ClipDescription.MIMETYPE_TEXT_PLAIN) // Check for plain text MIME type
                            },
                            target = remember {
                                object : DragAndDropTarget {
                                    override fun onDrop(event: DragAndDropEvent): Boolean {
                                        isPlaying = !isPlaying // Toggle playing state on drop
                                        dragBoxIndex = index // Update the active box index
                                        return true
                                    }
                                }
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    // Animation visibility for the text inside the active box
                    this@Row.AnimatedVisibility(
                        visible = index == dragBoxIndex, // Show only for the active box
                        enter = scaleIn() + fadeIn(), // Entry animation
                        exit = scaleOut() + fadeOut() // Exit animation
                    ) {
                        Text(
                            text = "Right", // Text content
                            fontSize = 40.sp,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold,

                            modifier = Modifier
                                .fillMaxSize()
                                .dragAndDropSource {
                                    detectTapGestures(
                                        onLongPress = { offset ->
                                            startTransfer(
                                                transferData = DragAndDropTransferData(
                                                    clipData = ClipData.newPlainText(
                                                        "text", // Plain text data
                                                        ""
                                                    )
                                                )
                                            )
                                        }
                                    )
                                }
                        )
                    }
                }
            }
        }