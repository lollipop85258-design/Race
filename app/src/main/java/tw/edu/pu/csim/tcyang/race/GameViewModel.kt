package tw.edu.pu.csim.tcyang.race

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {

    var screenWidthPx by mutableStateOf(0f)
        private set

    var screenHeightPx by mutableStateOf(0f)
        private set

    var gameRunning by mutableStateOf(false)

    var circleX by mutableStateOf(0f)
    var circleY by mutableStateOf(0f)

    val horse1 = Horse(initialY = 100)
    val horse2 = Horse(initialY = 300)
    val horse3 = Horse(initialY = 500)

    var winnerMessage by mutableStateOf("")


    fun SetGameSize(w: Float, h: Float) {
        screenWidthPx = w
        screenHeightPx = h
    }

    private fun ResetHorses() {
        horse1.HorseX = 0
        horse2.HorseX = 0
        horse3.HorseX = 0
        winnerMessage = ""
    }


    fun StartGame() {
        circleX = 100f
        circleY = screenHeightPx - 100f

        gameRunning = true
        ResetHorses()

        viewModelScope.launch {
            val finishLine = screenWidthPx - 300

            while (gameRunning) {
                delay(100)

                circleX += 10
                if (circleX >= screenWidthPx - 100){
                    circleX = 100f
                }

                if (winnerMessage.isEmpty()) {
                    horse1.Run()
                    horse2.Run()
                    horse3.Run()

                    val winnerNo = when {
                        horse1.HorseX >= finishLine -> 1
                        horse2.HorseX >= finishLine -> 2
                        horse3.HorseX >= finishLine -> 3
                        else -> 0
                    }

                    if (winnerNo > 0) {
                        winnerMessage = "第${winnerNo}馬獲勝"

                        delay(2000)
                        ResetHorses()
                    }
                }
            }
        }
    }

    fun MoveCircle(x: Float, y: Float) {
        circleX += x
        circleY += y
    }
}