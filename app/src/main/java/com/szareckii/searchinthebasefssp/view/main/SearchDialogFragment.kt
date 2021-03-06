package com.szareckii.searchinthebasefssp.view.main

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException


class SearchDialogFragment : BottomSheetDialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var lastnameEditText: TextInputEditText
    private lateinit var firstnameEditText: TextInputEditText
    private lateinit var secondnameEditText: TextInputEditText
    private lateinit var birthdateEditText: TextInputEditText

    private var regionEditText: String = "10 Республика Карелия"

    private lateinit var searchButton: TextView

    private var onSearchClickListener: OnSearchClickListener? = null

    private lateinit var adapter : ArrayAdapter<String>

    var day = 0
    var month = 0
    var year = 0

    var savedDay = ""
    var savedMonth = ""
    var savedYear = ""

    private val cyrillicRegex = "[А-яЁё]+"

    private val textWatcherLastname = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if(lastnameEditText.text != null || !lastnameEditText.text.toString().isEmpty()) {
                lastname_input_layout.error = null
            }
        }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    private val textWatcherFirsrname = object : TextWatcher {
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if(firstnameEditText.text != null || !firstnameEditText.text.toString().isEmpty()) {
                firstname_input_layout.error = null
            }
        }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable) {}
    }

    fun isMatchingRegexp(text: String?, cyrillicRegex: String?): Boolean {
        var pattern: Pattern? = null
        try {
            pattern = Pattern.compile(cyrillicRegex)
        } catch (e: PatternSyntaxException) {
            e.printStackTrace()
        }
        if (pattern == null) {
            return false
        }
        val regexp: Matcher = pattern.matcher(text)
        return regexp.matches()
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            regionEditText = region_edit_text.text.toString()
            var verificator = 0
            //Если обязательные поля формы пустые, то предупреждение

            if(!isMatchingRegexp(lastnameEditText.text.toString(), cyrillicRegex)) {
                lastname_input_layout.error = getString(R.string.error_cyrillic)
                verificator = 1
            }

            if((lastnameEditText.text == null || lastnameEditText.text.toString().isEmpty())) {
                lastname_input_layout.error = getString(R.string.error_lastname)
                verificator = 1
            }

            if(!isMatchingRegexp(firstnameEditText.text.toString(), cyrillicRegex)) {
                firstname_input_layout.error = getString(R.string.error_cyrillic)
                verificator = 1
            }

            if(firstnameEditText.text == null || firstnameEditText.text.toString().isEmpty()) {
                firstname_input_layout.error = getString(R.string.error_firstname)
                verificator = 1
            }

            if(regionEditText == "") {
                region_input_layout.error = getString(R.string.error_region)
                verificator = 1
            }

            if(verificator == 1) {
                return@OnClickListener
            }

            onSearchClickListener?.onClick(
                regionEditText,
                lastnameEditText.text.toString().capitalize(Locale.ROOT),
                firstnameEditText.text.toString().capitalize(Locale.ROOT),
                secondnameEditText.text.toString().capitalize(Locale.ROOT),
                birthdateEditText.text.toString(),
            )
            dismiss()
            saveToSharePreference()
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
        pickDate()
        lastnameEditText.addTextChangedListener(textWatcherLastname)
        firstnameEditText.addTextChangedListener(textWatcherFirsrname)
        searchButton.setOnClickListener(onSearchButtonClickListener)
        readSharePreference()
    }

    private fun readSharePreference() {
        val sharedPref = activity?.getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE) ?: return
        val defaultValue = resources.getString(R.string.saved_default_key)
        lastname_edit_text.setText(sharedPref.getString("lastname", defaultValue))
        firstname_edit_text.setText(sharedPref.getString("firstname", defaultValue))
        secondname_edit_text.setText(sharedPref.getString("secondname", defaultValue))
        birthdate_edit_text.setText(sharedPref.getString("birthdate", defaultValue))
        region_edit_text.setText(regionMap[sharedPref.getString("region", defaultValue)]?.let { adapter.getItem(it.toInt() - 1).toString() }, false)
    }

    private fun setEditTextOfPhusical() {
        searchButton = search_button_textview
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
        savedDay = setDayOrMonth(dayOfMonth)
        savedMonth = setDayOrMonth(month + 1)
        savedYear = year.toString()
        val dateBirthday = "$savedDay.$savedMonth.$savedYear"
        birthdate_input_layout.editText?.setText(dateBirthday)
    }

    private fun setDayOrMonth(number: Int) =
        if (number.toString().length == 1) {
            "0$number"
        } else {
            number.toString()
        }

    private fun setListOfRegions() {
        val regions = resources.getStringArray(R.array.regions)
        adapter = ArrayAdapter(requireContext(), R.layout.list_item_region, regions)
        (region_input_layout.editText as? AutoCompleteTextView)?.setAdapter(adapter)
    }

    private fun saveToSharePreference() {
        val sharedPref = activity?.getSharedPreferences(
                getString(R.string.preference_file_key),
                Context.MODE_PRIVATE) ?: return
        sharedPref.edit().apply {
            putString("lastname", lastnameEditText.text.toString().capitalize(Locale.ROOT))
            putString("firstname", firstnameEditText.text.toString().capitalize(Locale.ROOT))
            putString("secondname", secondnameEditText.text.toString().capitalize(Locale.ROOT))
            putString("birthdate", birthdateEditText.text.toString())
            putString("region", regionEditText)
            apply()
        }
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
