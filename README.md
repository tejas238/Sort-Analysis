# SortAnalysis

## ABSTRACT

This project seeks to analyze fast sorting routines commonly used on arbitrarily sized data structures.
This choice was selected as the group expressed interest at the number of factors that we could analyze
right off the bat– general performance differences, bound and special case analysis, effects of applying
improvement, and theoretical comparison. Our cumulative strength seemed to indicate we all knew
how to code these algorithms, and apply formal analysis using the master theorem. Also, as sorting is a
major component of several more complex algorithms and the knowledge of the best choice for a
particular use case is somewhat obscure, this project seemed an interesting opportunity to explore this
topic.

## DETAILS

#### IMPLEMENTATION LANGUAGE: Java

#### COLLABORATION LINK: https://github.com/tejas238/SortAnalysis

#### REPORT DOCUMENTATION:

https://docs.google.com/document/d/1beyYESERPhXhTPyIs5ruoNSvbkhadfW858SHxkHGwVw/edit#hea
ding=h.nj23sjpj5u

#### COMMUNICATION TOOL: Slack

#### PRELIMINARY FEATURES IN REPORT:

## Formal

## Tasks

```
Introduction This section will provide a brief overview of the project
purpose, scope and direction.
Algorithm, worked
example, 3 case
analysis
```
```
We’re choosing the quicksort, merge sort and heapsort as
procedures on which we will do a best case, worst case and
average case big-oh analysis. Also this section will include
some general examples to illustrate how the algorithm works
and also how we expect the experimental results to fare in
relation to this analysis.
Space
requirement
analysis
```
```
This section will analyze the space requirements for each of
the three algorithms in the best, worst and average case and
also how our implementation may/may not differ from the
space requirements outlined in this analysis.
Special case study
and viable
improvements
```
```
This section will include edge cases where each of the
algorithms will encounter difficulties without certain
improvements, and what we expect these improvements will
```

```
have on asymptotic efficiency for both space and time when
implemented. In addition, this section will also discuss the
commonality of said special case input showing up in random
data.
```
## Practical

## Tasks

```
Efficiency testing This section will go over the entire implementation of how we
plan to test efficiency of each of our implemented algorithms
i.e.
 selection of sample data size points, determination of
minimum time execution from multiple executions for
each of these chosen points, how we chose an
appropriate number of re-runs for each sample data
point and how we generated graphs from all the data
for each sample point. In addition, we would also
need to discuss what happens when execution time
for a certain data size reaches zero.
 Also, creation of different types of data input would
be a topic to consider– totally (pseudo) random data,
duplicated data (some duplicates and all duplicates)
and uniquely random data. For the two underlined
types, we should have versions that are in almost
sorted, sorted, reverse sorted, almost reverse sorted
and random ordering. These different data types can
be produced for each sample data size n and
measured for each algorithm. A discussion of the
timing libraries used for testing will also be included.
Correctness
Testing
```
```
In this section, a brief discussion of the function(s) used to test
correctness of sorted output will be included proving how the
algorithms are correctly implemented for all the sample data
at the very least. Unit testing libraries will use this function(s)
after measuring efficiency to confirm correctness.
```
## Analysis

## Tasks

```
Summary of
results
```
```
A presentation of all the graphs for each type of data will be
displayed here. Specifically, that is 12 graphs per algorithm. A
discussion of the library used for automating the graph
drawing will also be included.
Analysis and
Conclusion
```
```
The analysis section will plough through the data and discover
differences from the formal results and try to explain them.
Additionally, this section will examine the performance of the
algorithm for special cases and compare it with formal
analysis. The conclusion will discern which algorithm will
perform best under which circumstance.
```
We decided on Java as our coding language due to both the availability of appropriate libraries for
automated testing and timing and also the ease in implementation from a programmer point of view
compared to C/C++. Additionally, all of us were comfortable with the concepts of java and more or less
understood the root causes of timing anomalies that could spring from a virtual machine based
language.


### TIME PLAN

```
Week of 19th Feb Work on all formal tasks for each of the three algorithms– time and space
efficiency, worked examples, improvements and edge case examination. Also
begin to implement algorithm. (We will use Microsoft Office’s formula utility or
Latex for any written analysis).
Week of 26th Feb Start on the practical tasks and bring together working implementations of all
algorithms. Work simultaneously in putting together last week’s contents into a
report format while working on correctness and efficiency testing code. Finish all
coding by the following Tuesday.
Week of 5th Mar Analyze data and create report for past week’s work. Keep close watch on rubric
while doing so. Report should discuss in detail coding choices for efficiency and
correctness testing as mentioned on the previous page. Finish by Friday the 8th.
Week of 12th Mar Add the introduction, summary of results, analysis of results and conclusion by
the 12th and brush up the rest of the report by the 14th prior to submission.
```
For this project, we intend to work by collaborating using Git for all the coding activity and using Slack
for any general questions/comments and submitting written work before including into our report.
Intelli-J for Java has a feature which connects all code to a pre-specified online repository so we also
intent to use that for committing changes. For report preparation, we have a Google Doc document
initialized, and will use that for drafting.

Initially, we have decided for Tejas to work on the heapsort algorithm, Dante on the quicksort and
Thomas on the merge sort although we can always overlook each other’s work once they are brought
into the report. Tejas will also begin working on the testing framework that can be utilized on any of the
three algorithms and once an initial commit is made, Dante and Thomas can begin to test their code on
this framework.
