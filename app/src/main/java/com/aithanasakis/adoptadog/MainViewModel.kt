package com.aithanasakis.adoptadog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aithanasakis.adoptadog.data.DogsRepository
import com.aithanasakis.adoptadog.models.Dog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class DogsListState {
    object Loading : DogsListState()
    class Loaded(val dogs: List<Dog>) : DogsListState()
    class Error(val reason: String = "can't load dogs list") : DogsListState()
}

sealed class DogDetailsState {
    object Loading : DogDetailsState()
    class Loaded(val dog: Dog) : DogDetailsState()
    class Error(val reason: String = "can't load dog details") : DogDetailsState()
}

// di needed...
class MainViewModel(private val dogsRepository: DogsRepository = DogsRepository()) : ViewModel() {

    private val _dogsFlow =
        MutableStateFlow<DogsListState>(DogsListState.Loading)
    val dogsFlow: StateFlow<DogsListState> = _dogsFlow

    private val _dogDetailsFlow =
        MutableStateFlow<DogDetailsState>(DogDetailsState.Loading)
    val dogDetailsFlow: StateFlow<DogDetailsState> = _dogDetailsFlow

    init {
        viewModelScope.launch {
            _dogsFlow.value = DogsListState.Loaded(dogsRepository.getAllDogs())
        }
    }

    fun getDogDetails(id: Int) {
        _dogDetailsFlow.value = DogDetailsState.Loading
        viewModelScope.launch {
            _dogDetailsFlow.value =
                dogsRepository.getDogById(id)?.let { dog -> DogDetailsState.Loaded(dog) }
                    ?: DogDetailsState.Error()
        }
    }
}