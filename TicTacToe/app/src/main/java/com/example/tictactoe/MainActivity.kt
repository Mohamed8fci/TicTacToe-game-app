package com.example.tictactoe

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random as Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {

        val butSelected = view as Button

        var cellId = 0
        when (butSelected.id) {
            R.id.but1 -> cellId = 1
            R.id.but2 -> cellId = 2
            R.id.but3 -> cellId = 3
            R.id.but4 -> cellId = 4
            R.id.but5 -> cellId = 5
            R.id.but6 -> cellId = 6
            R.id.but7 -> cellId = 7
            R.id.but8 -> cellId = 8
            R.id.but9 -> cellId = 9
        }
        //Log.d("buClick:",butSelected.id.toString())
        //Log.d("buClick: cellId",cellId.toString())

        playgame(cellId, butSelected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1
    fun playgame(cellId: Int, butSelected: Button) {
        if (activePlayer == 1) {
            butSelected.text = "X"
            butSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            autoPlay()
        } else {
            butSelected.text = "O"
            butSelected.setBackgroundResource(R.color.darkgreen)
            player2.add(cellId)
            activePlayer = 1
        }
        checkWinner()
    }

    fun checkWinner() {
        var winner = -1

        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }

        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }

        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }

        //col1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }

        //col2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }

        //col3
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        if (winner == 1) {
            player1WinsCount=+1
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            restartGame()
        } else if (winner == 2) {
            player2WinsCount=+2
            Toast.makeText(this, "player 2 win the game", Toast.LENGTH_LONG).show()
            restartGame()
        }
    }

    fun autoPlay() {
        var emptyCells = ArrayList<Int>()

        for (cellId in 1..9) {
            if (!player1.contains(cellId) || player2.contains(cellId)) {
                emptyCells.add(cellId)
            }
        }

        if (emptyCells.size==0){
            restartGame()
        }

        val r = java.util.Random()
        val randIndex = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randIndex]

        var butSelected: Button?
        butSelected = when (cellId) {
            1 -> but1
            2 -> but2
            3 -> but3
            4 -> but4
            5 -> but5
            6 -> but6
            7 -> but7
            8 -> but8
            9 -> but9
            else -> {
                but1
            }
        }
        playgame(cellId,butSelected)
    }


    var player1WinsCount=0
    var player2WinsCount=0

    fun restartGame(){
        activePlayer=1
        player1.clear()
        player2.clear()

        for (cellId in 1..9){

            var butSelected: Button?
            butSelected = when (cellId) {
                1 -> but1
                2 -> but2
                3 -> but3
                4 -> but4
                5 -> but5
                6 -> but6
                7 -> but7
                8 -> but8
                9 -> but9
                else -> {
                    but1
                }
            }

            butSelected.setText("")
            butSelected.setBackgroundResource(R.color.whitebu)

        }
        Toast.makeText(this,"player1: $player1WinsCount,player2: $player2WinsCount",Toast.LENGTH_LONG).show()
    }
}