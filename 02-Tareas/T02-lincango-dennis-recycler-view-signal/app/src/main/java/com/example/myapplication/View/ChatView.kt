package com.example.myapplication.View

import com.example.myapplication.Adaptador.ChatAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Entity.Chat
import com.example.myapplication.R


class ChatView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)

        val chatList = getSampleChatList()
        val chatRecyclerView: RecyclerView = findViewById(R.id.chatRecyclerView)
        val chatAdapter = ChatAdapter(chatList)

        chatRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChatView)
            adapter = chatAdapter
        }

        val botonStatus = findViewById<ImageButton>(R.id.imageButtonStories)
        botonStatus.setOnClickListener {
            irActividad(StatusView::class.java)
        }

    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent, ActivityOptionsCompat.makeCustomAnimation(this, 0, 0).toBundle())
    }


    private fun getSampleChatList(): List<Chat> {
        return listOf(
            Chat("Andrea", "¡Hola! ¿Cómo estás?", "https://picsum.photos/seed/1/200/300"),
            Chat("Camila", "¡Hola! Estoy bien, ¿y tú?", "https://picsum.photos/seed/2/200/300"),
            Chat("David", "¡Hola a todos!", "https://picsum.photos/seed/3/200/300"),
            Chat("Laura", "Hola, ¿alguien quiere tomar un café?", "https://picsum.photos/seed/4/200/300"),
            Chat("Carlos", "¡Hola mundo!", "https://picsum.photos/seed/5/200/300"),
            Chat("María", "¡Buenas tardes!", "https://picsum.photos/seed/6/200/300"),
            Chat("Alejandro", "¡Hola chicos! ¿Qué han estado haciendo?", "https://picsum.photos/seed/7/200/300"),
            Chat("Isabel", "Hola a todos, ¿qué tal el día?", "https://picsum.photos/seed/8/200/300"),
            Chat("Juan", "¡Hola! ¿Cómo les va?", "https://picsum.photos/seed/9/200/300"),
            Chat("Elena", "¡Hola amigos! ¿Qué hay de nuevo?", "https://picsum.photos/seed/10/200/300"),
            Chat("Pedro", "Hola a todos, ¿qué planes tienen para el fin de semana?", "https://picsum.photos/seed/11/200/300"),
            Chat("Sofía", "¡Buen día! Espero que tengan un excelente día.", "https://picsum.photos/seed/12/200/300"),
            Chat("Miguel", "¡Hola! ¿Alguien ha visto la última película de superhéroes?", "https://picsum.photos/seed/13/200/300"),
            Chat("Lucía", "¡Hola a todos! ¿Qué opinan sobre el nuevo restaurante que abrió cerca?", "https://picsum.photos/seed/14/200/300"),
            Chat("Hugo", "¡Hola! ¿Alguien quiere unirse al juego de fútbol el sábado?", "https://picsum.photos/seed/15/200/300"),
            Chat("Ana", "¡Hola! ¿Qué tal fue su fin de semana?", "https://picsum.photos/seed/16/200/300"),
            Chat("Diego", "Hola a todos, ¿qué les parece si organizamos una salida al parque?", "https://picsum.photos/seed/17/200/300"),
            Chat("Valentina", "¡Hola amigos! ¿Cómo va todo?", "https://picsum.photos/seed/18/200/300"),
            Chat("Roberto", "¡Hola! ¿Alguien sabe cómo resolver este problema en Android?", "https://picsum.photos/seed/19/200/300"),
            Chat("Carolina", "¡Buenas tardes a todos! ¿Qué han estado haciendo?", "https://picsum.photos/seed/20/200/300"),
            Chat("Fernando", "¡Hola! ¿Alguien quiere unirse al club de lectura?", "https://picsum.photos/seed/21/200/300"),
            Chat("Paola", "¡Hola a todos! ¿Qué planes tienen para el verano?", "https://picsum.photos/seed/22/200/300"),
            Chat("Luis", "¡Hola! ¿Cómo están todos?", "https://picsum.photos/seed/chat1/200/300"),
            Chat("Gabriela", "¡Hola amigos! ¿Qué tal su día?", "https://picsum.photos/seed/chat2/200/300"),
            Chat("Ricardo", "¡Buenas tardes a todos!", "https://picsum.photos/seed/chat3/200/300"),
            Chat("Daniela", "¡Hola a todos! ¿Qué han estado haciendo?", "https://picsum.photos/seed/chat4/200/300"),
            Chat("José", "¡Hola chicos! ¿Cómo va todo?", "https://picsum.photos/seed/chat5/200/300"),
            Chat("Verónica", "¡Hola a todos! ¿Qué planes tienen para el fin de semana?", "https://picsum.photos/seed/chat6/200/300"),
            Chat("Fernando", "¡Hola! ¿Alguien quiere unirse al juego de fútbol el sábado?", "https://picsum.photos/seed/chat7/200/300"),
            Chat("Monica", "¡Hola! ¿Qué tal fue su fin de semana?", "https://picsum.photos/seed/chat8/200/300"),
            Chat("Gustavo", "Hola a todos, ¿qué les parece si organizamos una salida al parque?", "https://picsum.photos/seed/chat9/200/300"),
            Chat("Mariana", "¡Hola amigos! ¿Cómo va todo?", "https://picsum.photos/seed/chat10/200/300"),
            Chat("Pablo", "¡Hola! ¿Alguien sabe cómo resolver este problema en Android?", "https://picsum.photos/seed/chat11/200/300"),
            Chat("Natalia", "¡Buenas tardes a todos! ¿Qué han estado haciendo?", "https://picsum.photos/seed/chat12/200/300"),
            Chat("Andrés", "¡Hola! ¿Alguien quiere unirse al club de lectura?", "https://picsum.photos/seed/chat13/200/300"),
            Chat("Valeria", "¡Hola a todos! ¿Qué planes tienen para el verano?", "https://picsum.photos/seed/chat14/200/300"),
            Chat("Hernán", "¡Hola! ¿Han probado el nuevo restaurante de sushi?", "https://picsum.photos/seed/chat15/200/300"),
            Chat("Sandra", "¡Hola amigos! ¿Qué opinan sobre la última película de ciencia ficción?", "https://picsum.photos/seed/chat16/200/300"),
            Chat("Mateo", "¡Hola a todos! ¿Alguien ha leído algún buen libro recientemente?", "https://picsum.photos/seed/chat17/200/300"),
            Chat("Renata", "¡Buen día! ¿Cómo va su proyecto?", "https://picsum.photos/seed/chat18/200/300"),
            Chat("Emilio", "¡Hola! ¿Han probado el nuevo café del centro?", "https://picsum.photos/seed/chat19/200/300"),
            Chat("Carla", "¡Hola a todos! ¿Qué tal su semana?", "https://picsum.photos/seed/chat20/200/300")
        )
    }

}
