package com.example.hotelhelper.presentation

import android.content.Context
import android.content.Intent
import android.content.Intent.parseIntent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.hotelhelper.R
import com.example.hotelhelper.domain.ShopItem
import com.example.hotelhelper.presentation.FragmentOrderItem.Companion.ADD_MOOD
import com.example.hotelhelper.presentation.FragmentOrderItem.Companion.EDIT_MOOD
import com.example.hotelhelper.presentation.FragmentOrderItem.Companion.EXTRA_SCREEN_MOOD
import com.example.hotelhelper.presentation.FragmentOrderItem.Companion.EXTRA_SHOP_ITEM_ID
import com.example.hotelhelper.presentation.FragmentOrderItem.Companion.MODE_UNKNOWN
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class OrderItemActivity : AppCompatActivity() {

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_item)
        parseIntent()

        lainchRightMode()
    }

    private fun lainchRightMode() {
        val fragment = when (screenMode) {
            EDIT_MOOD -> FragmentOrderItem.newInstanceEdit(shopItemId)
            ADD_MOOD -> FragmentOrderItem.newInstanceAdd()
            else -> throw RuntimeException("uknown mood $screenMode")
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.order_item_container, fragment)
            .commit()
    }

    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MOOD)) {
            throw RuntimeException("param screen mode is absent")
        }
        val mood = intent.getStringExtra(EXTRA_SCREEN_MOOD)
        if (mood != EDIT_MOOD && mood != ADD_MOOD) {
            throw RuntimeException("uknown mood $mood")
        }
        screenMode = mood
        if (screenMode == EDIT_MOOD) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("mood id is not absent")
            }
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }


    companion object {
        val EXTRA_SCREEN_MOOD = "extra_mood"
        val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        val EDIT_MOOD = "mood_edit"
        val ADD_MOOD = "mood_add"
        val MODE_UNKNOWN = ""

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, OrderItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MOOD, ADD_MOOD)
            return intent
        }

        fun newIntentEdit(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, OrderItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MOOD, EDIT_MOOD)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }
    }
}