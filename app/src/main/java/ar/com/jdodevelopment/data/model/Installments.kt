package ar.com.jdodevelopment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Installments(
    @SerializedName("id") val id: String,
    @SerializedName("payer_costs") var payerCosts: List<PayerCost>
) : Parcelable
// TODO: rest of fields