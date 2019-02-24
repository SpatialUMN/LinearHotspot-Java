# Significant Linear Hotspot Discovery
* [Why Linear Hotspot](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#Why-Linear-Hotspot)  
* [What Can You Get](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#What-can-you-get)  
* [Usage](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#Usage)
  * [Input data format](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#Input-data-format)  
    * [Generate Information for Monte Carlo Simulation](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#Generate-Information-for-Monte-Carlo-Simulation)  
    * [Detect Linear Hotspots](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#Detect-Linear-Hotspots)    
  * [Download and Run](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#Download-and-Run)   
    * [To Generate Information for Monte Carlo Simulation](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#To-Generate-Information-for-Monte-Carlo-Simulation)   
    * [To Detect Linear Hotsots](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#To-detect-linear-hotsots) 

* [Code Explanation(java diagram)](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Java-Class-Diagram)  
* [Case Study](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Case-Study)   
* [Bug Report](https://github.com/SpatialUMN/LinearHotspot-Java/issues)    
* [Use Your Own Code](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/README.md#Use-Your-Own-Code)

***

## Why Linear Hotspot
Linear Hotspot is a line shape area of significant activity. Our [motivation](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Motivation-of-Developing-Linear-Hotspot-Detection-Method) of developing linear hotspot detection method is to identify accident prone road segment.

## What Can You Get  
You will get the shortest path between two activities. The shortest path has high density ratio that pass the threshold, so we can call it a potential hotspot. Then Monte Carlo simulation is executed. Those potential hotspots that have low p-value will get picked to be the linear hotspots.   
![Linear](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/image/linear.PNG)  
[Basic Concepts](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Basic-Concepts) can help better understanding the problem
  
For academic users, see [here](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Algorithm-Explanation) for algorithm explanation.
## Usage   
### Input data format     
#### Generate Information for Monte Carlo Simulation  
* We need 2 input files: `Node` and `Edge`:    
[Node](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Node.txt) is the road intersaction file that has three attributes: Node ID, Longitude and Latitude. Attributes are separated by `;`    
[Edge](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Edge.txt) is the road segment file that has four attributes:   Edge ID, Start node id, End node id, and Distance between start node and end node (weight). Attributes are separated by `;`  
  
#### Detect Linear Hotspots  
* We need 3 input files: `Node`, `Edge` and `Activity`:     
The `Node` and `Edge` file formats are the same as the files mentioned in previous session with same names.    
[Activity](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Activity.txt) is the car accident file (or whatever interesting events in the research) that has five attributes:   Activity ID, Longitude, Latitude, Edge start node id and Edge end node id. The edge here is the one that contains the activity. Attributes are separated by `;`  

* The output file contains linear hotspot road segment:    
[result]() has 4 attributes:  
Activity 1 id, Activity 2 id, Shortest path between two activities and Density ratio    

### Download and Run  
#### [How to import a GitHub project into Eclipse](https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse)  

#### To Generate Information for Monte Carlo Simulation:    
After import project into Eclipse, find the [GetHotspots.java](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/src/GetHotspots.java) file, change line 6 - 11   
`nodePath`and`edgePath` are the path to your node and edge files.   
`outputActivityFilePrefix` is the path to the folder that you will save all the generated activity files from Monte Carlo simulation.   
`filenumber` is the number of time to run Monte Carlo simulation. 
`result` is the path to save the final outcome of linear hotspots.  
`nNewActivities` is the number of total activities in your activity file.  
`pValue` is the p-value that measure the significent of your hotspots.  

Then run GetHotspots.java file.     

#### To Detect Linear Hotsots:   
Find the [RunLinear.java](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/src/RunLinear.java) file, change line 4 and 5. Set the `activityPath` to your activity file that probably contain hotspots. Change `result` to where you want the final result to be put. Finally run RunLinear.java file 


## Use your own code  
The linear hotspots detection process can be splited into small sessions. You can replace each session with your own code based on the method you need in your own project. Here is a list of session your can manipulate with.
* [Data Cleaning](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Add-On-Examples): Replace this file using your own data cleaning method.  
* [StatisticMeasure](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Add-On-Examples): Replace this file to define the "hotspots" in your project.  

