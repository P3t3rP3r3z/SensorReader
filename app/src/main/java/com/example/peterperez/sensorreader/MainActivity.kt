package com.example.peterperez.sensorreader

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensormanager : SensorManager
    var my_layout : ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sensormanager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensormanager.registerListener(this, sensormanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }


    override fun onSensorChanged(event: SensorEvent?) {
        x_val.text = "${event!!.values[0]}"
        y_val.text = "${event!!.values[1]}"
        z_val.text = "${event!!.values[2]}"

        val xhigh = "${event!!.values[0]}+2"
        val xlow = "${event!!.values[0]}-2"
        val yhigh = "${event!!.values[0]}+2"
        val ylow = "${event!!.values[0]}-2"
        val zhigh = "${event!!.values[0]}+2"
        val zlow = "${event!!.values[0]}-2"

        if(xlow < "${event!!.values[1]}" && xhigh > "${event!!.values[1]}"){
            surfaceView.setBackgroundColor(Color.parseColor("#ff38c82f"))
        }

    }

    override fun onResume() {
        super.onResume()

    }
    override fun onPause() {
        super.onPause()
        sensormanager!!.unregisterListener(this)
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // MotionEvent object holds X-Y values
        if (event.action == MotionEvent.ACTION_DOWN) {
            val text = "You touched at x = " + event.x + " and y = " + event.y
            Toast.makeText(this, text, Toast.LENGTH_LONG).show()
        }
        return super.onTouchEvent(event)
    }

}
