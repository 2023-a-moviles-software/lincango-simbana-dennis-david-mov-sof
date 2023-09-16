package com.example.myapplication.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import java.util.Date

class Comidas(

    val id: Int?,
    val nombre: String?,
    val tipo: String?,
    val descripcion: String?,
    val calorias: Double?,
    val menuId: Int?,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun toString(): String {
        return nombre!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

        parcel.writeString(id.toString())
        parcel.writeString(nombre)
        parcel.writeString(tipo)
        parcel.writeString(descripcion)
        parcel.writeString(calorias.toString())
        parcel.writeString(menuId.toString())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Comidas> {
        override fun createFromParcel(parcel: Parcel): Comidas {
            return Comidas(parcel)
        }

        override fun newArray(size: Int): Array<Comidas?> {
            return arrayOfNulls(size)
        }
    }
}