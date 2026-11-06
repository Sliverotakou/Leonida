package com.sliver.leonida.ui.views.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.sliver.leonida.ui.viewmodels.HomeScreenViewmodel
import com.sliver.leonida.ui.views.shared.DeckleEdge
import com.sliver.leonida.ui.views.shared.PolaroidContainer
import leonida.shared.generated.resources.GTAV_SATELLITE_2048x2048
import leonida.shared.generated.resources.Res
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(
    modifier: Modifier,
    viewmodel: HomeScreenViewmodel = HomeScreenViewmodel()
) {
    val state = viewmodel.state.collectAsState()

    Scaffold(
        modifier = modifier,

        topBar = {
            HomeTopAppBar(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
                    .fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(0.3f))
            ) {


//                Image(
//                    painter = painterResource(Res.drawable.GTAV_SATELLITE_2048x2048),
//                    contentDescription = null,
//                    alpha = 0.6f,
//                    modifier = Modifier.align(Alignment.TopCenter)
//                )

                PolaroidContainer(
                    modifier = Modifier
                        .rotate(-2f)
                        .align(Alignment.TopCenter)
                        .offset(y = (80).dp, x = (-16).dp)
                        .size(280.dp),
                    content = {
                        Image(
                            painter = painterResource(Res.drawable.GTAV_SATELLITE_2048x2048),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                )

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {

                    DeckleEdge()

                    HomeScreenPresentation(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(horizontal = 24.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(0.6f)
                    )
                }

            }
        }
    }
}
