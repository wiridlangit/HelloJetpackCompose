package com.jsc.hellojetpackcompose.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jsc.hellojetpackcompose.R

@Composable
fun ListItem(name: String, image: Bitmap?, onClick: () -> Unit) {
    val imageResId = when (name) {
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

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (image != null) {
            Image(
                bitmap = image.asImageBitmap(),
                contentDescription = "Photo of $name",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
        } else {
            Image(
                painter = painterResource(imageResId),
                contentDescription = "Placeholder Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Gray, CircleShape)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = name,
                fontSize = 24.sp
            )
        }
    }
}
