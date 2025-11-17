package tw.edu.pu.csim.tcyang.race

class Horse(initialY: Int) {
    var HorseX = 0
    var HorseY = initialY
    var HorseNo = 0

    fun Run(){
        HorseNo ++
        if (HorseNo > 3){
            HorseNo = 0
        }

        HorseX += (10..30).random()
    }
}