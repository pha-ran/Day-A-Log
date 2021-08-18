package com.day_a_log.src.login.signup

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentSignUpBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.login.login.LoginFragment
import com.day_a_log.src.login.signup.models.*

class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::bind, R.layout.fragment_sign_up),
    DuplicatedEmailView, DuplicatedIdView, AuthEmailView, SignUpView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogin.setOnClickListener {
            (activity as LoginActivity).replaceFragment(LoginFragment())
        }

        binding.btnSend.setOnClickListener {
            showLoadingDialog(requireContext())
            DuplicatedEmailService(this).tryGetDuplicatedEmail(email = binding.etEmail.text.toString())
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

    override fun onGetDuplicatedEmailSuccess(response: DuplicatedEmailResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)

        if (response.code == 1000) {
            binding.btnSend.visibility = View.GONE
            binding.linearCode.visibility = View.VISIBLE
            binding.etName.isEnabled = false
            binding.etEmail.isEnabled = false

            //인증코드 전송
            //AuthEmailService(this).tryPostAuthEmail(email = binding.etEmail.text.toString())
        }
    }

    override fun onGetDuplicatedEmailFailure(message: String) {
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
                email = binding.etEmail.text.toString(),
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

    override fun onPostAuthEmailSuccess(response: AuthEmailResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)
    }

    override fun onPostAuthEmailFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }
}