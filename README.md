# Significant Linear Hotspot Discovery
## Why Linear Hotspot
Linear Hotspot is a line shape area of significant activity. Our [motivation](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Motivation-of-Developing-Linear-Hotspot-Detection-Method) of developing linear hotspot detection method is to identify accident prone road segment.

## Problem Statement
[Basic Concepts](https://github.com/SpatialUMN/LinearHotspot-Java/wiki/Basic-Concepts) can help better understanding the problem

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
