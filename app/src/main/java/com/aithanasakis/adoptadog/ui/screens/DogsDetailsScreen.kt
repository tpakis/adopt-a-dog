package com.aithanasakis.adoptadog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aithanasakis.adoptadog.DogDetailsState
import com.aithanasakis.adoptadog.MainViewModel
import com.aithanasakis.adoptadog.R
import com.aithanasakis.adoptadog.composables.ErrorMessage
import com.aithanasakis.adoptadog.composables.SimpleCircularProgress
import com.aithanasakis.adoptadog.models.Dog
import dev.chrisbanes.accompanist.coil.CoilImage

const val dogsDetailsRouteName = "DOG_DETAILS"

@Composable
fun DogDetailsScreen(dogId: Int, viewModel: MainViewModel) {
    viewModel.getDogDetails(dogId)
    val dogState = viewModel.dogDetailsFlow.collectAsState().value
    Scaffold(
        content = {
            when (dogState) {
                is DogDetailsState.Loaded -> Column {
                    Header(dogState.dog)
                    Details(dogState.dog)
                }
                is DogDetailsState.Loading -> SimpleCircularProgress()
                is DogDetailsState.Error -> ErrorMessage(message = dogState.reason)
            }

        }
    )
}

@Composable
fun Header(dog: Dog) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CoilImage(
                data = dog.photoUrl,
                contentDescription = dog.breed,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun Details(dog: Dog) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        Text(text = dog.name, style = MaterialTheme.typography.h4)
        Text(text = dog.breed, style = MaterialTheme.typography.h5)
        Text(text = stringResource(id = R.string.age, "${dog.age}"), style = MaterialTheme.typography.h6)
        Text(
            text = dog.description,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify,
            modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
        )
        Button(
            modifier = Modifier.height(40.dp),
            shape = MaterialTheme.shapes.small,
            onClick = { },
        ) {
            Text(text = stringResource(id = R.string.adopt_me), fontSize = 16.sp)
        }
    }
}


