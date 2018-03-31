# Chicago-Landmarks
Two Android apps that communicate through broadcast receivers and permissions. City attractions and restaurants are displayed using fragments. Detailed description below:

CS478: Software Development for Mobile Platforms

In this project I have designed and coded two simple Android apps that to work together on a device running version
7.1, Nougat. The first app helps visitors in Chicago decide on points of interest in the city. The second app
has specific information about the points of interest.
* Application A1 - defines a dangerous level permission and an activity containing two buttons. The buttons broadcast two different intents (attractions or restaurants) depending on the button pressed.
* Application A2 - receives the intents; Only responds to the intents sent by A1 using its defined permission. A2
has two activities.
  * The first activity displays information about 10 attractions in the city of Chicago.
  * The second activity shows 6 Chicago restaurants.
  * There is an options menu to switch between the activities.
  * Each activity contains two fragments:
    * The first fragment displays a list of items(attractions/restaurants). If an item is selected, it is highlited.
    * The second fragment shows the official web site of the highlighted item using a browser.  
  * Configuration changes:
    * Portrait mode: the two fragments are displayed on different screens. Initially the first fragment is displayed until a user selects an item, then it disappears and the second fragment is shown. Pressing the “back” soft button on the device returns the device to the original configuration (first fragment only).
    * Landscape mode: initially only the first fragment is displayed. When the user selects an item, both fragments are shown on the same screen (1:2 ratio). Pressing the “back” button returns device to initial configuration.
