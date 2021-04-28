#!/bin/bash
sudo apt-get update
sudo apt install firefox
sudo apt-get install xvfb
sudo Xvfb &
export DISPLAY=localhost:0.0
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo apt install ./google-chrome-stable_current_amd64.deb