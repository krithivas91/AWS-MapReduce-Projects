Description

Configured Apache Hadoop in psuedo distributed mode on Amazon EC2 instances. Deployed a simple MapReduce Application that takes as input a deck of cards, tells the user which of the cards are missing from the deck.



OUTPUT
———————————————————————————————————————


krithivass-MacBook-Pro:Downloads vasu$ ssh -i rambo3.pem ec2-user@52.37.33.163

       __|  __|_  )
       _|  (     /   Amazon Linux AMI
      ___|\___|___|

https://aws.amazon.com/amazon-linux-ami/2015.09-release-notes/
No packages needed for security; 3 packages available
Run "sudo yum update" to apply all updates.

[ec2-user@ip-172-31-35-191 ~]$ hadoop jar poker.jar input/ output
10/03/16 03:34:43 INFO Configuration.deprecation: session.id is deprecated. Inst                                           ead, use dfs.metrics.session-id
10/03/16 03:34:43 INFO jvm.JvmMetrics: Initializing JVM Metrics with processName                                           =JobTracker, sessionId=
10/03/16 03:34:43 WARN mapreduce.JobResourceUploader: Hadoop command-line option                                            parsing not performed. Implement the Tool interface and execute your applicatio                                           n with ToolRunner to remedy this.
10/03/16 03:34:43 INFO input.FileInputFormat: Total input paths to process : 1
10/03/16 03:34:43 INFO mapreduce.JobSubmitter: number of splits:1
10/03/16 03:34:43 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_lo                                           cal1232719736_0001
10/03/16 03:34:43 INFO mapreduce.Job: The url to track the job: http://localhost                                           :8080/
10/03/16 03:34:43 INFO mapreduce.Job: Running job: job_local1232719736_0001
10/03/16 03:34:43 INFO mapred.LocalJobRunner: OutputCommitter set in config null
10/03/16 03:34:44 INFO mapred.LocalJobRunner: OutputCommitter is org.apache.hado                                           op.mapreduce.lib.output.FileOutputCommitter
10/03/16 03:34:44 INFO mapred.LocalJobRunner: Waiting for map tasks
10/03/16 03:34:44 INFO mapred.LocalJobRunner: Starting task: attempt_local123271                                           9736_0001_m_000000_0
10/03/16 03:34:44 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
10/03/16 03:34:44 INFO mapred.MapTask: Processing split: file:/home/ec2-user/inp                                           ut/deck.txt:0+206
10/03/16 03:34:44 INFO mapred.MapTask: (EQUATOR) 0 kvi 26214396(104857584)
10/03/16 03:34:44 INFO mapred.MapTask: mapreduce.task.io.sort.mb: 100
10/03/16 03:34:44 INFO mapred.MapTask: soft limit at 83886080
10/03/16 03:34:44 INFO mapred.MapTask: bufstart = 0; bufvoid = 104857600
10/03/16 03:34:44 INFO mapred.MapTask: kvstart = 26214396; length = 6553600
10/03/16 03:34:44 INFO mapred.MapTask: Map output collector class = org.apache.h                                           adoop.mapred.MapTask$MapOutputBuffer
10/03/16 03:34:44 INFO mapred.LocalJobRunner:
10/03/16 03:34:44 INFO mapred.MapTask: Starting flush of map output
10/03/16 03:34:44 INFO mapred.MapTask: Spilling map output
10/03/16 03:34:44 INFO mapred.MapTask: bufstart = 0; bufend = 320; bufvoid = 104                                           857600
10/03/16 03:34:44 INFO mapred.MapTask: kvstart = 26214396(104857584); kvend = 26                                           214308(104857232); length = 89/6553600
10/03/16 03:34:44 INFO mapred.MapTask: Finished spill 0
10/03/16 03:34:44 INFO mapred.Task: Task:attempt_local1232719736_0001_m_000000_0                                            is done. And is in the process of committing
10/03/16 03:34:44 INFO mapred.LocalJobRunner: map
10/03/16 03:34:44 INFO mapred.Task: Task 'attempt_local1232719736_0001_m_000000_                                           0' done.
10/03/16 03:34:44 INFO mapred.LocalJobRunner: Finishing task: attempt_local12327                                           19736_0001_m_000000_0
10/03/16 03:34:44 INFO mapred.LocalJobRunner: map task executor complete.
10/03/16 03:34:44 INFO mapred.LocalJobRunner: Waiting for reduce tasks
10/03/16 03:34:44 INFO mapred.LocalJobRunner: Starting task: attempt_local123271                                           9736_0001_r_000000_0
10/03/16 03:34:44 INFO mapred.Task:  Using ResourceCalculatorProcessTree : [ ]
10/03/16 03:34:44 INFO mapred.ReduceTask: Using ShuffleConsumerPlugin: org.apach                                           e.hadoop.mapreduce.task.reduce.Shuffle@73dd9a09
10/03/16 03:34:44 INFO reduce.MergeManagerImpl: MergerManager: memoryLimit=36328                                           5696, maxSingleShuffleLimit=90821424, mergeThreshold=239768576, ioSortFactor=10,                                            memToMemMergeOutputsThreshold=10
10/03/16 03:34:44 INFO reduce.EventFetcher: attempt_local1232719736_0001_r_00000                                           0_0 Thread started: EventFetcher for fetching Map Completion Events
10/03/16 03:34:44 INFO reduce.LocalFetcher: localfetcher#1 about to shuffle outp                                           ut of map attempt_local1232719736_0001_m_000000_0 decomp: 368 len: 372 to MEMORY
10/03/16 03:34:44 INFO reduce.InMemoryMapOutput: Read 368 bytes from map-output                                            for attempt_local1232719736_0001_m_000000_0
10/03/16 03:34:44 INFO reduce.MergeManagerImpl: closeInMemoryFile -> map-output                                            of size: 368, inMemoryMapOutputs.size() -> 1, commitMemory -> 0, usedMemory ->36                                           8
10/03/16 03:34:44 INFO reduce.EventFetcher: EventFetcher is interrupted.. Return                                           ing
10/03/16 03:34:44 INFO mapred.LocalJobRunner: 1 / 1 copied.
10/03/16 03:34:44 INFO reduce.MergeManagerImpl: finalMerge called with 1 in-memo                                           ry map-outputs and 0 on-disk map-outputs
10/03/16 03:34:44 WARN io.ReadaheadPool: Failed readahead on ifile
EBADF: Bad file descriptor
        at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posix_fadvise(Native Met                                           hod)
        at org.apache.hadoop.io.nativeio.NativeIO$POSIX.posixFadviseIfPossible(N                                           ativeIO.java:267)
        at org.apache.hadoop.io.nativeio.NativeIO$POSIX$CacheManipulator.posixFa                                           dviseIfPossible(NativeIO.java:146)
        at org.apache.hadoop.io.ReadaheadPool$ReadaheadRequestImpl.run(Readahead                                           Pool.java:206)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.                                           java:1145)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor                                           .java:615)
        at java.lang.Thread.run(Thread.java:745)
