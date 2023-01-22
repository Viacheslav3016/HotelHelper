package com.example.hotelhelper.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hotelhelper.R
import com.example.hotelhelper.domain.ShopItem
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlin.concurrent.fixedRateTimer

class FragmentOrderItem() : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parseParams()
        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        InitView(view)

        nameTextChangeListener()

        sizeTextChangedListener()

        countTextChangeListener()

        launchRightMode()

        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_count)
            } else {
                null
            }
            til_Count.error = message
        }

        viewModel.errotInputname.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_name)
            } else {
                null
            }
            til_name.error = message
        }

        viewModel.errorInputsize.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.error_input_size)
            } else {
                null
            }
            til_size.error = message
        }
        viewModel.closeScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

    private fun launchRightMode() {
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
        viewModel.shopItem.observe(viewLifecycleOwner) {
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

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(EXTRA_SCREEN_MOOD)) {
            throw RuntimeException("param screen mode is absent")
        }
        val mood = args.getString(EXTRA_SCREEN_MOOD)
        if (mood != EDIT_MOOD && mood != ADD_MOOD) {
            throw RuntimeException("uknown mood $mood")
        }
        screenMode = mood
        if (screenMode == EDIT_MOOD) {
            if (!args.containsKey(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("mood id is not absent")
            }
            shopItemId = args.getInt(EXTRA_SHOP_ITEM_ID, ShopItem.UNDEFINED_ID)
        }
    }


    private fun InitView(view: View) {
        til_name = view.findViewById(R.id.t_name)
        ed_name = view.findViewById(R.id.ed_name)
        til_size = view.findViewById(R.id.t_size)
        ed_size = view.findViewById(R.id.ed_size)
        til_Count = view.findViewById(R.id.t_Count)
        ed_count = view.findViewById(R.id.ed_count)
        button_save = view.findViewById(R.id.button_save)
    }



    companion object {
        val EXTRA_SCREEN_MOOD = "extra_mood"
        val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        val EDIT_MOOD = "mood_edit"
        val ADD_MOOD = "mood_add"
        val MODE_UNKNOWN = ""

        fun newInstanceAdd():FragmentOrderItem{
            val args = Bundle().apply {
                putString(EXTRA_SCREEN_MOOD, ADD_MOOD)
            }
            val fragment = FragmentOrderItem().apply {
                arguments = args
            }
            return fragment
        }
        fun newInstanceEdit(shopItemId: Int):FragmentOrderItem{
            val args = Bundle().apply {
                putString(EXTRA_SCREEN_MOOD, EDIT_MOOD)
                putInt(EXTRA_SHOP_ITEM_ID, shopItemId)
            }
            val fragment = FragmentOrderItem().apply {
                arguments = args
            }
            return fragment
        }
    }
}