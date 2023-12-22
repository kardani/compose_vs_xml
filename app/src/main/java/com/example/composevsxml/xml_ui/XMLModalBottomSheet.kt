package com.example.composevsxml.xml_ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.example.composevsxml.DataModel
import com.example.composevsxml.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso


class XMLModalBottomSheet : BottomSheetDialogFragment() {

    private val data by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getSerializable("data", DataModel::class.java)
        } else {
            arguments?.getSerializable("data") as DataModel
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return when (data) {
            is DataModel.Large -> inflater.inflate(R.layout.list_item_large, container, false)
            is DataModel.Small -> inflater.inflate(R.layout.list_item_small, container, false)
            else -> null
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data?.let {item ->
            view.findViewById<ImageView>(R.id.userImage)?.let {
                Log.d("ListItem", "onBindViewHolder: ${item.userImageUrl}")
                Picasso.get()
                    .load(item.userImageUrl)
                    .into(it)
            }

            view.findViewById<TextView>(R.id.userName).text = "${item.id}_${item.userFullName}"
            view.findViewById<TextView>(R.id.challengesCompleted).text = "Completed ${item.completedChallenges} of ${item.totalChallenges} challenges"

            view.findViewById<RatingBar>(R.id.userRating).rating = item.rate

            view.setOnClickListener {

            }

            view.findViewById<ImageView>(R.id.deleteButton)?.setOnClickListener {

            }
        }

    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }
}

//class MainActivity : AppCompatActivity() {
//    ...
//    val modalBottomSheet = ModalBottomSheet()
//    modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
//    ...
//}