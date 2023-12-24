package com.normal.normalae

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.normal.normalae.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var bind: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivitySignupBinding.inflate(layoutInflater)
        val view = bind.root
        setContentView(view)

        bind.btnSignup.setOnClickListener {
            if(bind.txtUName.text.isEmpty() || bind.txtPwd.text.isEmpty() || bind.txtPwdConfirmation.text.isEmpty()){
                Toast.makeText(this, "Fill in the empty fields", Toast.LENGTH_SHORT).show()
            }
            else if(bind.txtPwd.text.toString() == bind.txtPwdConfirmation.text.toString()){
                var user = User(bind.txtUName.text.toString(), bind.txtPwd.text.toString())
                val q = Volley.newRequestQueue(this)
                val url = "https://ubaya.me/native/160421044/set_likes.php"
                val stringRequest = object : StringRequest(Request.Method.POST, url,
                    Response.Listener {
                        Log.d("cekparams", it)
                    },
                    Response.ErrorListener {
                        Log.d("cekparams", it.message.toString())
                    })
                {
                    override fun getParams(): MutableMap<String, String> {
                        val params = HashMap<String, String>()
                        params["username"] = bind.txtUName.text.toString()
                        params["password"] = bind.txtPwd.text.toString()
                        params["url"] = bind.txtUrlPP.text.toString()

                        return params
                    }
                }
                q.add(stringRequest)
//                if(!Global.users.contains(user)){
//                    Global.users.add(User(bind.txtUName.text.toString(), bind.txtPwd.text.toString()))
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                }
//                else{
//                    Toast.makeText(this, "User already exist.", Toast.LENGTH_SHORT).show()
//                }
            }
            else{
                Toast.makeText(this, "Passwords does not match.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}