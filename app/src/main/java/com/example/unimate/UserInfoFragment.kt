package com.example.unimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.TextView

class UserInfoFragment : Fragment() {

    companion object {
        private const val ARG_USER = "user"

        fun newInstance(user: User): UserInfoFragment {
            val args = Bundle()
            args.putParcelable(ARG_USER, user)
            val fragment = UserInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(ARG_USER)!!
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_info, container, false)

        val  nameText: TextView = view.findViewById(R.id.infoProfileName)
        val lastNameText:TextView = view.findViewById(R.id.infoProfileLastname)
        val bolumText:TextView = view.findViewById(R.id.infoProfileBolum)
        val mezuniyet: TextView = view.findViewById(R.id.infoProfileMezuniyet)
        val startDateText:TextView  = view.findViewById(R.id.infoProfilGirisYili)
        val finishDateText :TextView = view.findViewById(R.id.infoProfileMezunYili)
        val emailText:TextView  = view.findViewById(R.id.infoProfilemail)
        val durumText:TextView = view.findViewById(R.id.infoProfilDurum)
        val sureText: TextView = view.findViewById(R.id.infoProfilSure)
        val sureTextYazi: TextView = view.findViewById(R.id.infoProfilSureYazi)
        val kisiSayisiText:TextView = view.findViewById(R.id.infoProfilKisiSayi)
        val kisiSayisiYazi:TextView = view.findViewById(R.id.infoProfilKisiSayiYazi)
        val mesafeText:TextView = view.findViewById(R.id.infoProfilMesafe)
        val mesafeYazi:TextView = view.findViewById(R.id.infoProfilMesafeYazi)
        val telefonText:TextView = view.findViewById(R.id.infoProfilTelefon)

        nameText.text = user.isim
        lastNameText.text = user.soyisim
        bolumText.text = user.bolum
        mezuniyet.text = user.mezuniyet
        emailText.text = user.email
        startDateText.text = user.girisYili
        finishDateText.text = user.mezunYili
        durumText.text = user.durum
        telefonText.text = user.telefon
        sureText.text = user.sure
        if (user.kisiSayisi.toString() != "0"){
            kisiSayisiText.text = user.kisiSayisi
        }else {
            kisiSayisiText.text = ""
            kisiSayisiYazi.text = ""
        }
        if (user.mesafe.toString() != "0"){
            mesafeText.text = user.mesafe
        }else {
            mesafeText.text = ""
            mesafeYazi.text = ""
        }
        if (user.sure.toString() != "0"){
            sureText.text = user.sure
        }else {
            sureText.text = ""
            sureTextYazi.text = ""
        }

        return view
    }
}
