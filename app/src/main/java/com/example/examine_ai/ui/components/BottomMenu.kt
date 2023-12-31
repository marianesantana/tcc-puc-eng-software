
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examine_ai.R
import com.example.examine_ai.domain.utils.Destinos


@Composable
fun BottomMenu(navController: NavController) {
    var selectedItem by remember { mutableIntStateOf(0) }

     BottomNavigation(
            backgroundColor = Color.White,
            contentColor = Color.Black,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            BottomNavigationItem(
                icon = {
                    Icon(
                        Icons.Filled.Home,
                        tint = Color.Black,
                        contentDescription = "Home"
                    )
                },
                selected = selectedItem == 0,
                onClick = {
                    selectedItem = 0
                    navController.navigate(Destinos.HOME)

                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_text_snippet_24),
                        contentDescription = null
                    )
                },
                selected = selectedItem == 1,
                onClick = {
                    selectedItem = 1
                    navController.navigate(Destinos.LISTA_EXAMES)
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_date_range_24),
                        contentDescription = null
                    )
                },
                selected = selectedItem == 2,
                onClick = { selectedItem = 2 }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.baseline_person_24),
                        contentDescription = null
                    )
                },
                selected = selectedItem == 3,
                onClick = { selectedItem = 3 }
            )
        }
    }

@Preview
@Composable
fun BottomMenuView(){
   var navController = rememberNavController()
    BottomMenu(navController = navController)
}


