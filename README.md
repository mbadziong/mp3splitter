# mp3splitter
Splits mp3 file into n chunks.
I hate my old iPod rewind function, especially while listening to long DnB set. When I missclick on the "next track" button and I would go back to the previous track, to the specific minute, it requires to keep pushing that f*ng rewind button forever, because it moves me only a few seconds ahead. That's the reason for this script, because I don't want to manually split audio files using some Audacity or any other tool.

## Installation
Download `kotlinc` for your OS and clone this repo for the script

## Usage
first arg - file
second arg - how many chunks do you want
```kotlinc -script mp3split.kts C:\Users\mbadziong\Downloads\1.mp3 5```