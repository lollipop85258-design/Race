package tw.edu.pu.csim.tcyang.race

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(message: String, gameViewModel: GameViewModel) {

    val imageBitmaps = listOf(
        ImageBitmap.imageResource(R.drawable.horse0),
        ImageBitmap.imageResource(R.drawable.horse1),
        ImageBitmap.imageResource(R.drawable.horse2),
        ImageBitmap.imageResource(R.drawable.horse3)
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Yellow)
    ){
        Canvas (modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    gameViewModel.MoveCircle( dragAmount.x, dragAmount.y)
                }
            }
        ) {
            // 繪製圓形
            drawCircle(
                color = Color.Red,
                radius = 100f,
                center = Offset(gameViewModel.circleX, gameViewModel.circleY)
            )

            // *** 繪製第一匹馬 (horse1) ***
            drawImage(
                image = imageBitmaps[gameViewModel.horse1.HorseNo],
                dstOffset = IntOffset(gameViewModel.horse1.HorseX, gameViewModel.horse1.HorseY),
                dstSize = IntSize(300, 300)
            )

            // *** 繪製第二匹馬 (horse2) ***
            drawImage(
                image = imageBitmaps[gameViewModel.horse2.HorseNo],
                dstOffset = IntOffset(gameViewModel.horse2.HorseX, gameViewModel.horse2.HorseY),
                dstSize = IntSize(300, 300)
            )

            // *** 繪製第三匹馬 (horse3) ***
            drawImage(
                image = imageBitmaps[gameViewModel.horse3.HorseNo],
                dstOffset = IntOffset(gameViewModel.horse3.HorseX, gameViewModel.horse3.HorseY),
                dstSize = IntSize(300, 300)
            )
        }


        // 顯示螢幕尺寸和遊戲標題 (靠左上角)
        Text(
            text = message + gameViewModel.screenWidthPx.toString() + "*"
                    + gameViewModel.screenHeightPx.toString(),
            modifier = Modifier.align(Alignment.TopStart)
        )

        // *** 顯示獲勝訊息 (置中) ***
        if (gameViewModel.winnerMessage.isNotEmpty()) {
            Text(
                text = gameViewModel.winnerMessage,
                fontSize = 50.sp,
                color = Color.Blue,
                modifier = Modifier.align(Alignment.Center)
            )
        }


        // 遊戲開始按鈕 (靠底部置中)
        Button(
            onClick = {
                gameViewModel.StartGame()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ){
            Text("遊戲開始")
        }
    }
}