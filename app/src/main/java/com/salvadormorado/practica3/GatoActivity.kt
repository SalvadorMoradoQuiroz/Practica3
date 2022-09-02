package com.salvadormorado.practica3

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class GatoActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button_00: Button
    private lateinit var button_01: Button
    private lateinit var button_02: Button
    private lateinit var button_10: Button
    private lateinit var button_11: Button
    private lateinit var button_12: Button
    private lateinit var button_20: Button
    private lateinit var button_21: Button
    private lateinit var button_22: Button
    private lateinit var button_Reiniciar: Button
    private lateinit var textView_PuntuacionTotal: TextView
    private lateinit var textView_PuntuacionX: TextView
    private lateinit var textView4_PuntuacionO: TextView
    private lateinit var textView_Empates: TextView

    private lateinit var progressBar: ProgressBar
    private var flagAux: Boolean? = false
    private var turno: Boolean? = false //False = Turno de jugador -  True = Turno de máquina
    private var gato = Array(3) { Array<String?>(3) { null } }
    private var contadorMovimientos: Int = 0
    private var puntuacionX: Int = 0
    private var puntuacionO: Int = 0
    private var empate: Int = 0
    private var buttonRamdon: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gato)

        setListenersButtons()
        flagAux = false
        textView_PuntuacionTotal = findViewById(R.id.textView_PuntuacionTotal)
        textView_PuntuacionX = findViewById(R.id.textView_PuntuacionX)
        textView4_PuntuacionO = findViewById(R.id.textView4_PuntuacionO)
        textView_Empates = findViewById(R.id.textView_Empates)
        progressBar = findViewById(R.id.progressBar)

        var myCountDownTimer = MyCountDownTimer(5000, 100)
        myCountDownTimer.start()
    }

    fun setListenersButtons() {
        button_00 = findViewById(R.id.button_00)
        button_01 = findViewById(R.id.button_01)
        button_02 = findViewById(R.id.button_02)
        button_10 = findViewById(R.id.button_10)
        button_11 = findViewById(R.id.button_11)
        button_12 = findViewById(R.id.button_12)
        button_20 = findViewById(R.id.button_20)
        button_21 = findViewById(R.id.button_21)
        button_22 = findViewById(R.id.button_22)
        button_Reiniciar = findViewById(R.id.button_Reiniciar)

        button_00.setOnClickListener(this)
        button_01.setOnClickListener(this)
        button_02.setOnClickListener(this)
        button_10.setOnClickListener(this)
        button_11.setOnClickListener(this)
        button_12.setOnClickListener(this)
        button_20.setOnClickListener(this)
        button_21.setOnClickListener(this)
        button_22.setOnClickListener(this)
        button_Reiniciar.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        println(v!!.id)
        when (v!!.id) {
            R.id.button_00 -> {
                gato[0][0] = setLetterButton(button_00)
                validateWinner()
            }
            R.id.button_01 -> {
                gato[0][1] = setLetterButton(button_01)
                validateWinner()
            }
            R.id.button_02 -> {
                gato[0][2] = setLetterButton(button_02)
                validateWinner()
            }
            R.id.button_10 -> {
                gato[1][0] = setLetterButton(button_10)
                validateWinner()
            }
            R.id.button_11 -> {
                gato[1][1] = setLetterButton(button_11)
                validateWinner()
            }
            R.id.button_12 -> {
                gato[1][2] = setLetterButton(button_12)
                validateWinner()
            }
            R.id.button_20 -> {
                gato[2][0] = setLetterButton(button_20)
                validateWinner()
            }
            R.id.button_21 -> {
                gato[2][1] = setLetterButton(button_21)
                validateWinner()
            }
            R.id.button_22 -> {
                gato[2][2] = setLetterButton(button_22)
                validateWinner()
            }
            R.id.button_Reiniciar -> {
                restart()
            }
        }
    }

    fun setLetterButton(button: Button): String {
        var opc = ""
        if (!flagAux!!) {
            button.text = "X"
            flagAux = true
            opc = "X"
        } else {
            button.text = "O"
            flagAux = false
            opc = "O"
        }
        button.isEnabled = false
        contadorMovimientos++
        return opc
    }

    fun setLetterButtonAuto() {
        while (true) {
            var num = (0 until 9).random()
            println(num)
            when (num) {
                0 -> {
                    if (button_00!!.text != null) {
                        setLetterButton(button_00)
                        break
                    }
                }
                1 -> {
                    if (button_01!!.text != null) {
                        setLetterButton(button_01)
                        break
                    }
                }
                2 -> {
                    if (button_02!!.text != null) {
                        setLetterButton(button_02)
                        break
                    }
                }
                3 -> {
                    if (button_10!!.text != null) {
                        setLetterButton(button_10)
                        break
                    }
                }
                4 -> {
                    if (button_11!!.text != null) {
                        setLetterButton(button_11)
                        break
                    }
                }
                5 -> {
                    if (button_12!!.text != null) {
                        setLetterButton(button_12)
                        break
                    }
                }
                6 -> {
                    if (button_20!!.text != null) {
                        setLetterButton(button_20)
                        break
                    }
                }
                7 -> {
                    if (button_21!!.text != null) {
                        setLetterButton(button_21)
                        break
                    }
                }
                8 -> {
                    if (button_22!!.text != null) {
                        setLetterButton(button_22)
                        break
                    }
                }
            }
        }
    }

    fun restart() {
        button_00.isEnabled = true
        button_01.isEnabled = true
        button_02.isEnabled = true
        button_10.isEnabled = true
        button_11.isEnabled = true
        button_12.isEnabled = true
        button_20.isEnabled = true
        button_21.isEnabled = true
        button_22.isEnabled = true

        button_00.text = ""
        button_01.text = ""
        button_02.text = ""
        button_10.text = ""
        button_11.text = ""
        button_12.text = ""
        button_20.text = ""
        button_21.text = ""
        button_22.text = ""

        for (i in (0 until 3)) {
            for (j in (0 until 3)) {
                gato[i][j] = null
            }
        }

        contadorMovimientos = 0
        flagAux = false
    }

    fun validateWinner() {
        var aux = ""
        if (contadorMovimientos >= 5) {
            //Validaciones horizontales
            if ((gato[0][0] == gato[0][1]) && (gato[0][1] == gato[0][2])) {
                aux = gato[0][0].toString()
            } else if ((gato[1][0] == gato[1][1]) && (gato[1][1] == gato[1][2])) {
                aux = gato[1][0].toString()
            } else if ((gato[2][0] == gato[2][1]) && (gato[2][1] == gato[2][2])) {
                aux = gato[2][0].toString()
            }
            //Validaciones verticales
            else if ((gato[0][0] == gato[1][0]) && (gato[1][0] == gato[2][0])) {
                aux = gato[0][0].toString()
            } else if ((gato[0][1] == gato[1][1]) && (gato[1][1] == gato[2][1])) {
                aux = gato[0][1].toString()
            } else if ((gato[0][2] == gato[1][2]) && (gato[1][2] == gato[2][2])) {
                aux = gato[0][2].toString()
            }
            //Validaciones en diagonal
            else if ((gato[0][0] == gato[1][1]) && (gato[1][1] == gato[2][2])) {
                aux = gato[0][0].toString()
            } else if ((gato[2][0] == gato[1][1]) && (gato[1][1] == gato[0][2])) {
                aux = gato[2][0].toString()
            }

            if (aux == "X" || aux == "O") {
                showAlertDialogWinner(aux)
            } else if (contadorMovimientos == 9) {
                empate++
                showAlertDialogWinner("Empate")
            }
        }
    }

    fun showAlertDialogWinner(winner: String) {
        if (winner != "Empate") {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Ganador ${winner}")
                .setMessage("El ganador es el jugador ${winner}")
                .setPositiveButton("Aceptar") { view, _ ->
                    updateScore(winner)
                    restart()
                    view.dismiss()
                }
                .setCancelable(false)
                .create()
            dialog.show()
        } else {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Empate")
                .setMessage("Ningún jugador gano, es un empate.")
                .setPositiveButton("Aceptar") { view, _ ->
                    updateScore(winner)
                    restart()
                    view.dismiss()
                }
                .setCancelable(false)
                .create()
            dialog.show()
        }
    }

    fun updateScore(winner: String) {
        if (winner == "X") {
            puntuacionX++
        } else {
            puntuacionO++
        }
        textView_PuntuacionTotal.text = "Puntuación total: ${puntuacionX + puntuacionO + empate}"
        textView_PuntuacionX.text = "Puntuación X: ${puntuacionX}"
        textView4_PuntuacionO.text = "Puntuación O: ${puntuacionO}"
        textView_Empates.text = "Empate: ${empate}"
    }

    inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long) :
        CountDownTimer(millisInFuture, countDownInterval) {

        override fun onTick(millisUntilFinished: Long) {
            //Se debe modificar, nomas falta la logica para los turnos con el tiempo
            if (turno == false) {
                setLetterButtonAuto()
                turno = true
            }
            val progress = (millisUntilFinished / 50).toInt()
            progressBar.setProgress(progressBar.getMax() - progress)
        }

        override fun onFinish() {
            //finish()
            Toast.makeText(applicationContext, "Finish progressBar.", Toast.LENGTH_SHORT).show()
        }
    }
}