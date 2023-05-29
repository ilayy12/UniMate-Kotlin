package com.example.unimate

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class RequestsActivity : AppCompatActivity() {
    private lateinit var requestrecyclerView: RecyclerView
    private lateinit var adapter: RequestRecycleViewAdapter
    private var matchRequests: MutableList<Request> = mutableListOf()
    private val currentUser = FirebaseAuth.getInstance().currentUser
    private val currentUserId  = currentUser?.uid.toString()
    val db = FirebaseFirestore.getInstance()
    private lateinit var user: User

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
    fun goToDetails(user: User){
        val intent = Intent(this, UserInfoActivity::class.java)
        intent.putExtra(UserInfoActivity.EXTRA_USER, user)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requests)
        matchRequests = mutableListOf()
        requestrecyclerView = findViewById(R.id.recycleRequest)
        adapter = RequestRecycleViewAdapter(matchRequests)
        requestrecyclerView.adapter = adapter

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(android.R.drawable.arrow_down_float)

        val layoutManager = LinearLayoutManager(this)
        requestrecyclerView.layoutManager = layoutManager
        requestrecyclerView.setHasFixedSize(true)
        adapter = RequestRecycleViewAdapter(matchRequests)
        adapter.setOnItemClickListener(object : RequestRecycleViewAdapter.OnItemClickListener {
            override fun onItemClick(request: Request) {
                db.collection("users").document(request.userID.toString()).get().addOnSuccessListener { result ->
                    val isim =  result.getString("isim") ?: ""
                    val soyisim = result.getString("soyisim") ?: ""
                    val email = result.getString("email") ?: ""
                    val girisYili = result.getString("girisYili") ?: ""
                    val mezunYili = result.getString("mezunYili") ?: ""
                    val mezuniyet = result.getString("mezuniyet") ?: ""
                    val bolum = result.getString("bolum") ?: ""
                    val durum = result.getString("durum") ?: ""
                    val kisiSayisi = result.getString("kisiSayisi") ?: "Fark Etmez"
                    val mesafe = result.getString("mesafe") ?: "Fark Etmez"
                    val sure = result.getString("sure") ?: ""
                    val telefon = result.getString("telefon") ?: ""
                    val userID = result.getString("userID") ?: ""
                    user = User(isim, soyisim, email, girisYili, mezunYili, mezuniyet, bolum, durum, kisiSayisi, mesafe, sure, telefon, userID)
                    goToDetails(user)
                }
            }
        })
        requestrecyclerView.adapter = adapter

        db.collection("match_requests")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val senderID =  document.getString("senderID") ?: ""
                    val receiverID = document.getString("receiverID") ?: ""
                    if(receiverID == currentUserId){
                        db.collection("users").document(senderID).get().addOnSuccessListener {
                            if(it != null){
                                val requester = it.toObject<User>()
                                if(requester != null){
                                    val isim =  document.getString("isim") ?: ""
                                    val soyisim = document.getString("soyisim") ?: ""
                                    val email = document.getString("email") ?: ""
                                    val bolum = document.getString("bolum") ?: ""
                                    val telefon = document.getString("telefon") ?: ""
                                    val userID = document.getString("userID") ?: ""
                                    val reqInfo  = Request(isim,soyisim,email, bolum, telefon,userID)
                                    matchRequests.add(reqInfo)
                                }else {
                                    Log.d(ContentValues.TAG,"cekemedim")
                                }
                            }
                        }.addOnFailureListener {
                            Log.d(ContentValues.TAG,"bişiler olmadı")
                        }
                    }
                }
                // RecyclerView için item animasyonları ve yükseklik
                requestrecyclerView.itemAnimator = DefaultItemAnimator()
                requestrecyclerView.itemAnimator?.addDuration = 300L
                requestrecyclerView.itemAnimator?.removeDuration = 300L
                requestrecyclerView.itemAnimator?.moveDuration = 300L
                requestrecyclerView.itemAnimator?.changeDuration = 300L

                val itemCount = matchRequests.size
                requestrecyclerView.layoutParams.height = requestrecyclerView.resources.getDimensionPixelSize(R.dimen.recycle_item_height) * itemCount
                requestrecyclerView.requestLayout()
            }
            .addOnFailureListener {
                val defaultReq = Request(isim = "Aytuğ İlayda","Baldede", email = "test@test.com", bolum = "bilgisayar müh.", telefon = "555444", userID = "11111")  //daha eklencek
                matchRequests.add(defaultReq)

                // RecyclerView için item animasyonları ve yükseklik
                requestrecyclerView.itemAnimator = DefaultItemAnimator()
                requestrecyclerView.itemAnimator?.addDuration = 300L
                requestrecyclerView.itemAnimator?.removeDuration = 300L
                requestrecyclerView.itemAnimator?.moveDuration = 300L
                requestrecyclerView.itemAnimator?.changeDuration = 300L

                val itemCount = matchRequests.size
                requestrecyclerView.layoutParams.height = requestrecyclerView.resources.getDimensionPixelSize(R.dimen.recycle_item_height) * itemCount
                requestrecyclerView.requestLayout()
            }
    }

}