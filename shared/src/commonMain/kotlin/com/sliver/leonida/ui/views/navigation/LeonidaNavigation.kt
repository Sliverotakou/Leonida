package com.sliver.leonida.ui.views.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sliver.leonida.ui.views.home.HomeScreen
import com.sliver.leonida.ui.views.map.MapScreen

@Composable
fun LeonidaNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationManager = NavigationManager
    val navigationState = navigationManager.state.collectAsState().value

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp),
                horizontalArrangement = Arrangement.End
            ) {
                OrigamiNavButton(
                    onClick = {
                        navigationManager.updateNavigationDialogVisibility(!navigationState.showNavigationDialog)
                    },
                )
            }
        }
    ) { paddingValues ->


        Box(
            modifier = modifier.padding(paddingValues)
        ) {

            NavHost(
                navController = navController,
                startDestination = Route.Home.name,
            ) {
                composable(
                    route = Route.Home.name
                ) {
                    HomeScreen(
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                composable(
                    route = Route.Map.name
                ) {
                    MapScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                composable(
                    route = Route.Book.name
                ) {
                    Scaffold { }
                }

            }

            val orientation = remember { mutableStateOf(0f) }
            LaunchedEffect(navigationState.showNavigationDialog) {
                if (navigationState.showNavigationDialog) orientation.value =
                    -2f else orientation.value = 15f
            }

            val dialogOrientationAnimation = animateFloatAsState(
                targetValue = orientation.value,
                animationSpec = MaterialTheme.motionScheme.slowSpatialSpec()
            )

            AnimatedVisibility(
                visible = navigationState.showNavigationDialog,
                enter = slideInHorizontally { it } + scaleIn(
                    initialScale = 0.3f,
                    animationSpec = MaterialTheme.motionScheme.fastSpatialSpec()
                ),
                exit = slideOutHorizontally { it } + scaleOut(
                    targetScale = 0.95f,
                    animationSpec = MaterialTheme.motionScheme.fastSpatialSpec()
                )
            ) {
                val interactionSource = remember { MutableInteractionSource() }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            navigationManager.updateNavigationDialogVisibility(false)
                        }
                ) {
                    NavButtonDialog(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .rotate(dialogOrientationAnimation.value)
                            .padding(horizontal = 16.dp, vertical = 12.dp)
                            .background(MaterialTheme.colorScheme.surface)
                            .shadow(16.dp),
                        currentRoute = currentRoute!!,
                        navigateTo = {
                            // Navigate to destination and hide des navigationDialog
                                route ->
                            if (route == navController.currentDestination!!.route) {
                                navigationManager.updateNavigationDialogVisibility(false)
                            } else {
                                navController.navigate(route)
                                navigationManager.updateNavigationDialogVisibility(false)
                            }
                        },
                        routes = Route.entries.toList()
                    )
                }

            }
        }
    }
}