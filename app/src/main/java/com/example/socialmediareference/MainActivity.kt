package com.example.socialmediareference

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    val MORE_DETAILS_REQUEST_CODE: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shareButton = findViewById<ImageButton>(R.id.shareButton)
        val sendButton = findViewById<ImageButton>(R.id.sendButton)
        val moreDetailsButton = findViewById<ImageButton>(R.id.moreDetailsButton)
        val callButton = findViewById<ImageButton>(R.id.callButton)

        val descriptionTextView = findViewById<TextView>(R.id.description)
        val historyTextView = findViewById<TextView>(R.id.history)

        shareButton.setOnClickListener {
            val imageUri = Uri.parse("https://caymanasgolf.com/wp-content/uploads/2018/01/green-at-hole-6-1024x680.jpg")

            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
            shareIntent.putExtra(Intent.EXTRA_TEXT, historyTextView.text.toString())
            startActivity(Intent.createChooser(shareIntent, "Share Image"))
        }

        sendButton.setOnClickListener {
            val phoneNumber = "+18762567890"
            val message = descriptionTextView.text.toString()
            val sendIntent = Intent(Intent.ACTION_SENDTO)
            sendIntent.data = Uri.parse("smsto:$phoneNumber")
            sendIntent.putExtra("sms_body", message)
            startActivity(sendIntent)
        }

        callButton.setOnClickListener {
            val phoneNumber = "+18762567890"
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(callIntent)
        }

        moreDetailsButton.setOnClickListener {
            val intent = Intent(this, MoreDetailsActivity::class.java)
            intent.putExtra("title", descriptionTextView.text.toString())
            intent.putExtra("description", historyTextView.text.toString())
            startActivityForResult(intent, requestCode = this.MORE_DETAILS_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == this.MORE_DETAILS_REQUEST_CODE && resultCode == RESULT_OK) {
            //do something to handle this specific request

            val title = data?.getStringExtra("title")
            val description = data?.getStringExtra("description")

            Toast.makeText(this, "More Details Activity Returned", Toast.LENGTH_SHORT).show()
        } else if(resultCode == RESULT_CANCELED) {
            //do something to handle this specific request
            Toast.makeText(this, "More Details Activity Cancelled", Toast.LENGTH_SHORT).show()
        } else {
            //do something to handle this specific request
        }
    }

}