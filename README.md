# Significant Linear Hotspot Discovery
## Why Linear Hotspot
Linear Hotspot is a line shape area of significant activity. Our [motivation](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Motivation-of-Developing-Linear-Hotspot-Detection-Method) of developing linear hotspot detection method is to identify accident prone road segment.

## What can you get from this algorithm?  
You will get the shortest path between two activities. The shortest path has high density ratio that pass the threshold, so we can call it a potential hotspot. Then Monte Carlo simulation is executed. Those potential hotspots that have low p-value will get picked to be the linear hotspots.   
![Linear](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/image/linear.PNG)  
[Basic Concepts](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Basic-Concepts) can help better understanding the problem
  
For academic users, see [here](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Algorithm-Explanation) for algorithm explanation.
## Usage   
### Input data format     
#### Detect Potential Linear Hotspots  
* We need 3 input files: `Node`, `Edge` and `Activity`:     
[Node](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Node.txt) is the road intersaction file that has three attributes: Node ID, Longitude and Latitude. Attributes are separated by `;`    
[Edge](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Edge.txt) is the road segment file that has four attributes:   Edge ID, Start node id, End node id, and Distance between start node and end node (weight). Attributes are separated by `;`  
[Activity](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Activity.txt) is the car accident file (or whatever interesting events in the research) that has five attributes:   Activity ID, Longitude, Latitude, Edge start node id and Edge end node id. The edge here is the one that contains the activity. Attributes are separated by `;`  

* The output file contains potential linear hotspot road segment:   
[PotentialLH](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/PotentialLH.txt) has 7 attributes:   Activity 1 id, Activity 2 id,Cloest node to activity 1, Cloest node to activity2, Density ratio between two activities, Length of this shortest path and Number of total activities on this shortest path   

#### Detect Linear Hotspots
* We need 3 input files: `Node`,`Edge` and `PotentialLH`:  
The formats are the same as the files mentioned in previous session with same names.  
* The output file contains linear hotspot road segment:    
[result]() has 4 attributes:  
Activity 1 id, Activity 2 id, Shortest path between two activities and Density ratio


### Download and Run  
[How to import a GitHub project into Eclipse](https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse)  

* To detect potential linear hotspots:  
After import project into Eclipse, find the [RunLinear.java](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/src/RunLinear.java) file, change line 5, 6 and 7. Set the `nodePath`, `edgePath` and `activityPath` to your data file, and run RunLinear.java file  
* To detect linear hotsots:  
Find the [GetHotspots.java]() file, change line 5 `outputActivityFilePrefix`, Line 6 `filenumber` and Line 7 `result`.  
`outputActivityFilePrefix` is the path to save all the gemerated activity files used for Monte Carlo simulation.  
`filenumber` is the number of time to run Monte Carlo simulation. 
`result` is the path to save the final outcome of linear hotspots.  
Then run GetHotspots.java file.  


## Code Explanation
[Java class diagram](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Java-Class-Diagram) can help you understand the code structure.

## Case Study
Please click [here](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Case-Study) to see a demonstration of this algorithm.  

## Bug Report
To search for or report bugs, please see LinearHotspot's [issues](https://github.com/SpatialUMN/LinearHotspot-Java/issues).

## Use your own code  
The linear hotspots detection process can be splited into small sessions. You can replace each session with your own code based on the method you need in your own project. Here is a list of session your can manipulate with.
* [Data Cleaning](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Add-On-Examples): Replace this file using your own data cleaning method.  
* [StatisticMeasure](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Add-On-Examples): Replace this file to define the "hotspots" in your project.  

