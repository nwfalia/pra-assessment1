package org.d3if0110.praassessment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import org.d3if0110.praassessment.databinding.FragmentMainBinding
import org.d3if0110.praassessment.databinding.FragmentPersegiPanjangBinding



/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {
    private lateinit var binding: FragmentPersegiPanjangBinding
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_persegi_panjang, container, false)
        // Inflate the layout for this fragment
        binding.btnHitungPersegiPanjang.setOnClickListener { cekInputan() }
        return binding.root
    }

    private fun hitung() {
        val panjang = binding.textPanjang.text.toString().toDouble()
        val lebar = binding.textLebar.text.toString().toDouble()
        luas = panjang * lebar
        keliling = 2 * (panjang + lebar)

        binding.tvHasilLuas.text = "${luas}"
        binding.tvKeliling.text = "$keliling"
    }

    private fun cekInputan() {
        when {
            binding.textPanjang.text.isEmpty() -> {
                Toast.makeText(activity, "Panjang harus diisi", Toast.LENGTH_SHORT).show()
            }
            binding.textLebar.text.isEmpty() -> {
                Toast.makeText(activity, "Lebar harus diisi", Toast.LENGTH_SHORT).show()
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
            luas = savedInstanceState.getDouble("luas")
            keliling = savedInstanceState.getDouble("keliling")

            binding.tvHasilLuas.text = "${luas}"
            binding.tvKeliling.text = "$keliling"
        }
        super.onViewStateRestored(savedInstanceState)
    }
}
