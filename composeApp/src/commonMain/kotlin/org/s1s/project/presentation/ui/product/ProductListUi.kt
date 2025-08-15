package org.s1s.project.presentation.ui.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.s1s.project.domain.model.ProductDomain


@Composable
fun ProductListUi(products: List<ProductDomain>, onProductClick: (String) -> Unit) {
    LazyColumn {
        items(products) { product ->
            ProductListItem(
                product = product,
                onClick = { onProductClick(product.id) }
            )
        }
    }
}

@Composable
fun ProductListItem(product: ProductDomain, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(
            text = product.name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 2
        )
    }
}
