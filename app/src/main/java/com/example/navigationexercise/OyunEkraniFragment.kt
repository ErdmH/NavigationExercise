package com.example.navigationexercise

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.navigationexercise.databinding.FragmentOyunEkraniBinding
import com.example.navigationexercise.nesneler.User
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout

class OyunEkraniFragment : Fragment() {

    private lateinit var tasarim:FragmentOyunEkraniBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim =  FragmentOyunEkraniBinding.inflate(inflater, container, false)

        val bundle:OyunEkraniFragmentArgs by navArgs()
        val random:Int = (1..10).random()
        var user:User = bundle.user
        Log.e("Mamamia","$user")

        tasarim.button2.setOnClickListener {
            if (tasarim.editTextTahminEdilenSayi.text.toString() != "") {
                var tahminEdilenSayi = tasarim.editTextTahminEdilenSayi.text.toString().toInt()
                var gecis = OyunEkraniFragmentDirections.oyunEkrantoSonucEkranGecis(user = user,tahminSayi = tahminEdilenSayi, randomSayi = random)
                Navigation.findNavController(it).navigate(gecis)
            }else{
                Toast.makeText(requireContext(), "Mamammia boşluk girme yoksa Pizza yok sana", Toast.LENGTH_LONG).show()
            }
        }

        return tasarim.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("Debug->","onAttach çalıştı")
        val callback: OnBackPressedCallback = object :
        OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                //your onbackpressed code
                Snackbar.make(tasarim.root,"Gari tuşu yazıldı", Snackbar.LENGTH_SHORT).show()
                Log.e("Debug->","Geri tuşu kodu yazıldı")
                //super.isEnabled = false
                //requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

}