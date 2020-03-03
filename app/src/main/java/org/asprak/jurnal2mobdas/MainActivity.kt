package org.asprak.jurnal2mobdas

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import kotlinx.android.synthetic.main.activity_main.*
import org.asprak.jurnal2mobdas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            bHitung.setOnClickListener {
                if (!TextUtils.isEmpty(e_pr.text.toString()) &&
                    !TextUtils.isEmpty(e_ass1.text.toString()) &&
                    !TextUtils.isEmpty(e_ass2.text.toString()) &&
                    !TextUtils.isEmpty(e_name.text.toString()) &&
                    isInRange(e_pr.text.toString().toFloat()) &&
                    isInRange(e_ass1.text.toString().toFloat()) &&
                    isInRange(e_ass2.text.toString().toFloat())
                ) {
                    var nilai_akhir = ((3 * e_pr.text.toString().toFloat()) / 10) +
                            ((3 * e_ass1.text.toString().toFloat()) / 10)
                    if (r_yes.isChecked) {
                        nilai_akhir += ((4 * e_ass2.text.toString().toFloat()) / 10)
                    }
                    tScore.text = getString(R.string.Total_Score, nilai_akhir.toString())
                    if (nilai_akhir > 50) {
                        iScore.setImageResource(R.drawable.ic_mood_blue_24dp)
                        iTscore.setTextColor(Color.parseColor("#FF3F51B5"))
                    } else {
                        iScore.setImageResource(R.drawable.ic_mood_bad_pink_24dp)
                        iTscore.setTextColor(Color.parseColor("#FFFF4081"))
                    }
                    if (nilai_akhir <= 40) {
                        iTscore.text = "E"
                    } else if (nilai_akhir <= 50) {
                        iTscore.text = "D"
                    } else if (nilai_akhir <= 60) {
                        iTscore.text = "C"
                    } else if (nilai_akhir <= 65) {
                        iTscore.text = "BC"
                    } else if (nilai_akhir <= 70) {
                        iTscore.text = "B"
                    } else if (nilai_akhir <= 80) {
                        iTscore.text = "AB"
                    } else if (nilai_akhir <= 100) {
                        iTscore.text = "A"
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.warning),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            bReset.setOnClickListener {
                iTscore.text = ""
                iScore.setImageResource(0)
                tScore.text = ""
                e_name.text.clear()
                eName.requestFocus()
                e_ass1.text.clear()
                e_ass2.text.clear()
                e_pr.text.clear()
                rYes.isChecked = true
            }
        }

    }

    fun isInRange(a: Float): Boolean {
        return a >= 0 && a <= 100
    }
}
