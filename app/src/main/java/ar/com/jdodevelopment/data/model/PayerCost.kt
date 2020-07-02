package ar.com.jdodevelopment.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PayerCost(
    @SerializedName("installments") var installments: Int,
    @SerializedName("total_amount") val totalAmount: Double,
    @SerializedName("installment_amount") val installmentAmount: Double,
    @SerializedName("recommended_message") val recommendedMessage: String
) : Parcelable
// TODO: rest of fields