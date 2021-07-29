package com.Deliciouspoint.Delicious.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.Deliciouspoint.Delicious.R
import com.Deliciouspoint.Delicious.fragment.LoginFragment


class LoginRegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)

        val sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences),
            Context.MODE_PRIVATE
        )

        //If already logged in once, directly open DashboardActivity
        if (sharedPreferences.getBoolean("isLoggedIn", false)) {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            openLoginFragment()
        }
    }

    fun openLoginFragment() {
        supportFragmentManager.beginTransaction().replace(
            R.id.frameLayout,
            LoginFragment(this)
        ).commit()

        supportActionBar?.title = "DashBoard"
    }

    override fun onBackPressed() {
        when (supportFragmentManager.findFragmentById(R.id.frameLayout)) {
            !is LoginFragment -> openLoginFragment()
            is LoginFragment -> {
                val dialog = AlertDialog.Builder(this@LoginRegisterActivity)
                dialog.setTitle("Confirmation")
                dialog.setMessage("Exit the app?")
                dialog.setPositiveButton("Yes"){text, listener ->
//                    this.finish()
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    startActivity(intent)
                }
                dialog.setNegativeButton("No"){text, listener ->
                }
                dialog.create()
                dialog.show()
        }
            else -> super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                openLoginFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
