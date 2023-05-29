package com.example.unimate

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class UserInfoActivity : AppCompatActivity() {
    private val currentUser = FirebaseAuth.getInstance().currentUser

    companion object {
        const val EXTRA_USER = "extra_user"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                startActivity(intent)
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun sendMatchRequest(userID : String,requesterID : String) {
        val db = FirebaseFirestore.getInstance()
        val matchRequestsCollection = db.collection("match_requests")

        val matchRequestData = hashMapOf(
            "senderId" to requesterID,
            "receiverId" to userID
        )

        matchRequestsCollection
            .add(matchRequestData)
            .addOnSuccessListener {
                Toast.makeText(this, "İstek Başarıyla Gönderildi!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Sistemden Kaynaklanan Bir Hata Sebebiyle İsyek Gönderilemedi! \nİnternet Bağlantınızı Kontrol Ediniz!", Toast.LENGTH_SHORT).show()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(android.R.drawable.arrow_down_float)

        val user = intent.getParcelableExtra<User>(EXTRA_USER)

        val nameText: TextView = findViewById(R.id.infoProfileName)
        val lastNameText: TextView = findViewById(R.id.infoProfileLastname)
        val bolumText: TextView = findViewById(R.id.infoProfileBolum)
        val mezuniyet: TextView = findViewById(R.id.infoProfileMezuniyet)
        val startDateText: TextView = findViewById(R.id.infoProfilGirisYili)
        val finishDateText: TextView = findViewById(R.id.infoProfileMezunYili)
        val emailText: TextView = findViewById(R.id.infoProfilemail)
        val durumText: TextView = findViewById(R.id.infoProfilDurum)
        val sureText: TextView = findViewById(R.id.infoProfilSure)
        val sureTextYazi: TextView = findViewById(R.id.infoProfilSureYazi)
        val kisiSayisiText: TextView = findViewById(R.id.infoProfilKisiSayi)
        val kisiSayisiYazi: TextView = findViewById(R.id.infoProfilKisiSayiYazi)
        val mesafeText: TextView = findViewById(R.id.infoProfilMesafe)
        val mesafeYazi: TextView = findViewById(R.id.infoProfilMesafeYazi)
        val telefonText: TextView = findViewById(R.id.infoProfilTelefon)

        val btnSendMatchRequest: Button = findViewById(R.id.talepGonderButonu)
        btnSendMatchRequest.setOnClickListener {
            val userID = user?.userID.toString()
            val requesterID  = currentUser?.uid.toString()
            if(userID == requesterID){
                Toast.makeText(this, "Kendi İlanınıza İstek Atamazsınız!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            sendMatchRequest(userID, requesterID) // Eşleşme talebi gönderme işlemleri
        }

        nameText.text = user?.isim
        lastNameText.text = user?.soyisim
        bolumText.text = user?.bolum
        mezuniyet.text = user?.mezuniyet
        emailText.text = user?.email
        startDateText.text = user?.girisYili
        finishDateText.text = user?.mezunYili
        durumText.text = user?.durum
        telefonText.text = user?.telefon
        sureText.text = user?.sure
        if (user?.kisiSayisi.toString() != "0") {
            kisiSayisiText.text = user?.kisiSayisi
        } else {
            kisiSayisiText.text = ""
            kisiSayisiYazi.text = ""
        }
        if (user?.mesafe.toString() != "0") {
            mesafeText.text = user?.mesafe
        } else {
            mesafeText.text = ""
            mesafeYazi.text = ""
        }
        if (user?.sure.toString() != "0") {
            sureText.text = user?.sure
        } else {
            sureText.text = ""
            sureTextYazi.text = ""
        }
    }
}
