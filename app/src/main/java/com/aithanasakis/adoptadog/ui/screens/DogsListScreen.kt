package com.aithanasakis.adoptadog.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import coil.transform.CircleCropTransformation
import com.aithanasakis.adoptadog.DogsListState
import com.aithanasakis.adoptadog.MainViewModel
import com.aithanasakis.adoptadog.R
import com.aithanasakis.adoptadog.composables.ErrorMessage
import com.aithanasakis.adoptadog.composables.SimpleCircularProgress
import com.aithanasakis.adoptadog.models.Dog
import dev.chrisbanes.accompanist.coil.CoilImage
import kotlin.random.Random


const val dogsListRouteName = "DOGS_LIST"

@Composable
fun DogsListScreen(navController: NavController, viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Adopt-a-Dog") })
        },
        content = {
            DogsContent(navController, viewModel)
        }
    )
}

@Composable
fun DogsContent(navController: NavController, viewModel: MainViewModel) {
    val dogsState = viewModel.dogsFlow.collectAsState().value
    when (dogsState) {
        is DogsListState.Loaded -> DogsList(navController = navController, dogs = dogsState.dogs)
        is DogsListState.Loading -> SimpleCircularProgress()
        is DogsListState.Error -> ErrorMessage(message = dogsState.reason)
    }
}

@Composable
fun DogsList(navController: NavController, dogs: List<Dog>) {
    val colors = dogs.map { getRandomColor() }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
        content = {
            itemsIndexed(dogs) { index, _ ->
                DogListItem(dogs[index], colors[index]) { _ ->
                    navController.navigate("$dogsDetailsRouteName/${dogs[index].id}")
                }
            }
        }
    )
}

@Composable
fun DogListItem(
    dog: Dog,
    backgroundColor: Color,
    onClick: (id: Int) -> Unit
) {
    Card(
        backgroundColor = backgroundColor,
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(PaddingValues(horizontal = 4.dp, vertical = 4.dp))
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = { onClick(dog.id) })
                .padding(PaddingValues(all = 16.dp))
                .fillMaxWidth()
        ) {
            CoilImage(
                data = dog.photoUrl,
                modifier = Modifier.size(84.dp, 84.dp),
                contentDescription = dog.breed,
                requestBuilder = {
                    transformations(CircleCropTransformation())
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = stringResource(id = R.string.name, dog.name),
                    style = MaterialTheme.typography.subtitle1,
                    color = Color.Black
                )
                Text(
                    text = stringResource(id = R.string.breed, dog.breed),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Black
                )
                Text(
                    text = stringResource(id = R.string.age,"${dog.age}"),
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Black
                )
            }
        }
    }
}

private fun getRandomColor(): Color =
    Color(Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255))

