package com.example.movilescomputacion2023a

import android.os.Parcelable
import android.os.Parcel

// BEntrenador.kt
class BEntrenador(
    var id: Int,
    var nombre: String?,
    var descripcion: String?,
): Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nombre)
        parcel.writeString(descripcion)
    }

    override fun toString(): String {
        return "${id} - ${nombre} - ${descripcion}"
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BEntrenador> {
        override fun createFromParcel(parcel: Parcel): BEntrenador {
            return BEntrenador(parcel)
        }

        override fun newArray(size: Int): Array<BEntrenador?> {
            return arrayOfNulls(size)
        }
    }

}