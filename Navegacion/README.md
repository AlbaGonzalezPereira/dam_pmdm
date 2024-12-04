# 1. Navegación en compose primera pantalla
## 1.1. Creamos un proyecto vacio o usamos uno ya creado.

## 1.2. Eliminamos greeting y el Scarfold de la MainActivity y a la Preview le sacamos el Greeting.

## 1.3. Creamos un paquete screens a nivel de App(en el directorio principal) y dentro de ese paquete creamos una clase kotlin llamada FirstScreen.

## 1.4. En FirstScreen eliminamos la clase y ponemos una funcion compostable con el nombre de la clase FirstScreen y le ponemos un Scaffold(que es donde podemos definir la barra superor o un boton flotante y el cuerpo de la pantalla).

## 1.5. Al scaffold le ponemos una barra superior Top bar, le ponemos colores y un titulo.

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(){
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(),
            title =  { Text("Primera pantalla") }   )
        }
    ) {

    }
}
```

## 1.6. Creamos un BodyContent que es un composable 

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(){
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(),
            title =  { Text("Primera pantalla") }   )
        }
    ) {
        BodyContent(
        )

    }
}
@Composable
fun BodyContent(modifier: Modifier){
    //Añadimos el contenido de la pantalla
}
```

## 1.7. Añadimos un texto y un boton a BodyContent en una columna. Se usa column para poner los elementos uno debajo del otro y no uno encima del otro. Intenta hacerlo sin ver el codigo de abajo.

<details>
<summary>Código</summary>
<br>

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(){
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(),
            title =  { Text("Primera pantalla") }   )
        }
    ) {
        BodyContent(
            modifier = Modifier.padding(it)
            //Se pone it porque es el padding que se le pone al scaffold
        )

    }
}
@Composable
fun BodyContent(modifier: Modifier){
    //Añadimos el contenido de la pantalla
    Column{
        Text("Navegando")
        Button(
            onClick = {
                //navegar
            }
        ) {
            Text("Navegar")
        }

    }
}
```
This is how you dropdown.
</details>

## 1.8. Si ponemos en la main activity el composable de FirstScreen, se verá la pantalla con el texto y el boton.

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                FirstScreen()
            }
        }
    }
}
```
### 1.8.1. Un detalle que hay que hacer es al Column ponerle un modifier para que se vea el contenido y no este oculto debajo de la barra superior.


```kotlin
modifier = modifier.fillMaxSize(),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally,
```

## 1.9. Pero el objetivo es que tengamos una navegación no solo una pantalla.

# 2. Hacer la segnda pantalla
## 2.1. Hacemos un SecondScreen en el paquete screens y creamos una funcion composable que va a recibir un text que va a ser el texto que se va a mostrar en la pantalla y es un String que puede ser nulo.
## 2.2. Le ponemos un Scaffold con una barra superior y un cuerpo(se llamara SecondBodyContent) Que va a tener por parametro un modificador que se le pasara el padding y un texto(que es el que se le pasa a Second Screen).

<details>
<summary>Código</summary>
<br>

```kotlin
package com.example.navigation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SecondScreen(text : String?) {
    Scaffold {
        SecondBodyContent(
            modifier = Modifier.padding(it),
            text
        )
    }
}

@Composable
fun SecondBodyContent(modifier: Modifier, text: String?) {

}

```
This is how you dropdown.
</details>

## 2.3. Creamos un Column con un text que ponga "He navegado a la segunda pantalla" y un texto que visualice el texto que se le pasa a la pantalla. teniendo en cuenta que puede ser nulo asi que hay q usar let, text?.let{ Text("") }. Y crear un boton que diga "Volver" y que al pulsar el boton se vuelva a la primera pantalla.
<details>
<summary>Código</summary>
<br>

```kotlin

@Composable
fun SecondScreen(text : String?) {
    Scaffold {
        SecondBodyContent(
            modifier = Modifier.padding(it),
            text
        )
    }
}

@Composable
fun SecondBodyContent(modifier: Modifier, text: String?) {
    Column{
        Text("He navegado a la segunda pantalla")
        text?.let{
            Text("Texto: $it")
        }
        Button(onClick = {

        }) {
            Text("Volver")
        }
    }
}

```

</details>


## 2.4. Al column le ponemos el fillmaxsize y el center para que se vea el contenido y no este oculto debajo de la barra superior. Como antes


<details>
<summary>Código</summary>
<br>

```kotlin
modifier = modifier.fillMaxSize(),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
```

</details>

## 2.5. Y el topbar(topAppBar) le ponemos un titulo que diga "Segunda pantalla" y le agregamos una flecha para volver a la pantalla anterior navigation icon. el modifier le ponemos un clickable para en caso que se le de a la flecha se vuelva a la pantalla anterior.

<details>
<summary>Código</summary>
<br>

```kotlin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(text: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Segunda pantalla") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Vovler",
                        modifier = Modifier.clickable {
                            // Vovler a la pantalla anterior
                        }
                    )
                }
            )
        }
    ) {
        SecondBodyContent(
            modifier = Modifier.padding(it),
            text
        )
    }
}

@Composable
fun SecondBodyContent(modifier: Modifier, text: String?) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("He navegado a la segunda pantalla")
        text?.let {
            Text("Texto: $it")
        }
        Button(onClick = {

        }) {
            Text("Volver")
        }
    }
}
```

