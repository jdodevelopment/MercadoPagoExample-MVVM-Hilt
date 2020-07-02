package ar.com.jdodevelopment.ui.main.installments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.jdodevelopment.data.model.PayerCost
import ar.com.jdodevelopment.databinding.ItemPayerCostsBinding
import ar.com.jdodevelopment.ui.shared.adapter.OnItemClickListener


class PayerCostsAdapter(
    private val onItemClickListener: OnItemClickListener<PayerCost>
) : ListAdapter<PayerCost, PayerCostsAdapter.PayerCostsViewHolder>(PayerCostDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): PayerCostsViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemPayerCostsBinding.inflate(layoutInflater, viewGroup, false)
        return PayerCostsViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: PayerCostsViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bindTo(item, onItemClickListener)
    }


    /**
     * ViewHolder
     */
    class PayerCostsViewHolder(private val binding: ItemPayerCostsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: PayerCost, onItemClickListener: OnItemClickListener<PayerCost>) {
            binding.setObject(item)
            binding.root.setOnClickListener { view: View ->
                onItemClickListener.onItemClick(view, item)
            }
        }
    }

    /**
     * Diff Callback
     */
    class PayerCostDiffCallback : DiffUtil.ItemCallback<PayerCost>() {

        override fun areItemsTheSame(oldItem: PayerCost, newItem: PayerCost): Boolean {
            return newItem.installments == oldItem.installments
        }

        override fun areContentsTheSame(oldItem: PayerCost, newItem: PayerCost): Boolean {
            return false
        }

    }
}

