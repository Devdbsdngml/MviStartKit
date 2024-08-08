package com.mvistartkit.view

import android.content.Intent
import android.net.Uri
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.Issue
import com.mvistartkit.base.BaseActivity
import com.mvistartkit.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private val vm by viewModels<MainViewModel>()

    override fun init() {
        initAdapter()
        handleIntent(intent)
    }

    override fun initSubscribe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.uiState.collect { state ->
                    when (state) {
                        MainViewModel.UiState.Loading -> handleLoading()
                        is MainViewModel.UiState.Success -> handleSuccess(state.list)
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun initAdapter() {
        // todo init adapter
    }

    private fun handleIntent(intent: Intent) {
        val data: Uri? = intent.data
        if (data != null) {
            val parts = data.pathSegments
            if (parts.size >= 2) {
                val githubUsername = parts[0]
                val repositoryName = parts[1]
                vm.onEvent(MainViewModel.Event.FetchIssue(owner = githubUsername, repo = repositoryName))
            }
        }
    }

    private fun handleLoading() {
        // todo pb
    }

    private fun handleSuccess(list: List<Issue>) {
        // todo submitList
    }
}
