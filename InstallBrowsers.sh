#!/bin/bash
set -ex
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo apt install ./google-chrome-stable_current_amd64.deb


sudo apt-get update
sudo apt install firefox

# wget -O ~/FirefoxSetup.tar.bz2 "https://download.mozilla.org/?product=firefox-latest&os=linux64"
# sudo tar xjf ~/FirefoxSetup.tar.bz2 -C /opt/
# sudo ln -s /opt/firefox/firefox /usr/lib/firefox/firefox