</details>

## 2.6. Ya tenemos las dos pantallas realizadas, ahora vamos a hacer la navegación.

# 3. Navegación entre pantallas
## 3.1. Creamos el paquete navigation a nivel de App
## 3.2. Creamos dentro una clase Kotlin llamada AppScreens, en este caso va a ser una clase, que recive por parametro un val route: String
```kotlin
/**
 * Será una clase sellada para reperentar las pantallas de la aplicacion
 */
sealed class AppScreens(val route: String) {
    object FirstScreen : AppScreens("first_screen")
    object SecondScreen : AppScreens("second_screen")
}

```

## 3.3. Crear la logica de la navegacion: Creamos en el paquete navigarion AppNavigation que es una kotlinclass
## 3.4. PAra manejar las navegaciones necesitamos un componente que se encargue de eso, lo vamos a importar.
## 3.5. Vamos a Gradle Scripts -> build.gradle(Module) y en dependencies ponemos la dependencia de navigation compose.

```kotlin
dependencies {
    val nav_version = "2.8.1"

    implementation("androidx.navigation:navigation-compose:$nav_version")
```
## 3.6. Y SINCRONIZAMOS en SYNC NOW
## 3.7. Vovlemos a AppNavigation y será una función compostable
## 3.8. Creamos un rememberNavController que es un componente que se encarga de la navegación.
```kotlin
val navController = rememberNavController()
```
## 3.9. Añadimos un NavHost que es el contenedor de las pantallas y le pasamos el navController.
```kotlin
NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
}
```
## 3.10. Añadimos las pantallas de la aplicacion en NavHost, FisrtScreen y SecondScreen son de screens IMPORTANTE AL IMPORTARLOS
```kotlin
NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
    composable(AppScreens.FirstScreen.route) {
        FirstScreen()
    }
    composable(AppScreens.SecondScreen.route) {
        SecondScreen()
    }
}
```

## 3.11. Si quiesaromos pasar un texto a la segunda pantalla lo hariamos asi
```kotlin
NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route) {
    composable(AppScreens.FirstScreen.route) {
        FirstScreen()
    }
    composable(AppScreens.SecondScreen.route + "/{text}",arguments = listOf(navArgument(name="text"){
        type = NavType.StringType
    })) {
        SecondScreen(it.arguments?.getString("text"))
    }
}
```
# 4. Aplicar lógica a los botones
## 4.1. En firstScreen en el BodyContent reciba un navController, tanto en la declaracion como en la llamada y en FirstScreen tambien tiene que recibirlo.

<details>
<summary>Código</summary>
<br>

```kotlin
package com.example.navigation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(),
            title =  { Text("Primera pantalla") }   )
        }
    ) {
        BodyContent(
            modifier = Modifier.padding(it),
            navController
        )

    }
}
@Composable
fun BodyContent(modifier: Modifier,navController: NavController){
    //Añadimos el contenido de la pantalla
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text("Navegando")
        Button(
            onClick = {
                //navegar
            }
        ) {
            Text("Navegar")
        }

    }
}
```

</details>

## 4.2. Modificamos el onclick del boton de BodyContent para que navegue a la segunda pantalla
```kotlin
Button(
    onClick = {
        navController.navigate(AppScreens.SecondScreen.route + "/Esto es un parametro")
    }
) {
    Text("Navegar")
}
```

## 4.3. Como le pasamos el navController a FirstScreen lo tenemos que modificar en AppNavigation
```kotlin
FirstScreen(navController)
```


## 4.4. En mainActivity en el setContent dentro del tema ponemos AppNavigation()
```kotlin
setContent {
    NavigationTheme {
        AppNavigation()
    }
}
```
## 4.5. Ya se puede probar la primera pantalla 

## 4.6. Hacemos al segunda pantalla
## 4.7. En SecondScreen pasamos el navController y en el onClick del icono de volver ponemos navController.popBackStack() ya que es una pila
```kotlin
Icon(
    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
    contentDescription = "Vovler",
    modifier = Modifier.clickable {
        navController.popBackStack()
    }
)
```
## 4.8. Se lo pasamos tambien a SecondBodyContent
```kotlin
SecondBodyContent(
    modifier = Modifier.padding(it),
    navController,
    text
)
```
@Composable
fun SecondScreen(modifier:Modifier,navController: NavController,text: String?) {
```
## El resultado final es este
```kotlin
package com.example.navigation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController:NavController,text: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Segunda pantalla") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Vovler",
                        modifier = Modifier.clickable {
                            // Vovler a la pantalla anterior
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        SecondBodyContent(
            modifier = Modifier.padding(it),
            navController,
            text
        )
    }
}

@Composable
fun SecondBodyContent(modifier: Modifier,navController: NavController, text: String?) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("He navegado a la segunda pantalla")
        text?.let {
            Text("Texto: $it")
        }
        Button(onClick = {
            navController.popBackStack() //Vuelve a la anterior
        }) {
            Text("Volver")
        }
    }
}

```

## 4.9. Por ultimo en AppNavigation hay que pasar en el SecondScreen el navController
```kotlin
SecondScreen(navController,it.arguments?.getString("text"))
```

# 5. Probamos la app y ya deberia funcionar la navegación entre pantallas.
github del proyecto:
https://github.com/resuadam2/TutorialCompose/tree/master/navigation

