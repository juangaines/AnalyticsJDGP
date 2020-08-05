package com.example.analyticsjdgp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.analyticsjdgp.databinding.ActivityPDFViewerBinding

class ActivityPDFViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_p_d_f_viewer)

        val binding:ActivityPDFViewerBinding = DataBindingUtil.setContentView(this, R.layout.activity_p_d_f_viewer)
        binding.webView.settings.javaScriptEnabled=true
        intent?.let {
            if (it.hasExtra("url_key")){
                val url=it.getStringExtra("url_key")
                binding.webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$url")
            }
        }
    }
}