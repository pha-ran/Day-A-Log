package com.day_a_log.src.login.signup

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentSignUpBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.login.login.LoginFragment
import com.day_a_log.src.login.signup.models.DuplicatedIdResponse
import com.day_a_log.src.login.signup.models.DuplicatedPhoneResponse
import com.day_a_log.src.login.signup.models.SignUpRequest
import com.day_a_log.src.login.signup.models.SignUpResponse

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::bind, R.layout.fragment_sign_up),
    DuplicatedPhoneView, DuplicatedIdView, SignUpView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setOnClickListener {
            (activity as LoginActivity).replaceFragment(LoginFragment())
        }

        binding.btnSend.setOnClickListener {
            showLoadingDialog(requireContext())
            DuplicatedPhoneService(this).tryGetDuplicatedPhone(phone = binding.etPhoneNumber.text.toString())
        }

        binding.btnCode.setOnClickListener {
            binding.linearKey.visibility = View.GONE
            binding.linearIdPassword.visibility = View.VISIBLE
        }

        binding.btnAdd.setOnClickListener {
            showLoadingDialog(requireContext())
            DuplicatedIdService(this).tryGetDuplicatedId(id = binding.etId.text.toString())
        }
    }

    override fun onGetDuplicatedPhoneSuccess(response: DuplicatedPhoneResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)

        if (response.code == 1000) {
            binding.btnSend.visibility = View.GONE
            binding.linearCode.visibility = View.VISIBLE
            binding.etName.isEnabled = false
            binding.etPhoneNumber.isEnabled = false
        }
    }

    override fun onGetDuplicatedPhoneFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onGetDuplicatedIdSuccess(response: DuplicatedIdResponse) {
        dismissLoadingDialog()
        showCustomToast("${response.code}, ${response.message}")

        if (response.code == 1000) {
            showLoadingDialog(requireContext())
            SignUpService(this).tryPostSignUp(SignUpRequest(
                name = binding.etName.text.toString(),
                phone = binding.etPhoneNumber.text.toString(),
                userId = binding.etId.text.toString(),
                userPw = binding.etPassword.text.toString()
            ))
        }
    }

    override fun onGetDuplicatedIdFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        dismissLoadingDialog()
        showCustomToast("${response.code}, ${response.message}")
    }

    override fun onPostSignUpFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}