package com.example.princeapp
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittlePrinceApp()
        }
    }
}

@Composable
fun LittlePrinceApp() {
    val context = LocalContext.current
    var currentTime by remember { mutableStateOf("morning") }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {

        // РЕЖИМЫ
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Кнопка УТРО
            Button(onClick = {
                currentTime = "morning"
                // Уведомление прямо здесь (как в Java-примере)
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channelId = "my_channel"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
                    manager.createNotificationChannel(channel)
                }
                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Утро")
                    .setContentText("Привести в порядок свою планету")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                manager.notify(1, builder.build())
            }) {
                Text("УТРО")
            }

            // Кнопка ДЕНЬ
            Button(onClick = {
                currentTime = "day"
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channelId = "my_channel"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
                    manager.createNotificationChannel(channel)
                }
                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("День")
                    .setContentText("Полить розу")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                manager.notify(1, builder.build())
            }) {
                Text("ДЕНЬ")
            }

            // Кнопка ВЕЧЕР
            Button(onClick = {
                currentTime = "evening"
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channelId = "my_channel"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
                    manager.createNotificationChannel(channel)
                }
                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Вечер")
                    .setContentText("Закрыть розу ширмой")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                manager.notify(1, builder.build())
            }) {
                Text("ВЕЧЕР")
            }

            // Кнопка НОЧЬ
            Button(onClick = {
                currentTime = "night"
                val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channelId = "my_channel"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(channelId, channelId, NotificationManager.IMPORTANCE_DEFAULT)
                    manager.createNotificationChannel(channel)
                }
                val intent = Intent(context, MainActivity::class.java)
                val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
                val builder = NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(android.R.drawable.ic_dialog_info)
                    .setContentTitle("Ночь")
                    .setContentText("Полюбоваться звездами")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                manager.notify(1, builder.build())
            }) {
                Text("НОЧЬ")
            }
        }

        // КАРТИНКИ
        Box(modifier = Modifier.fillMaxSize()) {
            val imageRes = when (currentTime) {
                "morning" -> R.drawable.morning
                "day" -> R.drawable.day
                "evening" -> R.drawable.evening
                else -> R.drawable.night
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Локация",
                modifier = Modifier.fillMaxSize()
            )

            // ===== НЕВИДИМЫЕ КНОПКИ (только для УТРА) =====
            if (currentTime == "morning") {

                // 1. Маленький принц (верх)
                Box(modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .fillMaxHeight(0.3f)
                    .align(Alignment.TopCenter)
                    .clickable {
                        Toast.makeText(context, "Маленький принц", Toast.LENGTH_LONG).show()
                    }
                )

                // 2. Планета (центр)
                Box(modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.3f)
                    .align(Alignment.Center)
                    .clickable {
                        Toast.makeText(context, "Планета на которой живет Маленький принц", Toast.LENGTH_LONG).show()
                    }
                )

                // 3. Вулкан (слева)
                Box(modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.2f)
                    .align(Alignment.CenterStart)
                    .clickable {
                        Toast.makeText(context, "Это вулкан", Toast.LENGTH_LONG).show()
                    }
                )

                // 4. Роза (справа)
                Box(modifier = Modifier
                    .fillMaxWidth(0.3f)
                    .fillMaxHeight(0.2f)
                    .align(Alignment.CenterEnd)
                    .clickable {
                        Toast.makeText(context, "Её нужно поливать, а на ночь укрывать ширмой и колпаком", Toast.LENGTH_LONG).show()
                    }
                )

                // 5. Завтрак (низ)
                Box(modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight(0.2f)
                    .align(Alignment.BottomCenter)
                    .clickable {
                        Toast.makeText(context, "Действующий вулкан, на нем удобно разогревать завтрак", Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }
}








