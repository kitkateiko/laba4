package com.example.laba4

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button;
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    /*private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0

    private val isAnswered = BooleanArray(questionBank.size) { false }

    private var correctAnswersCount = 0
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //val quizViewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
       // Log.d(TAG, "Got a QuizViewModel:$quizViewModel")

        trueButton = findViewById(R.id.true_button)
        prevButton = findViewById(R.id.prev_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }
        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)        }
        nextButton.setOnClickListener {
            if (quizViewModel.currentIndex < quizViewModel.questionBank.size - 1) {
                quizViewModel.moveToNext()
                updateQuestion()
            } else {
                showScore() // Показываем результат, если это последний вопрос
            }
        }
        questionTextView.setOnClickListener(){
            if (quizViewModel.currentIndex < quizViewModel.questionBank.size - 1) {
                quizViewModel.moveToNext()
                updateQuestion()

                updateQuestion()
            } else {
                showScore() // Показываем результат, если это последний вопрос
            }
        }
        prevButton.setOnClickListener(){
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        updateQuestion()


    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG,
            "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,
            "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG,
            "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG,
            "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,
            "onDestroy() called")
    }
    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
        if (quizViewModel.isAnswered[quizViewModel.currentIndex]) {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        } else {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            quizViewModel.correctAnswersCount++
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        trueButton.isEnabled = false
        falseButton.isEnabled = false
        quizViewModel.isAnswered[quizViewModel.currentIndex] = true
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }
    private fun showScore() {
        val totalQuestions = quizViewModel.questionBank.size
        val scorePercentage = (quizViewModel.correctAnswersCount * 100) / totalQuestions // Вычисляем процент правильных ответов

        Toast.makeText(this, "Ваш результат: $scorePercentage%", Toast.LENGTH_LONG).show()

        // Сброс состояния для новой игры (если нужно)
        resetGame()
    }
    private fun resetGame() {
        quizViewModel.currentIndex = 0
        quizViewModel.correctAnswersCount = 0
        quizViewModel.isAnswered.fill(false) // Сбрасываем состояние всех вопросов на "не отвечено"

        updateQuestion() // Обновляем вопрос для новой игры
    }

}