package ar.com.jdodevelopment.ui.main.amount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ar.com.jdodevelopment.databinding.AmountFragmentBinding
import ar.com.jdodevelopment.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AmountFragment : Fragment() {


    val viewModel: AmountViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = AmountFragmentBinding.inflate(inflater, container, false)
        viewModel.callback = SubmitAmountCallback { amount -> submitAmount(amount)}
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private fun submitAmount(amount: Double?) {
        (activity as MainActivity).viewModel.sumbitAmount(amount)
    }



}



