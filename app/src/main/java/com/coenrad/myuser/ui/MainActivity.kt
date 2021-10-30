package com.coenrad.myuser.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.coenrad.myuser.R
import com.coenrad.myuser.model.Users
import com.coenrad.myuser.network.UsersRetriever
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private val usersRetriever: UsersRetriever = UsersRetriever()
    private lateinit var usersFetchJob: Job

    override val coroutineContext: CoroutineContext
        get() = usersFetchJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersFetchJob = Job()
        searchButton.setOnClickListener {
            launch {
                fetchUser(userIdEditText.text.toString().toInt())
            }
        }
    }

    private fun fetchUser(id: Int) {

        val errorHandler = CoroutineExceptionHandler{ _, throwable ->
            Toast.makeText(this, "Error ", Toast.LENGTH_SHORT).show()
            throwable.printStackTrace()
        }

        val scope = CoroutineScope(usersFetchJob + Dispatchers.Main)
        scope.launch(errorHandler){
            val user: Users = usersRetriever.getUsers(id)

            idTextView.text = "${user.id}"
            nameTextView.text = user.name
            usernameTextView.text = user.username
            emailTextView.text = user.email
            addressTextView.text = user.address.toString()
            phoneTextView.text = user.phone
            websiteTextView.text = user.website
            companyTextView.text = user.company.toString()
        }
    }

}