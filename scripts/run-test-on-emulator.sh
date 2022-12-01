#! /usr/bin/env bash

CMD_TOOLS=$ANDROID_SDK_ROOT/cmdline-tools/latest/bin

test_device="testAVD"

$ANDROID_SDK_ROOT/emulator/emulator -avd $test_device -no-window -gpu swiftshader_indirect -no-snapshot -noaudio -no-boot-anim &

i=0
while [[ -z $($ANDROID_SDK_ROOT/platform-tools/adb shell getprop sys.boot_completed) ]]; do
  sleep 2;
  i=$((i+1))
  if [[ "$i" -gt 100 ]]; then
    echo "Waiting for device -timeout"
    exit 1
  fi
done

$ANDROID_SDK_ROOT/platform-tools/adb devices

#run test here
$result = 0

$ANDROID_SDK_ROOT/platform-tools/adb shell reboot -p

$CMD_TOOLS/avdmanager delete $test_device

echo "Finish test with result: $resut"

exit $result

