@file:OptIn(ExperimentalAnimationApi::class)

package com.example.tokenauthenticationdemo.ui.screens.itemScreen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tokenauthenticationdemo.R
import com.example.tokenauthenticationdemo.models.Diagram
import com.example.tokenauthenticationdemo.ui.screens.destinations.ItemDetailScreenDestination
import com.example.tokenauthenticationdemo.ui.theme.Orange
import com.example.tokenauthenticationdemo.utils.UiEvents
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@Composable
@Destination
fun ItemScreen(
    diagram: Diagram,
    navigator: DestinationsNavigator,
    viewModel: ItemViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

   LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvents.SnackbarEvent -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        backgroundColor = Color.White,
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                elevation = 1.dp,
                backgroundColor = Color.White,
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 24.dp),
                        text = "Items",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
            )
        }
    ) {
        LazyColumn {
            val data = listOf(diagram)
            items(data){ item ->
                Itemlist(
                    diagram = item,
                    viewModel = viewModel,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(135.dp)
                        .padding(8.dp),
                    navigator = navigator
                )
            }
        }

        if (listOf(diagram).isEmpty()) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(220.dp),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
fun Itemlist(
    diagram: Diagram,
    modifier: Modifier = Modifier,
    viewModel: ItemViewModel,
   navigator: DestinationsNavigator
) {
    Card(
        modifier = modifier.clickable {
           navigator.navigate(ItemDetailScreenDestination/*ProductDetailsScreenDestination(wishlist.toProduct())*/)
        },
        shape = RoundedCornerShape(8.dp),
        elevation = 3.dp
    ) {
        Row {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = diagram.image)
                        .apply(block = fun ImageRequest.Builder.() {
                            placeholder(R.drawable.ic_launcher_background)
                            crossfade(true)
                        }).build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .padding(5.dp)
                    .weight(1f)
                    .fillMaxHeight(),
                contentScale = ContentScale.Inside
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .padding(5.dp)
            ) {
                Text(
                    text = diagram.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = diagram.status,
                    color = Color.Black,
                    fontSize = 22.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Light
                )
                IconButton(
                    onClick = {
                        //viewModel.insertItemToFavorite(diagram)
                    },
                    modifier = Modifier.align(End),
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite_fill),
                        tint = Orange,
                        contentDescription = null,
                    )
                }
            }
        }
    }
}

