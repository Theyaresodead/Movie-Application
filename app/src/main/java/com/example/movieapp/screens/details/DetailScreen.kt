package com.example.movieapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.movieapp.model.Movie
import com.example.movieapp.model.getMovies
import com.example.movieapp.widgets.Movie

@Composable
fun DetailScreen(navController: NavController, movieid: String?){
    val newMovieList= getMovies().filter { movie ->
        movie.id == movieid
    }

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.LightGray, elevation = 5.dp) {
            Row(horizontalArrangement = Arrangement.Start){
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                    modifier = androidx.compose.ui.Modifier.clickable {
                        navController.popBackStack()
                    })
                Spacer(modifier = androidx.compose.ui.Modifier.height(100.dp))
            }
        }
    })
    {
        Surface(modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {
            Column(verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                Movie(movie = newMovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Film Images")
                HorizontalScrollableImageView(newMovieList)
            }
        }

    }
}

@Composable
private fun HorizontalScrollableImageView(newMovieList: List<Movie>) {
    LazyRow {
        items(newMovieList[0].images) { images ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp), elevation = 5.dp
            ) {
                Image(
                    painter = rememberImagePainter(data = images),
                    contentDescription = "Snapshot"
                )
            }
        }
    }
}

