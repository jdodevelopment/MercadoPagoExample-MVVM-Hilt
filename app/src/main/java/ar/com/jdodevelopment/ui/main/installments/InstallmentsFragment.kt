package ar.com.jdodevelopment.ui.main.installments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.jdodevelopment.R
import ar.com.jdodevelopment.data.model.Installments
import ar.com.jdodevelopment.data.model.PayerCost
import ar.com.jdodevelopment.databinding.InstallmentsFragmentBinding
import ar.com.jdodevelopment.ui.main.MainActivity
import ar.com.jdodevelopment.ui.shared.adapter.OnItemClickListener
import ar.com.jdodevelopment.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.installments_fragment.*

@AndroidEntryPoint
class InstallmentsFragment : Fragment() {


    private val viewModel: InstallmentsViewModel by viewModels()

    private lateinit var adapter: PayerCostsAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = InstallmentsFragmentBinding.inflate(inflater, container, false)
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
        val paymentMethodId = (activity as MainActivity).viewModel.paymentMethod.value?.id
        val issuerId = (activity as MainActivity).viewModel.cardIssuer.value?.id
        val amount = (activity as MainActivity).viewModel.amount.value
        viewModel.getInstallments(paymentMethodId!!, issuerId!!, amount!!)
    }

    private fun initRecyclerView() {
        adapter = PayerCostsAdapter(OnItemClickListener { _, item -> handleItemClick(item) })
        recyclerView.adapter = adapter
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
    }

    private fun handleItemClick(item: PayerCost) {
        (activity as MainActivity).viewModel.sumbitPayerCost(item)
    }

    private fun initObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer { res -> handleResource(res) })
    }

    private fun handleResource(resource: Resource<List<Installments>>) {
        when (resource) {
            is Resource.Success -> {
                finishLoading()
                adapter.submitList(resource.data?.get(0)?.payerCosts)
            }
            is Resource.Error -> error {
                finishLoading()
                Snackbar.make(
                    requireView(), R.string.se_ha_producido_un_error, Snackbar.LENGTH_LONG
                ).show()
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