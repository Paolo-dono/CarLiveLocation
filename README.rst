===============
CarLiveLocation
===============

This app receives MQTT messages that contain decimal coordinates. These are used to update the location of a marker on a map to show the live location of a GPS device.

* GitHub repo: https://github.com/Paolo-dono/CarLiveLocation
* Free Software: MIT License

Requirements for phone:

* Your phone must be an Android device that runs Android 5.0 or later and have at least 1GB RAM

Steps to install the app:

* Download the latest Java JDK
* Get Android Studio
* Open Android Studio and go to File -> New -> Project from Version Control...
* Paste the following URL: https://github.com/Paolo-dono/CarLiveLocation
* Click on 'Clone'
* You will now be able to run the project
* If you want to install the app on your phone, do the following:

  * Put your phone into developer mode:
  
    * Go to Settings -> About phone
    * Then tap 'Build Number' 7 times
  
  * Connect your phone to your PC
  * Then on your phone, go to Settings -> Developer options -> Enable USB debugging -> Click 'OK' on the following two popup menus
  * If your phone appears in the dropdown menu right next to the green play button in Android Studio or shows up as one of the devices when you click in that dropdown menu, you can skip the following step
  * Go to File Explorer -> Right click on 'This PC' -> Select 'Manage' from the right click menu -> Device Manager -> Right click on your phone or expand 'Android Phone' and right click -> Update Driver -> Search automatically for updated driver software -> Wait for it to finish loading then click on 'Close'
  * Then the final step is to run the program by hitting the green play button in Android Studio and then the app will be installed on your phone and automatically open
  
If you would like to use the Seeed Studio Grove GPS as the GPS device, you can do the following:

* Install the groveGPS API. Go to the following link learn how to do this: https://github.com/Paolo-dono/Grove_GPS/blob/master/README.rst
* Install the the mosquitto broker for MQTT communication with the command: sudo apt-get install mosquitto
* Run the following python file on your Raspberry Pi: https://github.com/Paolo-dono/Grove_GPS/blob/master/docs/stream_coordinates.py

Acknowledgements:

* MQTT communication code adapted from: https://github.com/eclipse/paho.mqtt.android/blob/master/paho.mqtt.android.example/src/main/java/paho/mqtt/java/example/PahoExampleActivity.java
* Google Maps setup (YouTube): Android Coding - How to Implement Google Map in Android Studio
  
  * Link: https://www.youtube.com/watch?v=eiexkzCI8m8&t=213s
