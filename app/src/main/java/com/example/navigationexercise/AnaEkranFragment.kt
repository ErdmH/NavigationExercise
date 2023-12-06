package com.example.navigationexercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.navigationexercise.databinding.FragmentAnaEkranBinding
import com.example.navigationexercise.nesneler.User
import java.util.Locale

class AnaEkranFragment : Fragment() {

    private lateinit var tasarim: FragmentAnaEkranBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        tasarim = FragmentAnaEkranBinding.inflate(inflater, container, false)

        if(Locale.getDefault().language.toString() == "tr"){
            tasarim.imageView.setImageResource(R.drawable.yuvarlakturkiye)
        }else{
            tasarim.imageView.setImageResource(R.drawable.yuvarlakamerica)
        }

        tasarim.button.setOnClickListener {
            var user:User = User(tasarim.editTextUserName.text.toString(),5)
            val gecis = AnaEkranFragmentDirections.anaEkranToOyunEkranGecis(user = user)
            Navigation.findNavController(it).navigate(gecis)
        }

        return tasarim.root
    }
}