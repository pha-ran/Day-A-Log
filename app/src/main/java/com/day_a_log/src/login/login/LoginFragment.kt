package com.day_a_log.src.login.login

import android.os.Bundle
import android.view.View
import com.day_a_log.R
import com.day_a_log.config.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.day_a_log.config.ApplicationClass.Companion.sSharedPreferences
import com.day_a_log.config.BaseFragment
import com.day_a_log.databinding.FragmentLoginBinding
import com.day_a_log.src.login.LoginActivity
import com.day_a_log.src.login.find.FindFragment
import com.day_a_log.src.login.login.models.KakaoRequest
import com.day_a_log.src.login.login.models.KakaoResponse
import com.day_a_log.src.login.login.models.LoginRequest
import com.day_a_log.src.login.login.models.LoginResponse
import com.day_a_log.src.login.signup.SignUpFragment
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::bind, R.layout.fragment_login),
    LoginView, KakaoView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSignUp.setOnClickListener {
            (activity as LoginActivity).replaceFragment(SignUpFragment())
        }

        binding.tvFind.setOnClickListener {
            (activity as LoginActivity).replaceFragment(FindFragment())
        }

        binding.btnLogin.setOnClickListener {
            showLoadingDialog(requireContext())
            LoginService(this).tryPostLogin(LoginRequest(
                userId = binding.etId.text.toString(),
                userPw = binding.etPassword.text.toString()
            ))
        }

        binding.ivKakaoLogin.setOnClickListener {

            // 로그인 공통 callback 구성
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    showCustomToast("로그인 실패 $error")
                }
                else if (token != null) {
                    showCustomToast("로그인 성공")
                    println("로그인 성공 ${token.accessToken}, ${token.refreshToken}")

                    (activity as LoginActivity).login()

                    UserApiClient.instance.me { user, error ->
                        if (error != null) {
                            println("사용자 정보 요청 실패 $error")
                        }
                        else if (user != null) {
                            println("사용자 정보 요청 성공, 닉네임: ${user.kakaoAccount?.profile?.nickname}")
                        }
                    }
                    UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
                        if (error != null) {
                            println("토큰 정보 보기 실패, $error")
                        }
                        else if (tokenInfo != null) {
                            println("토큰 정보 보기 성공, 회원번호: ${tokenInfo.id}, 만료시간: ${tokenInfo.expiresIn} 초")
                        }
                    }
                }
            }

            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = callback)
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
            }

//            showLoadingDialog(requireContext())
//            KakaoService(this).tryGetKakaoLogin(KakaoRequest(
//                client_id = "191fe93a39ab3e539023124d82e77785",
//                redirect_uri = "http://localhost:3000/auth/kakao/callback",
//                response_type = "code",
//                state = null,
//                prompt = null
//            ))
        }
    }

    override fun onPostLoginSuccess(response: LoginResponse) {
        dismissLoadingDialog()
        showCustomToast(response.message)

        if (response.code == 1000) {
            sSharedPreferences.edit().putString(X_ACCESS_TOKEN, response.result.jwt).apply()
            (activity as LoginActivity).login()
            //ToDo 일반 로그인시 jwt api 호출 후 useridx 받기
        }
    }

    override fun onPostLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast(message)
    }

    override fun onGetKakaoLoginSuccess(response: KakaoResponse) {
        dismissLoadingDialog()
        showCustomToast("상태 : ${response.state}, 코드 : ${response.code}")
    }

    override fun onGetKakaoLoginFailure(message: String) {
        dismissLoadingDialog()
        showCustomToast("실패 메세지 : $message")
        println("실패 메세지 : $message")
    }
}