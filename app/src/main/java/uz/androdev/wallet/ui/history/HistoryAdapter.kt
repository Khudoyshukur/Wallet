package uz.androdev.wallet.ui.history

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androdev.shared.model.Transaction
import uz.androdev.shared.model.TransactionType
import uz.androdev.wallet.databinding.ItemTransactionBinding
import uz.androdev.wallet.ui.util.toFormattedBalance
import uz.androdev.wallet.ui.util.toFormattedDate

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class HistoryAdapter : ListAdapter<Transaction, HistoryAdapter.ViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transaction = getItem(position)
        holder.bind(transaction)
    }

    inner class ViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(transaction: Transaction) = with(transaction) {
            binding.amount.text = mAmount.toFormattedBalance(binding.root.context)
            binding.date.text = mDate.toFormattedDate()
            binding.details.text = mDetails

            binding.amount.setTextColor(
                if (mType == TransactionType.EXPENSE) {
                    Color.RED
                } else {
                    Color.parseColor("#3CAE5C")
                }
            )
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Transaction>() {
        override fun areItemsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem.mId == newItem.mId
        }

        override fun areContentsTheSame(oldItem: Transaction, newItem: Transaction): Boolean {
            return oldItem == newItem
        }
    }
}