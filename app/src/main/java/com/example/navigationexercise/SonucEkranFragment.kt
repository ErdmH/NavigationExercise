package com.example.navigationexercise

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.navigationexercise.databinding.FragmentSonucEkranBinding
import com.example.navigationexercise.nesneler.User
import java.util.Locale

class SonucEkranFragment : Fragment() {
    private lateinit var tasarim:FragmentSonucEkranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        tasarim =  FragmentSonucEkranBinding.inflate(inflater, container, false)

        val bundle:SonucEkranFragmentArgs by navArgs()
        val tahminEdilenSayi : Int = bundle.tahminSayi
        val randomSayi : Int = bundle.randomSayi
        var user:User = bundle.user

        Log.e("Debug-->", Locale.getDefault().language) //en,tr diye cihazın seçili olduğu dili string return eder

        tasarim.textViewRandomSayi.text = resources.getString(R.string.guessed_the_number)+"$randomSayi"
        tasarim.textViewTahminEdilenSayi.text = "Tahmin Edilen Sayı $tahminEdilenSayi"

        if (tahminEdilenSayi == randomSayi){
            tasarim.textViewKazandinKaybettin.text = "Kazandın"
        }else{
            user.kalanHak -=1
            tasarim.textViewKazandinKaybettin.text = "Yanlış. Kalan Hak = ${user.kalanHak}"
            if (user.kalanHak == 0){
                tasarim.textViewKazandinKaybettin.text = "Kaybettin"
                tasarim.buttonAnaEkran.visibility = View.VISIBLE
                tasarim.buttonTekrarDene.visibility = View.INVISIBLE
            }else{
                tasarim.buttonAnaEkran.visibility = View.INVISIBLE
                tasarim.buttonTekrarDene.visibility = View.VISIBLE
            }
        }
        tasarim.buttonAnaEkran.setOnClickListener {
            //******Geri Tuşuna Basma Fonksiyonu******
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        tasarim.buttonTekrarDene.setOnClickListener {
            var gecis = SonucEkranFragmentDirections.sonucEkrantoOyunEkranGecis(user = user)
            Navigation.findNavController(it).navigate(gecis)
        }

        return tasarim.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object :
        OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val yeniIntent = Intent(Intent.ACTION_MAIN)
                yeniIntent.addCategory(Intent.CATEGORY_HOME)
                yeniIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(yeniIntent)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}