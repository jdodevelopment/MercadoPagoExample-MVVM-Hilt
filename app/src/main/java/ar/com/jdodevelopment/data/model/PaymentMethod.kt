package ar.com.jdodevelopment.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentMethod(
    var id: String,
    var name: String,
    var thumbnail: String
) : Parcelable
// TODO: rest of fields