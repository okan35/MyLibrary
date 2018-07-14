package com.okan.pc.mylbrary.feature

import android.os.Parcel
import android.os.Parcelable


data class Book(var barcode: Long) /*: Parcelable {
    constructor(parcel: Parcel) : this(parcel.readLong())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(barcode)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}
*/



