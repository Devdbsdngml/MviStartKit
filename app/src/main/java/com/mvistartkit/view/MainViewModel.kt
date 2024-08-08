package com.mvistartkit.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.ApiResponse
import com.example.domain.GetIssueListUseCase
import com.example.domain.Issue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
    @Inject
    constructor(
        private val getIssueListUseCase: GetIssueListUseCase,
    ) : ViewModel() {
        init {
            viewModelScope.launch {
                mainEvent
                    .collectLatest { event ->
                        when (event) {
                            is Event.FetchIssue -> fetchIssue(event.owner, event.repo)
                        }
                    }
            }
        }

        private val mainEvent: MutableSharedFlow<Event> = MutableSharedFlow()

        private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
        val uiState = _uiState.asStateFlow()

        fun onEvent(event: Event) {
            viewModelScope.launch {
                mainEvent.emit(event)
            }
        }

        private fun fetchIssue(
            owner: String,
            repo: String,
        ) {
            viewModelScope.launch {
                getIssueListUseCase(
                    owner,
                    repo,
                )
                    .distinctUntilChanged()
                    .collect {
                        when (it) {
                            is ApiResponse.Success -> {
                                _uiState.value = UiState.Success(it.data)
                            }
                            is ApiResponse.Failure -> {
                                // todo failure
                            }
                        }
                    }
            }
        }

        sealed interface Event {
            data class FetchIssue(val owner: String, val repo: String) : Event
        }

        sealed interface UiState {
            data object Loading : UiState

            data class Success(val list: List<Issue>) : UiState
        }
    }
