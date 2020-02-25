package org.d3if0110.praassessment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.d3if0110.praassessment.databinding.FragmentSegitigaBinding
import java.text.DecimalFormat
import kotlin.math.pow
import kotlin.math.sqrt


/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaFragment : Fragment() {
    private lateinit var binding: FragmentSegitigaBinding
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_segitiga, container, false)
        // Inflate the layout for this fragment
        binding.btnHitung.setOnClickListener { cekInputan() }
        return binding.root
    }

    private fun hitung() {
        val decimalFormat = DecimalFormat("#.##")
        val alas = binding.textAlas.text.toString().toDouble()
        val tinggi = binding.textTinggi.text.toString().toDouble()
        val sisiMiring = sqrt(alas.pow(2) * tinggi.pow(2))
        luas = (alas * tinggi) / 2
        keliling = alas + tinggi + sisiMiring

        binding.tvHasilLuas.text = decimalFormat.format(luas).toString()
        binding.tvHasilKeliling.text = decimalFormat.format(keliling).toString()
    }

    private fun cekInputan() {
        when {
            binding.textAlas.text.isEmpty() -> {
                Toast.makeText(activity, "Alas wajib diidi", Toast.LENGTH_SHORT).show()
            }
            binding.textTinggi.text.isEmpty() -> {
                Toast.makeText(activity, "Tinggi wajib diisi", Toast.LENGTH_SHORT).show()
            }
            else -> hitung()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luas", luas)
        outState.putDouble("keliling", keliling)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            val decimalFormat = DecimalFormat("#.##")
            luas = savedInstanceState.getDouble("luas")
            keliling = savedInstanceState.getDouble("keliling")

            binding.tvHasilLuas.text = decimalFormat.format(luas).toString()
            binding.tvHasilKeliling.text = decimalFormat.format(keliling).toString()
        }
        super.onViewStateRestored(savedInstanceState)
    }
}
