# Time Lapse Viewer

TLV serves two main purposes...

1. It is a viewer that shows orthorectified imagery from the O2 database.
2. It is able to show several images in a flip-book fashion, allowing users to quickly look through stacks of imagery.

**<span class="glyphicon glyphicon-step-backward"></span>&nbsp;:**
The rewind button will cycle back to the previous image in the stack.

**<span class="glyphicon glyphicon-play"></span>&nbsp;:**
This play button cycles through all images at 1 second intervals. We're still working on an '80s jam to go along in the background.

**<span class="glyphicon glyphicon-step-forward"></span>&nbsp;:**
The fast forward button will advance to the next image in the stack.

_Note: Use the left and right arrow keys on the keyboard to cycle through the image stack and avoid carpal tunnel._

**<span class="glyphicon glyphicon-list-alt"></span>&nbsp;:**
The summary button will give a list of all images in the stack along with an abbreviated composition of metadata. It's better than cliff notes!

## Annotations
* **Circle:**
Click to begin a circle center and then click again to end it.
* **Line:**
Click to begin a line and click again each time you want a new vertex. Double-click to end the line.
* **Point:**
Just click anywhere.
* **Polygon:**
Click to begin a polygon and click again each time you want a new vertex. Double-click to close the polygon.
* **Rectangle:**
Click to start a rectangle and then click again to close it.
* **Square:**
Click to start a square center and then click again to close it.
* **Modify:**
Click on any annotation to modify its style.

## Export
* **Metadata:**
This will take all the metdata from all the layers, convert it to a CSV file and allow you to download it. TLV harvests as much metadata as it can from its libraries and does not make any attempt to change or normalize the data. What TLV sees is what you get.
* **Screenshot:**
Think of it like the map taking a selfie. #nofilter

## Image Properties
See [image properties](image-properties.md) for details.

## Layers
* **Base Layer:**
Select from an assortment of base layers to bring context to the imagery.
* **Corss-Hair:**
As the name implies, this places a corss-hair in the center of the map. Whether you are looking to zero-in on the bad guy or just want to center the map on a particular target, this will get the job done.
* **Search Origin:**
This will place a marker on the map that indicates the exact location used for the center point search. All the zooming and panning can sometimes disorient you worse than someone waking you up from deep REM sleep. So, in case you forget the point around which you actually were searching, we’ve got you covered.

## Map
* **Mouse Coordinates:**
The coordinate of the mouse is displayed in the lower-left corner of the map. Click the box to cycle through DD, DMS and MGRS formats. Right-click anywhere on the map to get a dialog box of that coordinate in all three formats.

* **Rotation:**
In 2D mode you can rotate the map by holding Shift + Alt while clicking and dragging. In 3D mode, you can hold Alt while clicking and dragging to pitch the globe.

## Search
* **Bookmark It!:**
Get a URL that captures ALL of the current search parameters. You can use that URL and keep certain parameters fixed and bookmark it so that those parameters are automatically set when you bring up a new TLV session.
* **End Date:**
The end date of the search. The default is now.
* **Library:**
You can select one or more libraries from which to search for imagery. Each selected library will be searched one at a time and the results will be aggregated and sorted before they are returned to the browser.
* **Location:**
This will be the center point around which the search is conducted. TLV will bring its A game and attempt to automatically detect which coordinate format you enter. _Note: You can add TLV's custom search toolbar plugin to your browser and use it to search for imagery just like you would when searching for anyting else._
* **Max. Cloud Cover (%):**
The maximum amount of cloud cover you want in any of the images that are returned. The default is 100.
* **Max. Results:**
The total amount of images you are willing to look through. The default is 10. The ability to examine large amounts of imagery relies heavily on available browser/computer resources.
* **Min. Niirs:**
The minimum NIIRS (National Imagery Interpretability Rating Scale) in which you are interested. The default is 0.
* **Sensor:**
Discriminate results to specific sensor types.
* **Start Date:**
The start date of the search. The default is 30 days prior to today.

## Time Lapse
* **Delete Frame:**
Kick that image to the curb and remove it from the stack. Get rid of things even faster by using the delete button on the keyboard.
* **Geo-Jump:**
Go to any place on the map that your heart desires.
* **Orientation:**
* * **Manual:**
You are responsible for your own rotation (and pitch, tilt, etc. if you’re in 3D).
* * **Auto:**
TLV will attempt to use your device’s internal sensors to orient the imagery in real-time to align itself with your device’s "view".
* **Reverse Order:**
The default order of the image stack is chronological. But, if you prefer the other way, here’s where to do it.

## View
* **Dimensions:**
If your browser supports it, TLV will give you a 3D view of your images!
* **Swipe:**
Swipe an image over another. This is TLV's version of "wax on, wax off"... only instead of wax, it's imagery.
