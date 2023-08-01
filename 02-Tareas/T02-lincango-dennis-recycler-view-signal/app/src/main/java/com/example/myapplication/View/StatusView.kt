package com.example.myapplication.View

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Entity.Status
import com.example.myapplication.Adaptador.StatusAdapter

class StatusView : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        val statusList = getSampleStatusList() // Aquí obtén la lista de mensajes de chat (puedes usar la lista que definimos anteriormente)

        val statusRecyclerView: RecyclerView = findViewById(R.id.statusRecyclerView)
        val statusAdapter = StatusAdapter(statusList)

        statusRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@StatusView)
            adapter = statusAdapter
        }

        val botonChat = findViewById<ImageButton>(R.id.imageButtonChat)
        botonChat.setOnClickListener {
            irActividad(ChatView::class.java)
        }

    }

    private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent, ActivityOptionsCompat.makeCustomAnimation(this, 0, 0).toBundle())
    }

    private fun getSampleStatusList(): List<Status> {
        return listOf(
            Status("https://i.postimg.cc/RCjY422M/Add-Status.png",
                "My Stories", "8m", "https://picsum.photos/200/300"),
            Status("https://picsum.photos/id/10/200/", "Victoria", "10m", "https://picsum.photos/id/70/200/300"),
            Status("https://picsum.photos/id/11/200/", "Andrew", "15m", "https://picsum.photos/id/71/200/300"),
            Status("https://picsum.photos/id/12/200/300", "Scarlett", "20m", "https://picsum.photos/id/72/200/300"),
            Status("https://picsum.photos/id/13/200/300", "Aiden", "22m", "https://picsum.photos/id/73/200/300"),
            Status("https://picsum.photos/id/14/200/300", "Madison", "22m", "https://picsum.photos/id/74/200/300"),
            Status("https://picsum.photos/id/15/200/300", "Gabriel", "2m", "https://picsum.photos/id/75/200/300"),
            Status("https://picsum.photos/id/16/200/300", "Lillian", "5m", "https://picsum.photos/id/76/200/300"),
            Status("https://picsum.photos/id/17/200/300", "Carter", "0m", "https://picsum.photos/id/77/200/300"),
            Status("https://picsum.photos/id/18/200/300", "Elizabeth", "2m", "https://picsum.photos/id/78/200/300"),
            Status("https://picsum.photos/id/19/200/300", "Henry", "5m", "https://picsum.photos/id/79/200/300"),
            Status("https://picsum.photos/id/20/200/300", "Chloe", "55m", "https://picsum.photos/id/80/200/300"),
            Status("https://picsum.photos/id/21/200/300", "Ryan", "5m", "https://picsum.photos/id/81/200/300"),
            Status("https://picsum.photos/id/22/200/300", "Grace", "5m", "https://picsum.photos/id/82/200/300"),
            Status("https://picsum.photos/id/23/200/300", "Nolan", "59m", "https://picsum.photos/id/83/200/300"),
            Status("https://picsum.photos/id/24/200/300", "Zoe", "1h", "https://picsum.photos/id/84/200/300"),
            Status("https://picsum.photos/id/25/200/300", "Leo", "1h", "https://picsum.photos/id/85/200/300"),
            Status("https://picsum.photos/id/27/200/300", "Lucy", "2h", "https://picsum.photos/id/87/200/300"),
            Status("https://picsum.photos/id/28/200/300", "Owen", "2h", "https://picsum.photos/id/88/200/300"),
            Status("https://picsum.photos/id/29/200/300", "Aria", "2h", "https://picsum.photos/id/89/200/300"),
            Status("https://picsum.photos/id/30/200/300", "Liam", "h", "https://picsum.photos/id/90/200/300"),
            Status("https://picsum.photos/id/31/200/300", "Emily", "h", "https://picsum.photos/id/91/200/300"),
            Status("https://picsum.photos/id/32/200/300", "Noah", "h", "https://picsum.photos/id/92/200/300"),
            Status("https://picsum.photos/id/33/200/300", "Abigail", "h", "https://picsum.photos/id/93/200/300"),
            Status("https://picsum.photos/id/34/200/300", "Elijah", "5h", "https://picsum.photos/id/94/200/300"),
            Status("https://picsum.photos/id/35/200/300", "Aaliyah", "5h", "https://picsum.photos/id/95/200/300"),
            Status("https://picsum.photos/id/36/200/300", "Mason", "5h", "https://picsum.photos/id/96/200/300"),
            Status("https://picsum.photos/id/38/200/300", "Olivia", "h", "https://picsum.photos/id/98/200/300"),
            Status("https://picsum.photos/id/39/200/300", "Alexander", "h", "https://picsum.photos/id/99/200/300"),
            Status("https://picsum.photos/id/40/200/300", "Aria", "h", "https://picsum.photos/id/100/200/300"),
            Status("https://picsum.photos/id/41/200/300", "Daniel", "9h", "https://picsum.photos/id/101/200/300"),
            Status("https://picsum.photos/id/42/200/300", "Sofia", "9h", "https://picsum.photos/id/102/200/300"),
            Status("https://picsum.photos/id/43/200/300", "William", "9h", "https://picsum.photos/id/103/200/300"),
            Status("https://picsum.photos/id/44/200/300", "Ava", "9h", "https://picsum.photos/id/104/200/300"),
            Status("https://picsum.photos/id/46/200/300", "Emma", "11h", "https://picsum.photos/id/106/200/300"),
            Status("https://picsum.photos/id/47/200/300", "Oliver", "11h", "https://picsum.photos/id/107/200/300"),
            Status("https://picsum.photos/id/48/200/300", "Isabella", "11h", "https://picsum.photos/id/108/200/300"),
            Status("https://picsum.photos/id/49/200/300", "James", "1h", "https://picsum.photos/id/109/200/300"),
            Status("https://picsum.photos/id/50/200/300", "Sophia", "1h", "https://picsum.photos/id/110/200/300"),
            Status("https://picsum.photos/id/51/200/300", "Jackson", "1h", "https://picsum.photos/id/111/200/300"),
            Status("https://picsum.photos/id/53/200/300", "Evelyn", "1h", "https://picsum.photos/id/113/200/300"),
            Status("https://picsum.photos/id/54/200/300", "Lucas", "1h", "https://picsum.photos/id/114/200/300"),
            Status("https://picsum.photos/id/55/200/300", "Aria", "1h", "https://picsum.photos/id/115/200/300"),
            Status("https://picsum.photos/id/56/200/300", "Ethan", "1h", "https://picsum.photos/id/116/200/300"),
            Status("https://picsum.photos/id/57/200/300", "Madison", "1h", "https://picsum.photos/id/117/200/300"),
            Status("https://picsum.photos/id/58/200/300", "Henry", "19h", "https://picsum.photos/id/118/200/300"),
            Status("https://picsum.photos/id/59/200/300", "Scarlett", "19h", "https://picsum.photos/id/119/200/300"),
            Status("https://picsum.photos/id/60/200/300", "Michael", "20h", "https://picsum.photos/id/120/200/300"),
            Status("https://picsum.photos/id/61/200/300", "Mia", "21h", "https://picsum.photos/id/121/200/300"),
            Status("https://picsum.photos/id/62/200/300", "Carter", "21h", "https://picsum.photos/id/122/200/300"),
            Status("https://picsum.photos/id/63/200/300", "Luna", "22h", "https://picsum.photos/id/123/200/300"),
            Status("https://picsum.photos/id/64/200/300", "Liam", "22h", "https://picsum.photos/id/124/200/300"),
            Status("https://picsum.photos/id/65/200/300", "Emily", "22h", "https://picsum.photos/id/125/200/300"),
            Status("https://picsum.photos/id/66/200/300", "Daniel", "2h", "https://picsum.photos/id/126/200/300"),
            Status("https://picsum.photos/id/67/200/300", "Olivia", "2h", "https://picsum.photos/id/127/200/300"),
            Status("https://picsum.photos/id/68/200/300", "Mason", "2h", "https://picsum.photos/id/128/200/300"),
            Status("https://picsum.photos/id/69/200/300", "Aurora", "2h", "https://picsum.photos/id/129/200/300")
        )
    }
}