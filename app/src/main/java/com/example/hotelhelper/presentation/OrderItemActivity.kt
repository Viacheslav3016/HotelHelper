package com.example.hotelhelper.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.hotelhelper.R
import com.example.hotelhelper.domain.ShopItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class OrderItemActivity : AppCompatActivity() {

    private lateinit var viewModel: ShopItemViewModel

    private lateinit var til_name: TextInputLayout
    private lateinit var ed_name: TextInputEditText
    private lateinit var til_size: TextInputLayout
    private lateinit var ed_size: TextInputEditText
    private lateinit var til_Count: TextInputLayout
    private lateinit var ed_count: TextInputEditText
    private lateinit var button_save: Button
    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_item)
        parseIntent()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        InitView()

        nameTextChangeListener()

        sizeTextChangedListener()

        countTextChangeListener()

        lainchRightMode()

        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.errorInputCount.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            til_Count.error = message
        }

        viewModel.errotInputname.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            til_name.error = message
        }

        viewModel.errorInputsize.observe(this) {
            val message = if (it) {
                getString(R.string.error_input_size)
            } else {
                null
            }
            til_size.error = message
        }
        viewModel.closeScreen.observe(this) {
            finish()
        }
    }

    private fun lainchRightMode() {
        when (screenMode) {
            EDIT_MOOD -> launchEditMode()
            ADD_MOOD -> launchAddMode()
        }
    }

    private fun countTextChangeListener() {
        ed_count.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorCount()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun sizeTextChangedListener() {
        ed_size.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorSize()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun nameTextChangeListener() {
        ed_name.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.resetErrorName()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }


    private fun launchAddMode() {
        button_save.setOnClickListener {
            viewModel.addOrder(
                ed_name.text?.toString(),
                ed_size.text?.toString(),
                ed_count.text?.toString()
            )
        }
    }

    private fun launchEditMode() {
        viewModel.getShopOrder(shopItemId)
        viewModel.shopItem.observe(this) {
            ed_name.setText(it.name)
            ed_size.setText(it.size)
            ed_count.setText(it.count.toString())
        }
        button_save.setOnClickListener {
            viewModel.editShopOrder(
                ed_name.text?.toString(),
                ed_size.text?.toString(),
                ed_count.text?.toString()
            )
        }
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


    private fun InitView() {
        til_name = findViewById(R.id.t_name)
        ed_name = findViewById(R.id.ed_name)
        til_size = findViewById(R.id.t_size)
        ed_size = findViewById(R.id.ed_size)
        til_Count = findViewById(R.id.t_Count)
        ed_count = findViewById(R.id.ed_count)
        button_save = findViewById(R.id.button_save)
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