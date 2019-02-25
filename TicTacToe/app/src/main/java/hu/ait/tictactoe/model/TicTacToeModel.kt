package hu.ait.tictactoe.model

object TicTacToeModel {

    public val EMPTY: Short = 0
    public val CIRCLE: Short = 1
    public val CROSS: Short = 2

    private val model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY)
    )


    public var nextPlayer = CIRCLE

    public fun resetModel() {
        for (i in 0..2) {
            for (j in 0..2) {
                model[i][j] = EMPTY
            }
        }
    }

    public fun setFieldContent(x: Int, y: Int, player: Short) {
        model[x][y] = player
    }

    public fun getFieldContent(x: Int, y: Int) = model[x][y]


    public fun switchPlayer() {
        nextPlayer = if (nextPlayer == CROSS) {
            CIRCLE
        } else {
            CROSS
        }
    }

}