package ar.com.jdodevelopment.ui.main.banks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ar.com.jdodevelopment.data.model.CardIssuer
import ar.com.jdodevelopment.databinding.ItemCardIssuerBinding
import ar.com.jdodevelopment.ui.shared.adapter.OnItemClickListener


class BanksAdapter(
    private val onItemClickListener: OnItemClickListener<CardIssuer>
) : ListAdapter<CardIssuer, BanksAdapter.BanksViewHolder>(BanksDiffCallback()) {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): BanksViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = ItemCardIssuerBinding.inflate(layoutInflater, viewGroup, false)
        return BanksViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: BanksViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bindTo(item, onItemClickListener)
    }


    /**
     * ViewHolder
     */
    class BanksViewHolder(private val binding: ItemCardIssuerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(item: CardIssuer, onItemClickListener: OnItemClickListener<CardIssuer>) {
            binding.setObject(item)
            binding.root.setOnClickListener { view: View ->
                onItemClickListener.onItemClick(view, item)
            }
        }
    }

    /**
     * Diff Callback
     */
    class BanksDiffCallback : DiffUtil.ItemCallback<CardIssuer>() {

        override fun areItemsTheSame(oldItem: CardIssuer, newItem: CardIssuer): Boolean {
            return newItem.id.contentEquals(oldItem.id)
        }

        override fun areContentsTheSame(oldItem: CardIssuer, newItem: CardIssuer): Boolean {
            return false
        }

    }
}
