package com.example.analyticsjdgp.fragments

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.analyticsjdgp.CheckoutActivity
import com.example.analyticsjdgp.R
import com.example.analyticsjdgp.fragments.dummy.DummyContent
import com.huawei.hms.analytics.type.HAEventType.VIEWPRODUCTLIST
import com.huawei.hms.analytics.type.HAParamType.CATEGORY

/**
 * A fragment representing a list of Items.
 */
@Suppress("DEPRECATION")
class ItemFragment : Fragment() {

    private var columnCount = 2
    private lateinit var checkoutActivity:CheckoutActivity


    override fun onAttach(activity: Activity) {
        super.onAttach(activity)

        checkoutActivity=activity as CheckoutActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyItemRecyclerViewAdapter(OnClickHandler {id->
                    val action=ItemFragmentDirections.actionItemFragmentToDetailFragment2(id)
                    checkoutActivity.findNavController(R.id.nav_host_fragment).navigate(action)
                },DummyContent.ITEMS)
            }
        }


        val bundle = Bundle()
        bundle.putString(CATEGORY, "Dummy List")
        checkoutActivity.huaweiAnalyticsInstance.onEvent(VIEWPRODUCTLIST, bundle)
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}

