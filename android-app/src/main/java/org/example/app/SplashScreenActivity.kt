package org.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.example.app.MainActivity
import org.example.app.R
import org.example.mpp.ServerBase

class SplashScreenActivity : AppCompatActivity() {

//    val driver: SqlDriver = AndroidSqliteDriver(ServerBase.Schema, this, "ServerDB.db")

    private val timeOut: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, timeOut)
    }
}