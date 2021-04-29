package com.szareckii.searchinthebasefssp.view.main

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.szareckii.searchinthebasefssp.R
import com.szareckii.searchinthebasefssp.utils.regionMap
import kotlinx.android.synthetic.main.search_dialog_fragment.*
import java.util.*

class SearchDialogFragment : BottomSheetDialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var lastnameEditText: TextInputEditText
    private lateinit var firstnameEditText: TextInputEditText
    private lateinit var secondnameEditText: TextInputEditText
    private lateinit var birthdateEditText: TextInputEditText

    private var regionEditText: String = "10 Республика Карелия"

    private lateinit var searchButton: TextView

    private var onSearchClickListener: OnSearchClickListener? = null

    var day = 0
    var month = 0
    var year = 0

    var savedDay = ""
    var savedMonth = ""
    var savedYear = ""

    private val textWatcher = object : TextWatcher {

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            searchButton.isEnabled = lastnameEditText.text != null && !lastnameEditText.text.toString().isEmpty() &&
                    firstnameEditText.text != null && !firstnameEditText.text.toString().isEmpty()
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun afterTextChanged(s: Editable) {}
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(
             regionMap[regionEditText] ?: "10",
                    lastnameEditText.text.toString(),
                    firstnameEditText.text.toString(),
                    secondnameEditText.text.toString(),
                    birthdateEditText.text.toString(),
            )
            dismiss()
        }

    internal fun setOnSearchClickListener(listener: OnSearchClickListener) {
        onSearchClickListener = listener
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_dialog_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListOfRegions()
        setEditTextOfPhusical()

        region_input_layout.setOnClickListener {
            regionEditText = region_edit_text.text.toString()
        }

        pickDate()
        lastnameEditText.addTextChangedListener(textWatcher)
        firstnameEditText.addTextChangedListener(textWatcher)
        searchButton.setOnClickListener(onSearchButtonClickListener)
    }

    private fun setEditTextOfPhusical() {
        searchButton = search_button_textview
        searchButton.isEnabled = false
        lastnameEditText = lastname_edit_text
        firstnameEditText = firstname_edit_text
        secondnameEditText = secondname_edit_text
        birthdateEditText = birthdate_edit_text
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
    }

    private fun pickDate() {
        birthdate_edit_text.setOnClickListener {
            getDateCalendar()

            context?.let { it1 -> DatePickerDialog(it1, this, year, month, day).show() }
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth.toString()
        savedMonth = setMonth(month)
        savedYear = year.toString()
        val dateBirthday = "$savedDay.$savedMonth.$savedYear"
        birthdate_input_layout.editText?.setText(dateBirthday)
    }

    private fun setMonth(month: Int) =
        if (month.toString().length == 1) {
            "0$month"
        } else {
            month.toString()
        }

    private fun setListOfRegions() {
        val regions = resources.getStringArray(R.array.regions)
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, regions)
        (region_input_layout.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    override fun onDestroyView() {
        onSearchClickListener = null
        super.onDestroyView()
    }

    interface OnSearchClickListener {
        fun onClick(
                region: String,
                lastname: String,
                firstname: String,
                secondname: String,
                birthdate: String,
        )
    }

    companion object {
        fun newInstance(): SearchDialogFragment {
            return SearchDialogFragment()
        }
    }
}
