package com.example.laba4

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
    var currentIndex = 0
    var cheatscount = 0
    var correctAnswersCount = 0
     val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    val isAnswered = BooleanArray(questionBank.size) { false }
    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    fun moveToNext() { currentIndex = (currentIndex + 1) % questionBank.size
    }
    fun moveToPrev() { currentIndex = (currentIndex - 1 + questionBank.size) % questionBank.size}


}