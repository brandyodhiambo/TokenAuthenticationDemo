package com.example.tokenauthenticationdemo.ui.screens.ItemDetailScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.tokenauthenticationdemo.R
import com.example.tokenauthenticationdemo.models.Diagram
import com.example.tokenauthenticationdemo.ui.theme.DarkPurple
import com.example.tokenauthenticationdemo.ui.theme.Gray
import com.example.tokenauthenticationdemo.ui.theme.Orange
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
@Destination
fun ItemDetailScreen(
    navigator: DestinationsNavigator
) {
    val product = remember {
        Product(
            name = "Electrical and Computer",
            image = listOf(
                "https://images.unsplash.com/photo-1517336714731-489689fd1ca8?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YXBwbGUlMjBsYXB0b3B8ZW58MHx8MHx8&w=1000&q=80",
                "https://imageio.forbes.com/specials-images/imageserve/62239d44f06c1f3c7579a719/Apple-Brand-M1-Model-Macbook-pro-with-colorful-light-background-/960x0.jpg?format=jpg&width=960",
                "https://www.allround-pc.com/wp-content/uploads/2021/12/Apple-MacBook-Pro-2021-mit-M1-Pro-Chip-Notebook-Stock-1.jpg"
            ),
            category = "All",
            price = "₹ 100",
            description = "Electrical and Computer",
            status = "Active",
            price_per_meter = "₹ 100",
            dealer_price = "₹ 4000",
            quantity_on_hand = "10",
            quantity_on_sale_order = "5",
            weight = "340g",
            material = "Plastic",
            country_of_origin = "India",
        )
    }
    Scaffold(
        topBar = {
            ItemDetailTopAppBar(navigator)
        }
    ) {
        ItemDetailContent(
            navigator = navigator,
            product = product,
            modifier = Modifier.fillMaxSize()
        )
    }

}

@Composable
fun ItemDetailContent(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    product: Product
) {
    Column {
        val isEndless:Boolean = false
        val item:List<String> = product.image
        val listState = rememberLazyListState(
            if (isEndless) Int.MAX_VALUE /2 else 0
        )
        LazyRow(
            state = listState,
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(top = 2.dp, start = 1.dp, end = 1.dp)
        ){
            items(
                count = if (isEndless) Int.MAX_VALUE /2 else item.size,
                itemContent = {
                    val index = it%item.size
                    val image = item[index]
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(LocalContext.current)
                                    .data(data = image)
                                    .apply(block = fun ImageRequest.Builder.() {
                                        crossfade(true)
                                        placeholder(R.drawable.ic_launcher_background)
                                    }).build()
                            ),
                            contentDescription = null,
                            modifier = modifier
                                .fillMaxSize()
                                .height(250.dp),
                            contentScale = ContentScale.Crop
                        )

                    }

                }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = modifier
                .fillMaxWidth()
                .weight(2f),
            elevation = 0.dp,
            shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
            backgroundColor = Color.White
        ) {

            Box(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = product.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = product.dealer_price,
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Category: ${product.category}",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Canvas(modifier = Modifier.size(4.dp), onDraw = {
                            drawCircle(color = Color.Gray)
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Status: ${product.status}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Canvas(modifier = Modifier.size(4.dp), onDraw = {
                            drawCircle(color = Color.Gray)
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Quantity on Hand: ${product.quantity_on_hand}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Canvas(modifier = Modifier.size(4.dp), onDraw = {
                            drawCircle(color = Color.Gray)
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Quantity on Sale: ${product.quantity_on_sale_order}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Canvas(modifier = Modifier.size(4.dp), onDraw = {
                            drawCircle(color = Color.Gray)
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Weight: ${product.weight}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Canvas(modifier = Modifier.size(4.dp), onDraw = {
                            drawCircle(color = Color.Gray)
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Material: ${product.material}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Canvas(modifier = Modifier.size(4.dp), onDraw = {
                            drawCircle(color = Color.Gray)
                        })
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Country of Origin: ${product.country_of_origin}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                    Spacer(modifier = Modifier.height(10.dp))
                    val rating: Float by remember { mutableStateOf(3f) }

                  /*  Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RatingBar(
                            value = rating,
                            config = RatingBarConfig()
                                .activeColor(Orange)
                                .inactiveColor(Color.Gray)
                                .stepSize(StepSize.HALF)
                                .numStars(5)
                                .isIndicator(true)
                                .size(16.dp)
                                .padding(3.dp)
                                .style(RatingBarStyle.HighLighted),
                            onValueChange = {},
                            onRatingChanged = {}
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { *//*TODO*//* }) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    tint = Orange,
                                    contentDescription = null
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(onClick = { TODO }) {
                                Icon(
                                    imageVector = Icons.Default.AddCircle,
                                    tint = Orange,
                                    contentDescription = null
                                )
                            }
                        }

                    }*/

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Description:",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = product.description,
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )

                }
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.Bottom
                ) {
                   Button(
                        onClick = {
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Black,
                            backgroundColor = DarkPurple,
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            fontSize = 16.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            text = "Add to Cart"
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun ItemDetailTopAppBar(
    navigator: DestinationsNavigator
) {
    TopAppBar(
        title = {
            Text("Item Details")
        },
        backgroundColor = Color.White,
        navigationIcon = {
            IconButton(
                onClick = {
                    navigator.popBackStack()
                },
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Orange,
                    contentDescription = null
                )
            }
        },
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                IconButton(
                    onClick = {

                    },
                ){
                   /* Icon(
                        *//*
                        * we shall add a command for cart functionality and remove 1+2*//*
                        painter = if (1+2 == 3) {
                            painterResource(id = R.drawable.ic_shopping_fill)
                        } else {
                            painterResource(id = R.drawable.ic_shopping_cart_no_item)
                        },
                        tint = if (1+2 == 3) {
                            Orange
                        } else {
                            Gray
                        },
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )*/
                }
                Spacer(modifier = Modifier.width(12.dp))
                IconButton(
                    onClick = {

                    },
                ){
                    Icon(
                        /*
                        * we shall add a command for favorite and remove 1+2*/
                        painter = if (1+2 == 3) {
                            painterResource(id = R.drawable.ic_favorite_fill)
                        } else {
                            painterResource(id = R.drawable.ic_favorite)
                        },
                        tint = if (1+2 == 3) {
                            Orange
                        } else {
                            Gray
                        },
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }

            }
        }
    )
}

data class Product(
    val name: String,
    val price: String,
    val image: List<String>,
    val status:String,
    val description: String,
    val category: String,
    val price_per_meter: String,
    val dealer_price: String,
    val quantity_on_hand: String,
    val quantity_on_sale_order: String,
    val weight: String,
    val material: String,
    val country_of_origin: String,
)
