package ar.com.jdodevelopment.ui.main

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import ar.com.jdodevelopment.R
import ar.com.jdodevelopment.data.model.CardIssuer
import ar.com.jdodevelopment.data.model.PayerCost
import ar.com.jdodevelopment.data.model.PaymentMethod
import ar.com.jdodevelopment.databinding.ActivityMainBinding
import ar.com.jdodevelopment.ui.main.amount.AmountFragmentDirections
import ar.com.jdodevelopment.ui.main.banks.BanksFragmentDirections
import ar.com.jdodevelopment.ui.main.installments.InstallmentsFragmentDirections
import ar.com.jdodevelopment.ui.main.payment_methods.PaymentMethodsFragmentDirections
import ar.com.jdodevelopment.ui.main.success.SuccessFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this
        initUi()
        initObserbers()
    }

    private fun initUi() {
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.navHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun initObserbers() {
        viewModel.amount.observe(this, Observer { value -> handleAmount(value) })
        viewModel.paymentMethod.observe(this, Observer { value -> handlePaymentMethod(value) })
        viewModel.cardIssuer.observe(this, Observer { value -> handleCardIssuer(value) })
        viewModel.payerCost.observe(this, Observer { value -> handlePayerCost(value) })
    }

    private fun handleAmount(value: Double?) {
        value?.let {
            val navDirections = AmountFragmentDirections.choosePaymentMethodAction()
            navController.navigate(navDirections)
            hideKeyboard()
        } ?:let  {
            val navDirections = SuccessFragmentDirections.backToStartAction()
            navController.navigate(navDirections)
        }
    }

    private fun handlePaymentMethod(value: PaymentMethod?) {
        value?.let {
            val navDirections = PaymentMethodsFragmentDirections.chooseBankAction()
            navController.navigate(navDirections)
        }
    }

    private fun handleCardIssuer(value: CardIssuer?) {
        value?.let {
            val navDirections = BanksFragmentDirections.chooseInstallments()
            navController.navigate(navDirections)
        }
    }

    private fun handlePayerCost(value: PayerCost?) {
        value?.let {
            val navDirections = InstallmentsFragmentDirections.showSuccess()
            navController.navigate(navDirections)
        }
    }


    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(navHostFragment.view?.windowToken, 0)
    }

    private fun handleNavigateBack() {

    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.successFragment) {
            viewModel.reset()
        }else{
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (navController.currentDestination?.id == R.id.successFragment) {
            viewModel.reset()
            return false
        }else{
            return navController.navigateUp()
        }

    }
}