package com.mehmetaliocak.movie_app.android.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun LoadMoreTextView(text: String, lineLimit: Int) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(Modifier.padding(8.dp)) {
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            maxLines = if (expanded) Int.MAX_VALUE else lineLimit
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = if (expanded) "Collapse" else "Load more",
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .align(Alignment.End)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    expanded = !expanded
                },
            textDecoration = TextDecoration.Underline,
            color = Color.Blue
        )
    }
}