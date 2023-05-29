package com.example.unimate

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class HomeFragment : Fragment() {

    private lateinit var adapter: RecycleViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var usersArrayList: MutableList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        usersArrayList = mutableListOf()
        recyclerView = view.findViewById(R.id.recycleHome)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = RecycleViewAdapter(usersArrayList)
        adapter.setOnItemClickListener(object : RecycleViewAdapter.OnItemClickListener {
            override fun onItemClick(user: User) {
                val intent = Intent(requireContext(), UserInfoActivity::class.java)
                intent.putExtra(UserInfoActivity.EXTRA_USER, user)
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter

        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val isim =  document.getString("isim") ?: ""
                    val soyisim = document.getString("soyisim") ?: ""
                    val email = document.getString("email") ?: ""
                    val girisYili = document.getString("girisYili") ?: ""
                    val mezunYili = document.getString("mezunYili") ?: ""
                    val mezuniyet = document.getString("mezuniyet") ?: ""
                    val bolum = document.getString("bolum") ?: ""
                    val durum = document.getString("durum") ?: ""
                    val kisiSayisi = document.getString("kisiSayisi") ?: "Fark Etmez"
                    val mesafe = document.getString("mesafe") ?: "Fark Etmez"
                    val sure = document.getString("sure") ?: ""
                    val telefon = document.getString("telefon") ?: ""
                    val userID = document.getString("userID") ?: ""
                    val userInfo = User(isim, soyisim, email, girisYili, mezunYili, mezuniyet, bolum, durum, kisiSayisi, mesafe, sure, telefon, userID)
                    usersArrayList.add(userInfo)
                }
                // RecyclerView için item animasyonları ve yükseklik
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.itemAnimator?.addDuration = 300L
                recyclerView.itemAnimator?.removeDuration = 300L
                recyclerView.itemAnimator?.moveDuration = 300L
                recyclerView.itemAnimator?.changeDuration = 300L

                val itemCount = usersArrayList.size
                recyclerView.layoutParams.height = recyclerView.resources.getDimensionPixelSize(R.dimen.recycle_item_height) * itemCount
                recyclerView.requestLayout()
            }
            .addOnFailureListener {
                val defaultUser = User(isim = "Aytuğ İlayda","Baldede", mezuniyet = "Lisans", durum = "Ev Arıyor", mesafe = "300", kisiSayisi = "Farketmez")  //daha eklencek
                usersArrayList.add(defaultUser)

                // RecyclerView için item animasyonları ve yükseklik
                recyclerView.itemAnimator = DefaultItemAnimator()
                recyclerView.itemAnimator?.addDuration = 300L
                recyclerView.itemAnimator?.removeDuration = 300L
                recyclerView.itemAnimator?.moveDuration = 300L
                recyclerView.itemAnimator?.changeDuration = 300L

                val itemCount = usersArrayList.size
                recyclerView.layoutParams.height = recyclerView.resources.getDimensionPixelSize(R.dimen.recycle_item_height) * itemCount
                recyclerView.requestLayout()
            }
    }
}