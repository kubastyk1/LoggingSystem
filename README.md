How to run:
1) Import project
2) Add HSQLDB JAR to the libraries
3) Pass the file path as a first argument(example: "src/main/resources/logfile.txt")

What was done:
- Multithreading with specified number of threads done, by executors
- Events form .txt file are parsed dynamically, parsing big files is possible, reduced risk of outOfMemory 
- Test cases, only basic logic
- Connection to HSQLDB and Object Relational Mapping(Hibernate)

TODO in the future:
- Add logging system
- Implement integration tests with DB
