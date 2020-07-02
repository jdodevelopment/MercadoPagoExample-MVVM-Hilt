package ar.com.jdodevelopment.ui.main.banks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.jdodevelopment.R
import ar.com.jdodevelopment.data.model.CardIssuer
import ar.com.jdodevelopment.data.model.PaymentMethod
import ar.com.jdodevelopment.databinding.BanksFragmentBinding
import ar.com.jdodevelopment.ui.main.MainActivity
import ar.com.jdodevelopment.ui.shared.adapter.OnItemClickListener
import ar.com.jdodevelopment.util.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.banks_fragment.*
import kotlinx.android.synthetic.main.banks_fragment.recyclerView
import kotlinx.android.synthetic.main.banks_fragment.swipeRefreshLayout
import kotlinx.android.synthetic.main.payment_methods_fragment.*

@AndroidEntryPoint
class BanksFragment : Fragment() {


    private val viewModel: BanksViewModel by viewModels()

    private lateinit var adapter: BanksAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = BanksFragmentBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.setViewModel(viewModel)
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
        viewModel.getCardIssuers(paymentMethodId!!)
    }

    private fun initRecyclerView() {
        adapter = BanksAdapter(OnItemClickListener { _, item -> handleItemClick(item) })
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), VERTICAL))
        recyclerView.adapter = adapter
    }

    private fun handleItemClick(item: CardIssuer) {
        (activity as MainActivity).viewModel.sumbitCardIssuer(item)
    }

    private fun initObservers() {
        viewModel.data.observe(viewLifecycleOwner, Observer { res -> handleResource(res) })
    }

    private fun handleResource(resource: Resource<List<CardIssuer>>) {
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