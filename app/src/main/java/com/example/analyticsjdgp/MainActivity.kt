package com.example.analyticsjdgp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.analyticsjdgp.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {


    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        huaweiAnalyticsInstance.setUserProfile("user_key_for_app_jd", "Usuario prueba")
        huaweiAnalyticsInstance.setUserProfile("edad", "23")
        huaweiAnalyticsInstance.setUserProfile("prefered_brand", "Adidas")

        binding.reportEvent.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("brand", "Adidas")
            bundle.putDouble("price", 147000.0)
            bundle.putString("size", "10")
            huaweiAnalyticsInstance.onEvent("Purchase", bundle)
            Toast.makeText(this, "Reported Event", Toast.LENGTH_SHORT).show()
        }

        binding.testCrash.setOnClickListener {
            crashInstance.testIt(applicationContext)
        }

        binding.collectOff.setOnClickListener {
            crashInstance.enableCrashCollection(false)
            Toast.makeText(this, "Collector off", Toast.LENGTH_SHORT).show()
        }
        binding.collectOn.setOnClickListener {
            crashInstance.enableCrashCollection(true)
            Toast.makeText(this, "Collector on", Toast.LENGTH_SHORT).show()
        }
        binding.indexException.setOnClickListener {
            val array = Array(5) { 0 }
            for (i in 0..array.size + 1) {
                array[i]
            }
        }

        binding.openPdf.setOnClickListener {
            val url="http://www.pdf995.com/samples/pdf.pdf"
            val sendIntent = Intent().apply {
                setDataAndType(
                    Uri.parse(url),
                    "application/pdf"
                )
            }
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(sendIntent)
            } else {
                val browserIntent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
                startActivity(browserIntent)
            }
        }
        binding.checkOut.setOnClickListener {
            val intent= Intent(this,CheckoutActivity::class.java)
            startActivity(intent)
        }
        binding.openPdfActivity.setOnClickListener {
            val intent= Intent(this,ActivityPDFViewer::class.java)
            intent.putExtra("url_key","http://www.pdf995.com/samples/pdf.pdf")
            startActivity(intent)
        }
    }
}
