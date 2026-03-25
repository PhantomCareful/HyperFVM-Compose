package com.careful.hyperfvm.compose.ui.components.card_data

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun ImagesRow(
    imageResIds: List<Int>,
    modifier: Modifier = Modifier,
    imageWidth: Int,
    imageHeight: Int,
) {
    val cacheImageIds = remember { imageResIds }
    val context = LocalContext.current
    val density = LocalDensity.current

    Row(modifier = modifier) {
        if (imageWidth == 0 && imageHeight == 0) {
            // 自适应等宽模式：所有图片等宽，高度按原始比例自适应
            cacheImageIds.forEach { resId ->
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(resId)
                        // 使用不同的缓存键避免与固定尺寸模式冲突
                        .memoryCacheKey("img_${resId}_auto")
                        .diskCacheKey("img_${resId}_auto")
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .weight(1f)          // 水平等宽
                        .fillMaxWidth(),      // 确保图片占满分配的宽度
                    contentScale = ContentScale.FillWidth  // 宽度填满，高度按比例自适应
                )
            }
        } else {
            // 固定尺寸模式：所有图片使用指定的固定宽高
            val targetWidth = with(density) { imageWidth.dp.toPx().toInt() }
            val targetHeight = with(density) { imageHeight.dp.toPx().toInt() }
            cacheImageIds.forEach { resId ->
                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data(resId)
                        .size(targetWidth, targetHeight)
                        .memoryCacheKey("img_${resId}_${targetWidth}x$targetHeight")
                        .diskCacheKey("img_${resId}_${targetWidth}x$targetHeight")
                        .diskCachePolicy(CachePolicy.ENABLED)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier.size(imageWidth.dp, imageHeight.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}