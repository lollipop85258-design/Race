package tw.edu.pu.csim.tcyang.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize

@Composable
fun GameScreen(message: String, gameViewModel: GameViewModel) {

    // NOTE: R.drawable.horse0 must exist in your Android project resources.
    val imageBitmap = ImageBitmap.imageResource(R.drawable.horse0)

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
    ){
        Canvas (modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                // Detects drag gestures, updates ViewModel on every change
                detectDragGestures { change, dragAmount ->
                    change.consume() // Tells the system the event has been handled
                    gameViewModel.MoveCircle( dragAmount.x, dragAmount.y)
                }
            }
        ) {
            // 繪製圓形 (Drawing the circle which is draggable)
            drawCircle(
                color = Color.Red,
                radius = 100f,
                center = Offset(gameViewModel.circleX, gameViewModel.circleY)
            )

            // Drawing the horse image
            drawImage(
                image = imageBitmap,
                dstOffset = IntOffset(0, 100),
                dstSize = IntSize(300, 300)
            )
        }
    }
}