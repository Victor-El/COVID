package me.codeenzyme.covid

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.NumberFormat
import javax.inject.Inject

class Util @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun formatNumber(num: Number?): String {
        return num?.let {
            NumberFormat.getIntegerInstance().format(it)
        } ?: context.getString(R.string.no_data_available)
    }
}