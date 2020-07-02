package ar.com.jdodevelopment.ui.main.payment_methods

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.jdodevelopment.R
import ar.com.jdodevelopment.data.model.PaymentMethod
import ar.com.jdodevelopment.databinding.ItemPaymentMethodBinding
import ar.com.jdodevelopment.ui.main.payment_methods.PaymentMethodAdapter.PaymentMethodViewHolder
import ar.com.jdodevelopment.ui.shared.adapter.OnItemClickListener

class PaymentMethodAdapter(
    private val onItemClickListener: OnItemClickListener<PaymentMethod>
) : ListAdapter<PaymentMethod, PaymentMethodViewHolder>(PaymentMethodDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): PaymentMethodViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemPaymentMethodBinding.inflate(layoutInflater, viewGroup, false)
        return PaymentMethodViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: PaymentMethodViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bindTo(item, onItemClickListener)
    }


    /**
     * ViewHolder
     */
    class PaymentMethodViewHolder(private val binding: ItemPaymentMethodBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: PaymentMethod, onItemClickListener: OnItemClickListener<PaymentMethod>) {
            binding.setObject(item)
            binding.root.setOnClickListener { view: View ->
                onItemClickListener.onItemClick(view, item)
            }
        }
    }

    /**
     * Diff Callback
     */
    class PaymentMethodDiffCallback : DiffUtil.ItemCallback<PaymentMethod>() {

        override fun areItemsTheSame(oldItem: PaymentMethod, newItem: PaymentMethod): Boolean {
            return newItem.id.contentEquals(oldItem.id)
        }

        override fun areContentsTheSame(oldItem: PaymentMethod, newItem: PaymentMethod): Boolean {
            return false
        }

    }
}
