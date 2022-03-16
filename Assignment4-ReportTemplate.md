**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#4 – Mutation Testing and Web app testing**

| Group \#:       | 9  |
|-----------------|---|
| Student Names:  | Brian Chen  |
|                 | Zheng Chen  |
|                 | Rui Guan  |
|                 | Weitao Wu  |

# Introduction


# Analysis of 10 Mutants of the Range class 

# Report all the statistics and the mutation score for each test class

DataUtilityTest:

![WechatIMG1797](https://user-images.githubusercontent.com/74373450/158275924-0d96efa5-82b5-460e-b025-4a5881a96710.jpeg)



# Analysis drawn on the effectiveness of each of the test classes

# A discussion on the effect of equivalent mutants on mutation score accuracy

Equivalent mutants are functionally identical to the original class although syntactically different. Their exsitence does not mean that there are some functionalities of classes that we have not tested. The way to detect the equivalent mutants is to check if the mutant does the same functionality as the original class does. The comparison statement can easily generate the equivalent mutants. For example, "!=" can generate mutants of "less or equal" and "greater or equal". "Return true" can be replaced by "return -1". 

Benefits: Equivalent mutants cannot simulate bugs. Finding the equivalent mutants can make us focus more on the non-equivalent mutants which are significant to help us find the errors and bugs.

Disavantages:They will decrease the accuracy of mutation score because there might be a lot of equivalent mutants generated.

# A discussion of what could have been done to improve the mutation score of the test suites


DataUtilityTest:

In the original test file, the test cases had 92% mutation coverage. According to the pitest report, the mutants that were not killed by the test cases were equivalent mutants except two mutants. These two mutants are both "removed call to org/jfree/chart/util/ParamChecks::nullNotPermitted", which are unacceptable. They mean that the null parameters are not tested in our previous test cases. Therefore, adding two new test cases: testNullValue2DcalculateRowTotal() and testNullKeyedValueGetCumulativePercentages() can help kill those two mutants. Since the rest mutants were equivalent mutants and could not be killed, the mutant score will remain 92%.

# Why do we need mutation testing? Advantages and disadvantages of mutation testing

Advantages: Mutant testing can help us detect more bugs and error from the code. Plus, it can help us increase the coverage of our tests. After mutant testing, we can get a more stable and realiable system. 

Disavantages: Using mutant testing takes a lot of time and CPU of our computers. Some mutants are too complex for us to find a bug. 

# Explain your SELENUIM test case design process

We chose 8 functionalities (2 per member) from the BestBuy.ca/en-ca website

Best Buy:

Search: this was tested by using and valid search item and an invalid search item to see if we could achieve the expected results

Sort By: this was tested by searching a valid item, then creating test cases to see if each sort option achieved the expected results

# Explain the use of assertions and checkpoints

# how did you test each functionaity with different test data

# Discuss advantages and disadvantages of Selenium vs. Sikulix

# How the team work/effort was divided and managed


# Difficulties encountered, challenges overcome, and lessons learned

# Comments/feedback on the lab itself
