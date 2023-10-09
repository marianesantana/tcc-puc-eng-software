import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.examine_ai.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView


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
                        painterResource(id = R.drawable.ic_home),
                        contentDescription = "Home"
                    )
                },
                selected = selectedItem == 0,
                onClick = { selectedItem = 0 }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_exames),
                        contentDescription = null
                    )
                },
                selected = selectedItem == 1,
                onClick = {
                    selectedItem = 1
                    navController.navigate("exames")
                }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_consultas),
                        contentDescription = null
                    )
                },
                selected = selectedItem == 2,
                onClick = { selectedItem = 2 }
            )
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = R.drawable.ic_historico),
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


