package com.example.aop_part3_chapter03

import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //todo setp0 뷰를 초기화 해주기
        initOnOffButton()
        initChangeAlarmTimeButton()

        //todo setp1 데이터 가져오기

        //todo setp2 뷰에 데이터 그려주기

    }

    private fun initOnOffButton() {
        val onOffButton = findViewById<Button>(R.id.onOffButton)
        onOffButton.setOnClickListener {
            //데이터 확인

            //온오프에 따라 작업 처리

            //오프 -> 알람 제거
            //온 -> 알람 등록

            //데이터 저장
        }
    }

    private fun initChangeAlarmTimeButton() {
        val changeAlarmTimeButton = findViewById<Button>(R.id.changeAlarmTimeButton)
        changeAlarmTimeButton.setOnClickListener {
            //현재시간 가져오기
            val calender = Calendar.getInstance()

            //TImePickerDialog 사용하여 시간 설정
            TimePickerDialog(this, { picker, hour, minute ->

                val model = saveAlarmModel(hour, minute, false)

                //데이터 저장

                //뷰 업데이트

                //기존 알람 삭제

            }, calender.get(Calendar.HOUR_OF_DAY), calender.get(Calendar.MINUTE), false)
                .show()

        }
    }

    private fun saveAlarmModel(
        hour: Int,
        minute: Int,
        onOff: Boolean
    ): AlarmDisplayModel {
        val model = AlarmDisplayModel(
            hour = hour,
            minute = minute,
            onOff = onOff
        )

        val sharedPreferences = getSharedPreferences("time", Context.MODE_PRIVATE)

        with(sharedPreferences.edit()) {
            putString("alarm", model.makeDataForDB())
            putBoolean("onOff", model.onOff)
            commit()
        }



        return model
    }
}