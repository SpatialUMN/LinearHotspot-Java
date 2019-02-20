# Significant Linear Hotspot Discovery

## Why Linear Hotspot
Linear Hotspot is a line shape area of significant activity. Our [motivation](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Motivation-of-Developing-Linear-Hotspot-Detection-Method) of developing linear hotspot detection method is to identify accident prone road segment.  

## Problem Statement  
**Given:**
1) A spatial network G = (N, E) with a set of geo-referenced activities A, each if which is associated with an edge.
2) A density ratio threshold,  θ<sub>λ
3) A p-value threshold, θ<sub>p and the corresponding number of Monte Carlo simulations needed, m,

**Find:**    
All shortest paths r ∈ R with λ<sub>r</sub>≥ θ<sub>λ</sub>, p-value ≤  θ<sub>p

**Objective:**  
Computational efficiency

**Constraints:**
1) r<sub>i</sub> ∈ R is not a sub-path of r<sub>j</sub> ∈ R for ∀r<sub>i</sub>, r<sub>j</sub> ∈ R
where r<sub>i</sub> ≠ r<sub>j</sub>,
2) ∀r<sub>i</sub>  ∈ R is not shorter than a minimum distance (φ)
threshold θ<sub>φ</sub>
3) Correctness and completeness 


## What can you get  
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
Find the [GetHotspots.java]() file, change line 6 - 11   
`nodePath`and`edgePath` are the path to your node and edge files.   
`outputActivityFilePrefix` is the path to the folder that you will save all the generated activity files from Monte Carlo simulation.  
Line 6 `filenumber` and Line 7 `result`.  
`outputActivityFilePrefix` is the path to save all the gemerated activity files used for Monte Carlo simulation.  
`filenumber` is the number of time to run Monte Carlo simulation. 
`result` is the path to save the final outcome of linear hotspots.  
Then run GetHotspots.java file.    
#### To Detect Linear Hotsots:   
After import project into Eclipse, find the [RunLinear.java](https://github.com/SpatialUMN/LinearHotspot-Java/blob/master/src/RunLinear.java) file, change line 5, 6 and 7. Set the `nodePath`, `edgePath` and `activityPath` to your data file, and run RunLinear.java file 


## Use your own code  
The linear hotspots detection process can be splited into small sessions. You can replace each session with your own code based on the method you need in your own project. Here is a list of session your can manipulate with.
* [Data Cleaning](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Add-On-Examples): Replace this file using your own data cleaning method.  
* [StatisticMeasure](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Add-On-Examples): Replace this file to define the "hotspots" in your project.  

