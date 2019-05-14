package hu.aut.android.sensordemo

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.hardware.SensorManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        btnList.setOnClickListener{
            listSensors()
        }

        btnStart.setOnClickListener{
            startSensor()
        }
    }

    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this);
    }

    private fun listSensors() {
        sensorManager.getSensorList(Sensor.TYPE_ALL).forEach {
            tvStatus.append("${it.name}\n")
        }
    }

    private fun startSensor() {
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this, sensor,
                SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        val accX = event.values[0].toDouble()
        val accY = event.values[1].toDouble()
        val accZ = event.values[2].toDouble()



        var origin = Math.sqrt(
                Math.pow(accX, 2.0) +
                Math.pow(accY, 2.0) +
                Math.pow(accZ, 2.0)
        )
        origin = Math.abs(origin - SensorManager.STANDARD_GRAVITY)

        Log.d("ACCELEROMETER", "Accelerometer value: $origin")

        tvStatus.text =
                "X: $accX\nY: $accY\nZ: $accZ"
    }
}
