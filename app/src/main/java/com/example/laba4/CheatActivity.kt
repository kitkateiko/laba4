package com.example.laba4

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
private const val EXTRA_ANSWER_IS_TRUE = "com.example.laba4.answer_is_true"

class CheatActivity : AppCompatActivity() {
    private lateinit var answerTextView: TextView
    private lateinit var showAnswerButton: Button
    private lateinit var versionTextView: TextView
    val apiVersion = Build.VERSION.SDK_INT
    val androidVersion = Build.VERSION.RELEASE
    val versionInfo = "API Level: $apiVersion\nAndroid Version: $androidVersion"
    private var answerIsTrue = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        versionTextView = findViewById(R.id.version_text_view)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)

        showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
        }
        versionTextView.text = versionInfo

    }
    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext,
                CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE,
                    answerIsTrue)
            }
        }
    }

}