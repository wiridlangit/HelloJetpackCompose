package com.jsc.hellojetpackcompose.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jsc.hellojetpackcompose.Item
import com.jsc.hellojetpackcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, item: Item?, onDelete: () -> Unit) {
    val imageResId = when (item?.name) {
        "Andre" -> R.drawable.andre
        "Desta" -> R.drawable.desta
        "Parto" -> R.drawable.parto
        "Wendy" -> R.drawable.wendy
        "Komeng" -> R.drawable.komeng
        "Raffi Ahmad" -> R.drawable.raffi
        "Andhika Pratama" -> R.drawable.andhika
        "Vincent" -> R.drawable.vincent
        "Sule" -> R.drawable.sule
        "Cak Lontong" -> R.drawable.caklontong
        else -> R.drawable.jetpack_compose
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = item?.name ?: "Detail") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { onDelete() }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Item")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item?.image?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Photo of ${item.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                )
            } ?: Image(
                painter = painterResource(id = imageResId),
                contentDescription = "Photo of ${item?.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item?.name ?: "Unknown", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item?.description ?: "No description available", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
