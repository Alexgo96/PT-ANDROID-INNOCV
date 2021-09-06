package com.example.ptandroidinnocv

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGet = findViewById<Button>(R.id.btn_get)
        val listView = findViewById<ListView>(R.id.lv_users)

        btnGet.setOnClickListener{
            val queue = Volley.newRequestQueue(this)
            val url = "https://hello-world.innocv.com/api/User"

            val stringRequest = StringRequest(url,
                Response.Listener{ response ->
                    Log.d("LOG_TAG", "Response is: $response")

                    //A partir de aqui sirve
                    var users: MutableList<User> = mutableListOf()
                    var jsonArray = JSONArray(response)
                    for (jsonIndex in 0..(jsonArray.length() - 1)) {
                        val name = jsonArray.getJSONObject(jsonIndex).getString("name")
                        val birthday = jsonArray.getJSONObject(jsonIndex).getString("birthdate")
                        val id = jsonArray.getJSONObject(jsonIndex).getString("id")
                        users.add(User(Integer.parseInt(id), name, birthday))
                    }
                    //Modify the ListView
                    listView.adapter = UsersAdapter(this, users)
                     //YA NO SIRVE

                },

                Response.ErrorListener { error ->
                    error.printStackTrace()
                }
            )
            queue.add(stringRequest)
        }
    }
}
