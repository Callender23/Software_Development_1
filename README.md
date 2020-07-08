# Concurrency Assignment

Program caculates sum of an 200 million array populated with random values [1 - 10)
and returns the time in millseconds for using single thread and multithreading.

## Consideration for multithreading
Multithreading is one thing that can improve performace when it comes to progamming in java. This however, can aslo be inefficient if not use correctly. Multiple factors should be consider when using multithreading.

1. Size of elements: We want to make sure we have big sufficenlty large values to utlize multhreading. What is sufficiently large? When it comes to empirical runtime analysis the answer is, "it depends." For a function that is looking at string lengths, we might intuit that string lengths of 1, 10, and 20 aren't long enough to tell us anything meaningful about runtime. But a string of length 1,000,000,000 will be unreasonable to deal with if we're passing it to an O(2^n) function.

2. Amdahl's law: Sometimes having more cores with threads does not equate to more things getting caculated. This is attriubted to something called Amdahal's
law that state that in any program there is always a percetang the can not be run in parallel and there is another percentage that can be run
in parallel. The law predicts that for every given parallel portion (roughly 75%) of a program you can speed up exectuion only so sar(4 times) even if you use more and more processor to do the work. Essentially having to many threads can cause bottlenecking since you have threads waiting for others to be done and the program might not even required them at all which is just more work being done causing increase in our execution time.

3. Physical Hardware: One of the most important thing that effects our performace would be the CPU that we have. If we have a CPU which has more than one processor avaliable then you have avaliable. In ideal conditions having something like 4 cores can allow might allow for faster caculations such as primes numbers to be 4 times faster using 4 threads.

## Analysis of program
when presented with values under 2 millions multithreading became inefficent to use as the cost to make the threads in terms of execution time and the realtively "small" value
means that having a single thread exection in much more efficiency. This is shown in the following screenshot below

![concurreny analysis of execution](https://user-images.githubusercontent.com/51206691/86875801-566dc400-c0b1-11ea-830c-43cfe6ab40d5.PNG)


When values were around 200 millions we started to see the efficiency and power of multithreading as our execution time for multithreading was half of that for single thread run of the program. This was expected as now the extra threads are much more of a use to use now that we have a large sample size to caculated. This can be seen with the screenshot below.

![Concurrency image](https://user-images.githubusercontent.com/51206691/86876335-7e115c00-c0b2-11ea-86b4-6f7dcdbf2f8e.PNG)
