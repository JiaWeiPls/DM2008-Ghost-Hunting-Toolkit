# DM2008 Final Project Android Application Find Me

This is a Ghost-hunting Toolkit as an Android Application called Find Me.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:JiaWeiPls/DM2008-Ghost-Hunting-Toolkit.git
```

## Configuration
### Keystores:
Create `app/keystore.gradle` with the following info:
```gradle
ext.key_alias='...'
ext.key_password='...'
ext.store_password='...'
```
And place both keystores under `app/keystores/` directory:
- `playstore.keystore`
- `stage.keystore`


## Build variants
Use the Android Studio *Build Variants* button to choose between **production** and **staging** flavors combined with debug and release build types


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Maintainers
This project is mantained by:
* [Jia Wei](http://github.com/JiaWeiPls)
* [Theodore](http://github.com/theOrderOfLoki)

## Usage
Spirit Box/FM Radio:
Note that the spirit box has 2 buttons, skinned as knobs at the bottom left of the screen.
The lower button is to start/stop recording, & the upper button to send message.
1. Tap the lower button to start recording & speak into the phone's speaker (Proper enunciation helps improve the accuracy of STT)
2. Tap the lower button again to stop recording.
3. Tap the upper button to send the message.
Thermometer:
1. The lower the temperature value indicated on the screen, the nearer the ghost (No actions requried on the players end)
EMF Reader:
1. The higher the EMF value indicated on the screen, the nearer the ghost (No actions requried on the players end)

## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request
