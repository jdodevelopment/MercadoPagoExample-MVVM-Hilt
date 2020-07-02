package ar.com.jdodevelopment.ui.main.payment_methods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.jdodevelopment.R
import ar.com.jdodevelopment.data.model.PaymentMethod
import ar.com.jdodevelopment.databinding.PaymentMethodsFragmentBinding
import ar.com.jdodevelopment.ui.main.MainActivity
import ar.com.jdodevelopment.ui.shared.adapter.OnItemClickListener
import ar.com.jdodevelopment.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.payment_methods_fragment.*

@AndroidEntryPoint
class PaymentMethodsFragment : Fragment() {

    private val viewModel: PaymentMethodsViewModel by viewModels()

    private lateinit var adapter: PaymentMethodAdapter;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PaymentMethodsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.getRoot()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()
        consumeData()
    }

    private fun consumeData() {
        viewModel.getPaymentMethods()
    }

    private fun initRecyclerView() {
        adapter = PaymentMethodAdapter(OnItemClickListener { _, item -> handleItemClick(item) })
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration( requireContext(), VERTICAL))
    }

    private fun handleItemClick(item: PaymentMethod) {
        (activity as MainActivity).viewModel.sumbitPaymentMethod(item)
    }

    private fun initObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer { res -> handleResource(res) })
    }

    private fun handleResource(resource: Resource<List<PaymentMethod>>) {
        when (resource) {
            is Resource.Success -> {
                finishLoading()
                adapter.submitList(resource.data)
            }
            is Resource.Error -> error {
                finishLoading()
                Snackbar.make(requireView(),
                    R.string.se_ha_producido_un_error, Snackbar.LENGTH_LONG).show()
            }
            is Resource.Loading -> {
                startLoading()
            }
        }
    }

    private fun startLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    private fun finishLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

}