10/03/16 03:34:44 INFO mapred.Merger: Merging 1 sorted segments
10/03/16 03:34:44 INFO mapred.Merger: Down to the last merge-pass, with 1 segmen                                           ts left of total size: 358 bytes
10/03/16 03:34:44 INFO reduce.MergeManagerImpl: Merged 1 segments, 368 bytes to                                            disk to satisfy reduce memory limit
10/03/16 03:34:44 INFO reduce.MergeManagerImpl: Merging 1 files, 372 bytes from                                            disk
10/03/16 03:34:44 INFO reduce.MergeManagerImpl: Merging 0 segments, 0 bytes from                                            memory into reduce
10/03/16 03:34:44 INFO mapred.Merger: Merging 1 sorted segments
10/03/16 03:34:44 INFO mapred.Merger: Down to the last merge-pass, with 1 segmen                                           ts left of total size: 358 bytes
10/03/16 03:34:44 INFO mapred.LocalJobRunner: 1 / 1 copied.
10/03/16 03:34:44 INFO Configuration.deprecation: mapred.skip.on is deprecated.                                            Instead, use mapreduce.job.skiprecords
10/03/16 03:34:44 INFO mapred.Task: Task:attempt_local1232719736_0001_r_000000_0                                            is done. And is in the process of committing
10/03/16 03:34:44 INFO mapred.LocalJobRunner: 1 / 1 copied.
10/03/16 03:34:44 INFO mapred.Task: Task attempt_local1232719736_0001_r_000000_0                                            is allowed to commit now
10/03/16 03:34:44 INFO output.FileOutputCommitter: Saved output of task 'attempt                                           _local1232719736_0001_r_000000_0' to file:/home/ec2-user/output/_temporary/0/tas                                           k_local1232719736_0001_r_000000
10/03/16 03:34:44 INFO mapred.LocalJobRunner: reduce > reduce
10/03/16 03:34:44 INFO mapred.Task: Task 'attempt_local1232719736_0001_r_000000_                                           0' done.
10/03/16 03:34:44 INFO mapred.LocalJobRunner: Finishing task: attempt_local12327                                           19736_0001_r_000000_0
10/03/16 03:34:44 INFO mapred.LocalJobRunner: reduce task executor complete.
10/03/16 03:34:44 INFO mapreduce.Job: Job job_local1232719736_0001 running in ub                                           er mode : false
10/03/16 03:34:44 INFO mapreduce.Job:  map 100% reduce 100%
10/03/16 03:34:44 INFO mapreduce.Job: Job job_local1232719736_0001 completed suc                                           cessfully
10/03/16 03:34:45 INFO mapreduce.Job: Counters: 33
        File System Counters
                FILE: Number of bytes read=9766
                FILE: Number of bytes written=521590
                FILE: Number of read operations=0
                FILE: Number of large read operations=0
                FILE: Number of write operations=0
        Map-Reduce Framework
                Map input records=23
                Map output records=23
                Map output bytes=320
                Map output materialized bytes=372
                Input split bytes=99
                Combine input records=0
                Combine output records=0
                Reduce input groups=7
                Reduce shuffle bytes=372
                Reduce input records=23
                Reduce output records=7
                Spilled Records=46
                Shuffled Maps =1
                Failed Shuffles=0
                Merged Map outputs=1
                GC time elapsed (ms)=26
                CPU time spent (ms)=0
                Physical memory (bytes) snapshot=0
                Virtual memory (bytes) snapshot=0
                Total committed heap usage (bytes)=241442816
        Shuffle Errors
                BAD_ID=0
                CONNECTION=0
                IO_ERROR=0
                WRONG_LENGTH=0
                WRONG_MAP=0
                WRONG_REDUCE=0
        File Input Format Counters
                Bytes Read=206
        File Output Format Counters
                Bytes Written=84
[ec2-user@ip-172-31-35-191 ~]$ cd output
[ec2-user@ip-172-31-35-191 output]$ ls
part-r-00000  _SUCCESS
[ec2-user@ip-172-31-35-191 output]$ vi part-r-00000
1 diamond
2
3       
6 club
7  
8       
9 heart
10
11
12
13 
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
~
                                                                                                         1,1           All
