package com.skillbox.github.ui.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.skillbox.github.R
import com.skillbox.github.databinding.FragmentAuthBinding
import com.skillbox.github.utils.toast
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse

class AuthFragment : Fragment(R.layout.fragment_auth) {

    private val viewModel: AuthViewModel by viewModels()
    private var _binding: FragmentAuthBinding? = null
    private val binding get() = _binding!!

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK && it.data != null) {
                val tokenExchangeRequest = AuthorizationResponse.fromIntent(it.data!!)
                    ?.createTokenExchangeRequest()
                val exception = AuthorizationException.fromIntent(it.data)
                when {
                    tokenExchangeRequest != null && exception == null ->
                        viewModel.onAuthCodeReceived(tokenExchangeRequest)
                    exception != null -> viewModel.onAuthCodeFailed(exception)
                }
            } else {
                Toast.makeText(requireContext(), getString(R.string.auth_canceled), Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        val view = binding.root
        bindViewModel()
        return view
    }

/*
   //Старый код
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AUTH_REQUEST_CODE && data != null) {
            val tokenExchangeRequest = AuthorizationResponse.fromIntent(data)
                ?.createTokenExchangeRequest()
            val exception = AuthorizationException.fromIntent(data)
            when {
                tokenExchangeRequest != null && exception == null ->
                    viewModel.onAuthCodeReceived(tokenExchangeRequest)
                exception != null -> viewModel.onAuthCodeFailed(exception)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
*/

    private fun bindViewModel() {
        binding.loginButton.setOnClickListener { viewModel.openLoginPage() }
        viewModel.loadingLiveData.observe(viewLifecycleOwner, this::updateIsLoading)
        viewModel.openAuthPageLiveData.observe(viewLifecycleOwner, this::openAuthPage)
        viewModel.toastLiveData.observe(viewLifecycleOwner, this::toast)
        viewModel.authSuccessLiveData.observe(viewLifecycleOwner) {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToMainFragment())
        }
    }

    private fun updateIsLoading(isLoading: Boolean) {
        binding.loginButton.isVisible = !isLoading
        binding.loginProgress.isVisible = isLoading
    }

    private fun openAuthPage(intent: Intent) {
        resultLauncher.launch(intent)
        //startActivityForResult(intent, AUTH_REQUEST_CODE)
    }

/*    companion object {
        private const val AUTH_REQUEST_CODE = 342
    }*/
}