1、直接在命令行执行insert，大概10万条需要10分钟左右，而且后续越来越慢。未等到最终执行结束。
2、load data local infile '/Library/WorkSpace/test.sql' into table USER_INFO;通过命令直接加载本地文件的方式，
未能成功，提示ERROR 2 (HY000): File 'LibraryWorkSpace	est.sql' not found (Errcode: 2 - No such file or directory)。