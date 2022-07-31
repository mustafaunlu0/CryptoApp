package com.mustafaunlu.cryptocrazyagain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.mustafaunlu.cryptocrazyagain.ui.theme.CryptoCrazyAgainTheme
import com.mustafaunlu.cryptocrazyagain.view.CryptoDetailScreen
import com.mustafaunlu.cryptocrazyagain.view.CryptoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoCrazyAgainTheme {
                //compose güncellendiğinde öğelerin tek tek yeniden oluşturulmasını engellemek için kullanılır -> remember

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "crypto_list_screen"){

                    composable("crypto_list_screen"){
                        //CryptoListScreen
                        CryptoListScreen(navController = navController)

                    }

                    composable("crypto_detail_screen/{cryptoId}/{cryptoPrice}", arguments = listOf(
                        navArgument("cryptoId"){
                            type= NavType.StringType
                        },
                        navArgument("cryptoPrice"){
                            type= NavType.StringType
                        }

                    )){
                        //CryptoDetailScreen
                        val cryptoId = remember {
                            it.arguments?.getString("cryptoId")
                        }
                        println("main ici : "+cryptoId)
                        val cryptoPrice = remember {
                            it.arguments?.getString("cryptoPrice")
                        }
                        println("main ici : "+cryptoPrice)
                        CryptoDetailScreen(id = cryptoId ?: "", price = cryptoPrice ?: "", navController = navController)
                    }
                }
            }
        }
    }
}
