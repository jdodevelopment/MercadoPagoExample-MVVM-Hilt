package ar.com.jdodevelopment.ui.main.amount

class SubmitAmountCallback(val onItemClickListener: (amount: Double) -> Unit) {
    fun submitAmount(amount: Double) = onItemClickListener(amount)
}