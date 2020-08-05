package com.example.analyticsjdgp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.huawei.hms.analytics.type.HAEventType.COMPLETEPURCHASE
import com.huawei.hms.analytics.type.HAEventType.VIEWPRODUCT
import com.huawei.hms.analytics.type.HAParamType.*
import java.util.*


class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()

    private lateinit var checkoutActivity:CheckoutActivity


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)

        checkoutActivity=activity as CheckoutActivity
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val idText: TextView = view.findViewById(R.id.id_label)
        val buyButton: TextView = view.findViewById(R.id.button_payment)
        val amount = args.id
        idText.text = amount.toString()
        val bundle =Bundle()
        bundle.putLong(PRODUCTID, args.id)
        bundle.putString(PRODUCTNAME, "Product example")
        bundle.putString(PRODUCTID, "Product example")
        bundle.putString(CATEGORY,"Dummy List")
        checkoutActivity.huaweiAnalyticsInstance.onEvent(VIEWPRODUCT,bundle)
        buyButton.setOnClickListener {
            val bundle =Bundle()
            bundle.putLong(PRODUCTID, args.id)
            bundle.putString(PRODUCTNAME, "Product example")
            bundle.putLong(QUANTITY, 1)
            val currentTime: Date = Calendar.getInstance().time
            bundle.putString(OCCURREDTIME, currentTime.toString())
            checkoutActivity.huaweiAnalyticsInstance.onEvent(COMPLETEPURCHASE,bundle)
            checkoutActivity.finish()
        }

    }
}