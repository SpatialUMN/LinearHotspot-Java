# Significant Linear Hotspot Discovery
## Why Linear Hotspot
Linear Hotspot is a line shape area of significant activity. Our [motivation](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Motivation-of-Developing-Linear-Hotspot-Detection-Method) of developing linear hotspot detection method is to identify accident prone road segment.

## What can you get from this algorithm?  
You will get the shortest path between two activities. The shortest path has high density ratio that pass the threshold, so we can call it a potential hotspot. Then Monte Carlo simulation is executed. Those potential hotspots that have low p-value will get picked to be the linear hotspots.   
![Linear](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/image/linear.PNG)  
[Basic Concepts](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Basic-Concepts) can help better understanding the problem
  
For academic users, see [here](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Algorithm-Explanation) for algorithm explanation.
## Usage (how to download and run)
* Input data format   
`Node`  
`Activity`  
* [How to import a GitHub project into Eclipse](https://github.com/collab-uniba/socialcde4eclipse/wiki/How-to-import-a-GitHub-project-into-Eclipse)  

## Code Explanation
[Java class diagram](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Java-Class-Diagram) can help you understand the code structure.

## Case Study

## Bug Report
To search for or report bugs, please see LinearHotspot's [issues](https://github.com/SpatialUMN/LinearHotspot-Java/issues).
