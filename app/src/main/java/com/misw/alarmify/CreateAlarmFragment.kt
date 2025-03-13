package com.misw.alarmify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout

class CreateAlarmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_alarm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = listOf("Repetir", "Tipo de alarma", "Acción Forzada")
        val customAdapter = CustomAdapter(requireContext(), data)
        val listView = view.findViewById<ListView>(R.id.list)
        listView.adapter = customAdapter

        val textInputLayout = view.findViewById<TextInputLayout>(R.id.textField1)
        val openDialogButton = view.findViewById<Button>(R.id.save_alarm_button)

        openDialogButton.setOnClickListener {
            val alarmName = textInputLayout.editText?.text.toString().trim()

            if (alarmName.isEmpty()) {
                textInputLayout.error = "Por favor, ingresa un nombre"
                return@setOnClickListener
            } else {
                textInputLayout.error = null
            }

            MaterialAlertDialogBuilder(requireContext(), R.style.MyMaterialAlertDialog)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_supporting_text))
                .setNegativeButton(getString(R.string.dialog_decline)) { dialog, _ ->
                    Toast.makeText(context, "Cancelado", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .setPositiveButton(getString(R.string.dialog_accept)) { dialog, _ ->
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, AlarmsFragment())
                        .addToBackStack(null)
                        .commit()

                    (activity as? AppCompatActivity)?.supportActionBar?.apply {
                        title = "Alarmas"
                        setDisplayHomeAsUpEnabled(false)
                    }

                    activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
                        ?.menu?.findItem(R.id.alarms_nav_button)?.isChecked = true

                    dialog.dismiss()
                }
                .show()
        }
    }
}
