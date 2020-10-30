package com.example.carlivelocation3.manager

import android.content.Context
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*
import java.util.*

class MQTTmanager (context:Context, marker:Marker, map:GoogleMap, topic:String) {
    /* Creates an object to perform MQTT communication
     */
    var myMarker = marker
    var mMap = map
    var myTopic = topic

    var clientId = UUID.randomUUID().toString()
    var client = MqttAndroidClient(context, "tcp://test.mosquitto.org:1883", clientId)

    fun connect() {
        /* Connects to the appropriate topic to receive MQTT messages
         */
        client.setCallback(object : MqttCallbackExtended {
            override fun connectComplete(b: Boolean, s: String) {
                if (b) {
                    subToTopic()
                }
            }

            override fun connectionLost(throwable: Throwable) {
            }

            override fun messageArrived(topic: String, mqttMessage: MqttMessage) {
                /* If message arrived, extract coordinates and move marker and map camera
                 */
                var msgSpl = mqttMessage.toString().split(",")
                var coordinates = LatLng(msgSpl[0].toDouble(), msgSpl[1].toDouble())
                myMarker.position = coordinates
                mMap.animateCamera(CameraUpdateFactory.newLatLng(coordinates))
            }

            override fun deliveryComplete(iMqttDeliveryToken: IMqttDeliveryToken) {
            }
        })

        val options = MqttConnectOptions()
        options.isAutomaticReconnect = true
        options.isCleanSession = false

        try {
            client.connect(options, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    val disconnectedBufferOptions = DisconnectedBufferOptions()
                    disconnectedBufferOptions.isBufferEnabled = true
                    disconnectedBufferOptions.bufferSize = 100
                    disconnectedBufferOptions.isPersistBuffer = false
                    disconnectedBufferOptions.isDeleteOldestMessages = false
                    client.setBufferOpts(disconnectedBufferOptions)
                    subToTopic()
                }

                override fun onFailure(token: IMqttToken, ex: Throwable) {
                }
            })
        } catch (ex: MqttException) {
            ex.printStackTrace()
        }
    }

    fun subToTopic() {
        /* Subscribes MQTT client to topic
         */
        try {
            client.subscribe(myTopic, 0, null, object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                }

                override fun onFailure(token: IMqttToken, ex: Throwable) {
                }
            })
        } catch (ex: MqttException) {
            System.err.println("Exception whilst subscribing")
            ex.printStackTrace()
        }
    }
}