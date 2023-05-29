package com.example.unimate

import android.os.Parcel
import android.os.Parcelable

data class Request(
    val isim: String? = null,
    val soyisim: String? = null,
    val email: String? = null,
    val bolum: String? = null,
    val telefon: String? = null,
    val userID: String? = null,
): Parcelable {

    constructor(parcel: Parcel) : this(
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
        parcel.writeString(bolum)
        parcel.writeString(telefon)
        parcel.writeString(userID)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<Request> {
        override fun createFromParcel(parcel: Parcel): Request {
            return Request(parcel)
        }

        override fun newArray(size: Int): Array<Request?> {
            return arrayOfNulls(size)
        }
    }
}
