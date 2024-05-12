package com.study.disgroupportal.model.employee

import android.os.Parcel
import android.os.Parcelable

data class Duty(
    val dutyOne: String,
    val dutyTwo: String,
    val dutyThree: String,
    val dutyFour: String,
    val dutyFive: String,
) : Parcelable {
//    fun list() = listOf(
//        dutyOne, dutyTwo, dutyThree, dutyFour, dutyFive
//    )

    constructor() : this(
        dutyThree = "",
        dutyFour = "",
        dutyFive = "",
        dutyOne = "",
        dutyTwo = ""
    )

    constructor(parcel: Parcel) : this(
        dutyThree = parcel.readString() ?: "",
        dutyFour = parcel.readString() ?: "",
        dutyFive = parcel.readString() ?: "",
        dutyOne = parcel.readString() ?: "",
        dutyTwo = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(dutyOne)
        parcel.writeString(dutyTwo)
        parcel.writeString(dutyThree)
        parcel.writeString(dutyFour)
        parcel.writeString(dutyFive)
    }

    companion object CREATOR : Parcelable.Creator<Duty> {
        override fun createFromParcel(parcel: Parcel) = Duty(parcel)
        override fun newArray(size: Int) = arrayOfNulls<Duty>(size)
    }

    override fun describeContents() = 0
}