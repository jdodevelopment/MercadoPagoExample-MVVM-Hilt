package ar.com.jdodevelopment.ui.main.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ar.com.jdodevelopment.databinding.SuccessFragmentBinding
import ar.com.jdodevelopment.ui.main.MainActivity

class SuccessFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = SuccessFragmentBinding.inflate(inflater, container, false)
        val mainViewModel = (activity as MainActivity).viewModel
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.getRoot()
    }


}