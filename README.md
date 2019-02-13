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
* We need 3 input files: `Node`, `Edge` and `Activity`.    
[Node](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Node.txt) is the road intersaction file that has three attributes: Node ID, Longitude and Latitude. Attributes are separated by `;`    
[Edge](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Edge.txt) is the road segment file that has four attributes: Edge ID, Start node id, End node id, and Distance between start node and end node (weight). Attributes are separated by `;`  
[Activity](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/Activity.txt) is the car accident file (or whatever interesting events in the research) that has five attributes: Activity ID, Longitude, Latitude, Edge start node id and Edge end node id. The edge here is the one that contains the activity. Attributes are separated by `;`  
* The output file will contain potential linear hotspot road segment
[PotentialLH](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/SampleData/PotentialLH.txt) has five attributes: Activity 1 id, Activity 2 id, Shortest path between these two activities, Number of total activities on this shortest path and Length of this shortest path

#### Detect Linear Hotspots
* Both input and output data format in this part are the same as the output from the previous step.  
Inputs are potential linear hotspots without statictic significent. Outputs are linear hotspots.


### Download and Run  
[How to import a GitHub project into Eclipse](https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse)  

## Code Explanation
[Java class diagram](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Java-Class-Diagram) can help you understand the code structure.

## Case Study

## Bug Report
To search for or report bugs, please see LinearHotspot's [issues](https://github.com/SpatialUMN/LinearHotspot-Java/issues).
