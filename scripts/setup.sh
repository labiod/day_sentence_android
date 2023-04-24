#! /usr/bin/env bash

echo "Setup android"

wget https://dl.google.com/android/repository/commandlinetools-mac-9123335_latest.zip

unzip commandlinetools-mac-9123335_latest.zip

mkdir $ANDROID_SDK_ROOT

mkdir $ANDROID_SDK_ROOT/cmdline-tools

CMD_TOOLS=$ANDROID_SDK_ROOT/cmdline-tools/latest/bin

mv cmdline-tools $ANDROID_SDK_ROOT/cmdline-tools/latest

yes | $CMD_TOOLS/sdkmanager --licenses

$CMD_TOOLS/sdkmanager --install 'build-tools;33.0.0' platform-tools 'platforms;android-30' > /dev/null

$CMD_TOOLS/sdkmanager --install emulator --channel=0 > /dev/null

$CMD_TOOLS/sdkmanager --install 'system-images;android-29;default;x86' --channel=0 > /dev/null

echo no | $CMD_TOOLS/avdmanager create avd --force -n testAVD --abi 'default/x86' --package 'system-images;android-29;default;x86'