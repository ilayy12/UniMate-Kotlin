package com.example.unimate

import android.os.Parcel
import android.os.Parcelable

data class User (
    val isim: String? = null,
    val soyisim: String? = null,
    val email: String? = null,
    val girisYili: String? = null,
    val mezunYili: String? = null,
    val mezuniyet: String? = null,
    val bolum: String? = null,
    val durum: String? = null,
    val kisiSayisi: String? = null,
    val mesafe: String? = null,
    val sure: String? = null,
    val telefon: String? = null,
    val userID: String? = null,
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isim)
        parcel.writeString(soyisim)
        parcel.writeString(email)
        parcel.writeString(girisYili)
        parcel.writeString(mezunYili)
        parcel.writeString(mezuniyet)
        parcel.writeString(bolum)
        parcel.writeString(durum)
        parcel.writeString(kisiSayisi)
        parcel.writeString(mesafe)
        parcel.writeString(sure)
        parcel.writeString(telefon)
        parcel.writeString(userID)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}