package com.aithanasakis.adoptadog.data

import com.aithanasakis.adoptadog.models.Dog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// no time for di setup, only happy path :)
class DogsRepository(private val dogsDatasource: DogsDatasource = FakeDogsDatasource()) {

    suspend fun getAllDogs(): List<Dog> {
        return withContext(Dispatchers.IO) {
            dogsDatasource.getDogs()
        }
    }

    suspend fun getDogById(id: Int): Dog? {
        return withContext(Dispatchers.IO) {
            dogsDatasource.getDogs().firstOrNull { it.id == id }
        }
    }

}