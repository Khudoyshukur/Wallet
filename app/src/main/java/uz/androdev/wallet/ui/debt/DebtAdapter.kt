package uz.androdev.wallet.ui.debt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.androdev.shared.model.Expense
import uz.androdev.wallet.databinding.ItemTransactionBinding
import uz.androdev.wallet.ui.util.toFormattedBalance
import uz.androdev.wallet.ui.util.toFormattedDate

/**
 * Created by: androdev
 * Date: 9/26/2021
 * Time: 5:17 AM
 * Email: Khudoyshukur.Juraev.001@mail.ru
 */

class DebtAdapter(private val onDebtClicked: (Expense) -> Unit) :
    ListAdapter<Expense, DebtAdapter.ViewHolder>(DiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(expense: Expense) {
            binding.details.text = expense.details
            binding.amount.text = expense.expense.toFormattedBalance(binding.root.context)
            binding.date.text = expense.datetime.toFormattedDate()

            binding.root.setOnClickListener {
                onDebtClicked(expense)
            }
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }
    }
}