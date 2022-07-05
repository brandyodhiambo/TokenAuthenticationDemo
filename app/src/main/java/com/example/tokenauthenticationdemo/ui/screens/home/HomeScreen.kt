package com.example.tokenauthenticationdemo.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tokenauthenticationdemo.R
import com.example.tokenauthenticationdemo.models.Diagram
import com.example.tokenauthenticationdemo.ui.screens.destinations.ItemScreenDestination
import com.example.tokenauthenticationdemo.ui.theme.DarkPurple
import com.example.tokenauthenticationdemo.ui.theme.Gray
import com.example.tokenauthenticationdemo.ui.theme.Orange
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {

    }

    val productsState by viewModel.productsState

    Scaffold(
        topBar = {
            HomeScreenTopBar(
                onLogoutClicked = {
                    //trigger sign out
                },
                viewModel = viewModel
                //navigator = navigator
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
          LazyVerticalGrid(cells = GridCells.Fixed(2), contentPadding = PaddingValues(12.dp) ){
              item(span = { GridItemSpan(2) }) {
                  Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceBetween,
                  ){
                      Text(
                          text = "Categories",
                          color = Color.Black,
                          fontWeight = FontWeight.SemiBold,
                          fontSize = 18.sp,
                      )
                      Text(
                          text = "See All",
                          color = Orange,
                          fontSize = 14.sp,
                          fontWeight = FontWeight.SemiBold,
                          modifier = Modifier.clickable {
                              //navigate to category screen
                          }.padding(end = 8.dp)
                      )

                  }
              }
              //space
              item(span = { GridItemSpan(2) }) {
                  Spacer(modifier = Modifier.height(12.dp))
              }
              item(span = { GridItemSpan(2) }) {
                  Categories(categories = productsState.categories.map { it.name },viewModel = viewModel)
              }
              //space
              item(span = { GridItemSpan(2) }) {
                  Spacer(modifier = Modifier.height(12.dp))
              }
              item(span = { GridItemSpan(2) }) {
                  Text(
                      text = "Trending",
                      color = Color.Black,
                      fontWeight = FontWeight.SemiBold,
                      fontSize = 18.sp,
                  )
              }
              //products
              items(productsState.products){product->
                  ProductItem(
                      product = product,
                      navigator = navigator,
                      modifier = Modifier.width(150.dp)
                  )

              }
          }
        }
    }

}

@Composable
fun ProductItem(
    product:Diagram,
    navigator: DestinationsNavigator,
    modifier:Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable {
                navigator.navigate(ItemScreenDestination(product))
            },
        elevation = 2.dp,
        backgroundColor = Gray,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                //.height(100.dp),
                shape = RoundedCornerShape(topStart = 8.dp),
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = product.image)
                            .apply(block = fun ImageRequest.Builder.() {
                                placeholder(R.drawable.ic_launcher_background)
                                crossfade(true)
                            }).build()
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(100.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.FillBounds
                )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = product.name,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = product.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light
                )
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Top,
                ) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp)
                            .align(CenterVertically),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        tint = Orange
                    )
                    Text(
                        modifier = Modifier.align(CenterVertically),
                        text = product.status,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light
                    )
                }
            }

        }

    }

}


@Composable
fun HomeScreenTopBar(
    onLogoutClicked: () -> Unit,
    viewModel: HomeViewModel
   // navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkPurple)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = "Home",
                fontSize = 24.sp,
                color = Orange
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current)
                            .data(data = "https://media.istockphoto.com/photos/smiling-man-outdoors-in-the-city-picture-id1179420343?k=20&m=1179420343&s=612x612&w=0&h=G2UGMVSzAXGAQs3pFZpvWlHNRAzwPIWIVtSOxZHsEuc=")
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                            }).build()
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable {
                            /*navigator.navigate(
                                ProfileScreenDestination
                            )*/
                        }
                        .size(40.dp),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = CenterVertically
        ) {
            val item by remember { mutableStateOf("") }
            TextField(
                value = viewModel.searchTerm.value,
                onValueChange = {
                    viewModel.setSearchTerm(it)
                },
                placeholder = {
                    Text(
                        text = "Search",
                        //color = primaryGray
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .clickable {

                    }
                    .padding(start = 8.dp, end = 8.dp),
                shape = RoundedCornerShape(size = 8.dp),
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                ),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    disabledTextColor = Color.White,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedIndicatorColor = Color.White,
                    disabledIndicatorColor = Gray
                ),
                textStyle = TextStyle(color = Color.Black),
                maxLines = 1,
                singleLine = true,
                leadingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Gray
                    )
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun Categories(
    categories: List<String>,
    viewModel: HomeViewModel
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            Text(
                text = category,
                color = Color.White,
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(8.dp)
                    )
                    .clickable {
                        viewModel.setCategory(category)
                        viewModel.getCategories(viewModel.selectedCategory.value)
                    }
                    .background(
                        if (category == viewModel.selectedCategory.value) {
                            Orange
                        } else {
                            DarkPurple
                        }
                    )
                    .padding(10.dp)
            )
        }

    }

}
