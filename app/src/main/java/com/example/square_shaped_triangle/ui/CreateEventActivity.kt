package com.example.square_shaped_triangle.ui

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.square_shaped_triangle.R
import com.example.square_shaped_triangle.database.Event
import com.example.square_shaped_triangle.viewmodels.AppViewModel
import kotlinx.android.synthetic.main.activity_create_event.*
import java.text.SimpleDateFormat
import java.util.*

class CreateEventActivity : AppCompatActivity() {
    private lateinit var viewModel: AppViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)

        viewModel = ViewModelProviders.of(this).get(AppViewModel::class.java)

/*        profile_floating_action_button.setOnClickListener{
            openListGame()
        }*/
        activityEventInfo_button_delete.setOnClickListener{
            createvent()
        }

        create_event_text_view_time.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                create_event_text_view_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        val c   = Calendar.getInstance()
        val year= c.get(Calendar.YEAR)
        val month =c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        create_event_text_view_date.setOnClickListener {
            val calendar = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ view, mYear, mMonth, mDay ->
                create_event_text_view_date.text = ""+mDay+"/"+mMonth+"/"+mDay
            },year,month,day)
            calendar.show()
        }
        create_event_text_view_Games.setOnClickListener {
            startActivityForResult(Intent(this, GamePickerActivity::class.java), REQUEST_CODE_GAME)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_GAME) {
            if (resultCode == Activity.RESULT_OK) {
                create_event_text_view_Games.text = data?.getStringExtra(GamePickerActivity.KEY_PICKED_GAME_NAME)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun createvent(){
        viewModel.addEvent(event =
        Event(
            id = UUID.randomUUID().toString(),
            name = create_event_edit_text_name_event.text.toString(),
            address = create_event_edit_text_location.text.toString(),
            minPlayers = create_event_text_view_people_min.text.toString().toInt(),
            maxPlayers = create_event_text_view_people_max.text.toString().toInt(),
            date = create_event_text_view_date.text.toString(),
            creatorId = "autor",
            state = "dasd",
            game = create_event_text_view_Games.text.toString()) // TODO: update game text
        )
        {
            Toast.makeText(this,"Event created",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    companion object {
        private const val REQUEST_CODE_GAME = 2303
    }
}
