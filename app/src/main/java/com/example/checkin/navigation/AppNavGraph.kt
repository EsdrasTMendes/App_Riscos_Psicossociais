package com.example.checkin.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.checkin.data.local.UserPreferencesRepository
import com.example.checkin.data.remote.ApiClient
import com.example.checkin.data.repository.AuthRepository
import com.example.checkin.data.repository.CompanyRepository
import com.example.checkin.ui.screens.login.LoginScreen
import com.example.checkin.ui.screens.login.LoginViewModel
import com.example.checkin.ui.screens.login.LoginViewModelFactory
import com.example.checkin.ui.screens.platformAdmin.PlatformAdminHomeScreen
import com.example.checkin.ui.screens.registerCompany.RegisterCompanyScreen
import com.example.checkin.ui.screens.registerCompany.RegisterCompanyViewModel
import com.example.checkin.ui.screens.registerCompany.RegisterCompanyViewModelFactory
import androidx.compose.runtime.rememberCoroutineScope
import com.example.checkin.data.repository.CompanyAdminRepository
import com.example.checkin.data.repository.PlatformAdminRepository
import com.example.checkin.data.repository.UserRepository
import com.example.checkin.ui.screens.companyAdmin.CompanyAdminHomeScreen
import com.example.checkin.ui.screens.registerAdmin.RegisterAdminScreen
import com.example.checkin.ui.screens.registerAdmin.RegisterAdminViewModel
import com.example.checkin.ui.screens.registerAdmin.RegisterAdminViewModelFactory
import com.example.checkin.ui.screens.registerCompanyAdmin.CreateCompanyAdminScreen
import com.example.checkin.ui.screens.registerCompanyAdmin.CreateCompanyAdminViewModel
import com.example.checkin.ui.screens.registerCompanyAdmin.CreateCompanyAdminViewModelFactory
import com.example.checkin.ui.screens.reports.ReportsScreen
import com.example.checkin.ui.screens.reports.ReportsViewModel
import com.example.checkin.ui.screens.reports.ReportsViewModelFactory
import com.example.checkin.ui.screens.resetPassword.ResetPasswordScreen
import com.example.checkin.ui.screens.resetPassword.ResetPasswordViewModel
import com.example.checkin.ui.screens.resetPassword.ResetPasswordViewModelFactory
import com.example.checkin.ui.screens.user.DailyCheckinScreen
import com.example.checkin.ui.screens.user.DailyCheckinViewModel
import com.example.checkin.ui.screens.user.DailyCheckinViewModelFactory
import com.example.checkin.ui.screens.user.UserHomeScreen
import com.example.checkin.ui.screens.user.WeeklyQuestionnaireScreen
import com.example.checkin.ui.screens.user.WeeklyQuestionnaireViewModel
import com.example.checkin.ui.screens.user.WeeklyQuestionnaireViewModelFactory
import com.example.checkin.ui.screens.userRegistration.UserRegistrationScreen
import com.example.checkin.ui.screens.userRegistration.UserRegistrationViewModel
import com.example.checkin.ui.screens.userRegistration.UserRegistrationViewModelFactory
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "Login"
    ) {
        composable("Login") {
            val context = LocalContext.current
            val authRepository = AuthRepository(ApiClient.instance, UserPreferencesRepository(context))
            val loginViewModel: LoginViewModel = viewModel(factory = LoginViewModelFactory(authRepository))

            LoginScreen(
                loginViewModel = loginViewModel,
                onLoginSuccess = {
                    val role = loginViewModel.uiState.userRole

                    val destination = when (role) {
                        "ROLE_PLATFORM_ADMIN" -> "PlatformAdminHomeScreen"
                        "ROLE_COMPANY_ADMIN" -> "CompanyAdminHomeScreen"
                        else -> "UserHomeScreen"
                    }

                    navController.navigate(destination) {
                        popUpTo("Login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("UserRegistrationScreen")
                }
            )
        }

        composable("PlatformAdminHomeScreen") {
            val context = LocalContext.current
            val prefs = UserPreferencesRepository(context)
            val scope = rememberCoroutineScope()
            PlatformAdminHomeScreen(
                navController = navController,
                onLogoutClick = {
                    scope.launch {
                        prefs.clearAuthData()
                        navController.navigate("Login") {
                            popUpTo(0)
                        }
                    }
                }
            )
        }

        composable("RegisterCompanyScreen") {
            val context = LocalContext.current
            val apiService = ApiClient.instance
            val userPrefs = UserPreferencesRepository(context)

            val companyRepository = CompanyRepository(apiService, userPrefs)

            val registerCompanyViewModel: RegisterCompanyViewModel = viewModel(
                factory = RegisterCompanyViewModelFactory(companyRepository)
            )

            RegisterCompanyScreen(
                navController = navController,
                registerCompanyViewModel = registerCompanyViewModel
            )
        }

        composable("RegisterAdminScreen") {
            val context = LocalContext.current
            val apiService = ApiClient.instance
            val userPrefs = UserPreferencesRepository(context)

            val repo = PlatformAdminRepository(apiService, userPrefs)
            val vm: RegisterAdminViewModel = viewModel(factory = RegisterAdminViewModelFactory(repo))

            RegisterAdminScreen(navController = navController, registerAdminViewModel = vm)
        }

        composable("ResetPasswordScreen") {
            val context = LocalContext.current
            val apiService = ApiClient.instance
            val userPrefs = UserPreferencesRepository(context)

            val repo = PlatformAdminRepository(apiService, userPrefs)
            val vm: ResetPasswordViewModel = viewModel(factory = ResetPasswordViewModelFactory(repo))

            ResetPasswordScreen(navController = navController, resetPasswordViewModel = vm)
        }

        composable("CompanyAdminHomeScreen") {
            // A mesma lógica de logout da outra tela de admin
            val context = LocalContext.current
            val prefs = UserPreferencesRepository(context)
            val scope = rememberCoroutineScope()

            CompanyAdminHomeScreen(
                navController = navController,
                onLogoutClick = {
                    scope.launch {
                        prefs.clearAuthData()
                        navController.navigate("Login") {
                            popUpTo(0)
                        }
                    }
                }
            )
        }

        composable("CreateCompanyAdminScreen") {
            val context = LocalContext.current
            val apiService = ApiClient.instance
            val userPrefs = UserPreferencesRepository(context)

            val repo = CompanyAdminRepository(apiService, userPrefs)
            val vm: CreateCompanyAdminViewModel = viewModel(factory = CreateCompanyAdminViewModelFactory(repo))

            CreateCompanyAdminScreen(navController = navController, viewModel = vm)
        }

        composable("ReportsScreen") {
            val context = LocalContext.current
            val apiService = ApiClient.instance
            val userPrefs = UserPreferencesRepository(context)

            val repo = CompanyAdminRepository(apiService, userPrefs)
            val vm: ReportsViewModel = viewModel(factory = ReportsViewModelFactory(repo))

            ReportsScreen(navController = navController, viewModel = vm)
        }

        composable("UserRegistrationScreen") {
            val context = LocalContext.current
            val authRepository = AuthRepository(ApiClient.instance, UserPreferencesRepository(context))
            val vm: UserRegistrationViewModel = viewModel(factory = UserRegistrationViewModelFactory(authRepository))

            UserRegistrationScreen(navController = navController, viewModel = vm)
        }

        composable("UserHomeScreen") {
            // A mesma lógica de logout das outras telas home
            val context = LocalContext.current
            val prefs = UserPreferencesRepository(context)
            val scope = rememberCoroutineScope()

            UserHomeScreen(
                navController = navController,
                onLogoutClick = {
                    scope.launch {
                        prefs.clearAuthData()
                        navController.navigate("Login") {
                            popUpTo(0)
                        }
                    }
                }
            )
        }

        composable("DailyCheckinScreen") {
            val context = LocalContext.current
            val apiService = ApiClient.instance
            val userPrefs = UserPreferencesRepository(context)

            val repo = UserRepository(apiService, userPrefs)
            val vm: DailyCheckinViewModel = viewModel(factory = DailyCheckinViewModelFactory(repo))

            DailyCheckinScreen(navController = navController, viewModel = vm)
        }

        composable("WeeklyQuestionnaireScreen") {
            val context = LocalContext.current
            val repo = UserRepository(ApiClient.instance, UserPreferencesRepository(context))
            val vm: WeeklyQuestionnaireViewModel = viewModel(factory = WeeklyQuestionnaireViewModelFactory(repo))
            WeeklyQuestionnaireScreen(navController = navController, viewModel = vm)
        }


    }
}