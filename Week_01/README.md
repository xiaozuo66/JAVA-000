Lession  02    
Java命令

jstat -gc pid 1000 1000   第一个1000是毫秒，第二个是打印次数

jmap -heap pid打印堆内存的配置使用情况
-histo pid看哪些类占用的空间最多
-dump 堆内存


jstack -l pid 显示锁信息
jstac -F/-m

jcmd  是上述命令的整合

图形化工具
jconsole
jvisualvmGC
jmc

gc
gc时 为什么新生代复制，老年代移动？
gc三种算法：标记清除、标记复制、标记-清除-整理（老年代使用）
gc收集器种类：
串行GC（Serial）--改进 paraNew
-XX:+UseSerialGC
年轻代使用标记-复制算法，老年代标记-清除-整理

并行GC（Parallel）
-XX:+UseParallelGC
-XX:+UseParallelOldGC
-XX:ParallelGCThreads=N来指定GC线程数
年轻代标记-复制，老年代标记-清除-整理
适用于多核服务器
jdk 8版本之前默认GC都是并行，9是G1，

CMS GC（最大可能的并发）
-XX:+UseConcMarkSweepGC
年轻代标记-复制，老年代标记-清除（不做整理，节省时间，减少STW）
使用CPU核心数的1/4，另外的3/4可以同时做业务处理。
CMS-GC六个阶段：
初始标记（发生STW，标记根对象）
并发标记（遍历老年代，标记所有存活的对象）
并发预清理（先标记一遍，因为可能发生变化）
最终标记（再次标记，会发生STW）
并发清除（删除不使用的对象）
并发重置（重置CMS内部数据）

G1 GC（意为垃圾优先）,设计目标：把STW的时间变成可控
-XX:+UseG1GC
-XX:MaxGCMillis=50  设置最长暂停时间
分为2048个Region
CMS和G1可以设置启动垃圾收集的阈值
-XX:G1NewSizePercent 初始年轻代占整个heap的大小 5%
-XX:G1MaxNewSizePercent 最大年轻代占整个堆大小 60%
G1可以设置每个Region的大小
-XX:MaxGCPauseMills 预期每次GC暂停时，默认2000毫秒
G1阶段（与CMS类似）：初始化标记、Root区扫描、并发标记、再次标记、清理

ZGC jdk11推出的，只支持linux64位系统，堆越大越好，STW时间很短很短，GC频率
jdk15开始支持mac和windows
ShennandoahGC