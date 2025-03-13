package com.misw.alarmify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCreateAlarm = view.findViewById<Button>(R.id.create_alarm_button)

        buttonCreateAlarm.setOnClickListener {
            (activity as? AppCompatActivity)?.supportActionBar?.title = "Crear Alarma"
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CreateAlarmFragment())
                .addToBackStack(null)  // permite volver al fragment anterior
                .commit()
            (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
}