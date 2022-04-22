package com.endava.internship.mobile.weatherapp.com.endava.internship.mobile.weatherapp.utils

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.endava.internship.mobile.weatherapp.utils.weatherIDToResourceID

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:isVisible")
    fun isVisible(view: View, visible: Boolean) {
        view.visibility = if (visible) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("app:spanTimeText")
    fun spanTimeText(view: TextView, timeString: String) {
        val spannableTime = SpannableString(timeString)
        spannableTime.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        view.text = spannableTime.toString()
    }

    @JvmStatic
    @BindingAdapter("app:setIcon")
    fun setIcon(view: ImageView, id: Int) {
        view.setImageResource(weatherIDToResourceID(id))
    }
}