volatile
强制从主内存刷数据
适用于一个线程写，多个线程读
可以避免重排序
 保证可见性并禁止指令重排，但是不保证原子性

执行时可以使用以下命令查看汇编指令，最后的Counter是类名，incr是要查看的方法名
-server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:-Inline -XX:CompileCommand=print,*Counter.incr

MESI协议（用于解决内存可见性问题）：
M：Modified，这行数据有效，但是被修改了，和内存中数据不一致，数据只存在于本Cache
E：Exclusive，这行数据有效，和内存中数据一致，数据只存在于本Cache中
S：Shared，这行数据有效，和内存中数据一致，数据分布在很多Cache中
I：Invalid，这行数据无效
假如有4个Core，每个Core的Cache控制器不仅知道自己的读写操作，也监听其他Cache的读写操作：
1、Core1从内存中加载了变量X，值为10，这时Core1中缓存变量X的cache line状态是E
2、Core2也从内存中加载了变量X，则Core1和Core2中缓存变量X的cache line状态转为S
3、Core3也从内存中加载了变量X，并且修改成了20，这时Core3中缓存变量X的cache line状态是M，Core1和Core2缓存变量X的状态变成了I(无效)；

MESIF协议，多了F（forward）,一个cache line是F状态，可以把数据直接传给其他内核

LoadBuffer和StoreBuffer合称排序缓冲，Buffer与L1进行数据传输时，CPU无需等待：
1、CPU执行load读数据时，把读请求放到LoadBuffer缓冲区，这样就不用等待其他CPU响应，先进行下面的操作，稍后再处理这个读请求的结果。
2、CPU执行store写数据时，把数据写到StoreBuffer中，待到某个合适的时间点，把StoreBuffer的数据刷到主内存。

因为StoreBuffer的存在，CPU在写数据时，真实的数据不会立即表现到内存中，所以对其他CPU是不可见的，同样，LoadBuffer中的请求也无法拿到最新其他CPU设置的最新数据；

由于StoreBuffer和LoadBuffer是异步执行的，所以在外面看来，先写后读还是先读后写，没有严格的固定顺序。

综上，内存可见性问题的根本原因是load和store的异步性，造成了不同CPU之间的内存不可见。

内存屏障用来指定指令的执行顺序，有四种：
1、loadload屏障，load1、loadload、load2  ----acquire()
2、loadstore屏障，load、loadstore、store  ----acquire()
3、storestore屏障                                       ----release()
4、storeload屏障                                        ----fence()
1和2都是用acquire方法处理，往LoadBuffer中插入该屏障，清空屏障之前的load操作，然后才能执行屏障之后的操作，可以保证load操作的数据在下个store指令之前准备好。
3屏障通过release方法处理，在StoreBuffer中插入该屏障，清空屏障之前的store操作，然后才能执行屏障之后的store操作，保证store1写入的数据在执行store2时对其他CPU可见。

4屏障通过fence方法处理。storeload屏障会保证该屏障之前的所有存储指令和访问指令都完成，才执行屏障后的指令。

所谓的重排序指的是，针对程序代码的顺序而言，如果指令执行顺序与代码顺序不同，就说明指令被重排序了。

真 重排序：指令的执行顺序与代码顺序确实不一致
伪 重排序：其实是可见性问题

为什么要重排序？与其等待阻塞命令（如缓存刷入）完成，不如先去执行其他命令。

乱序执行的表现是，先进入指令序列缓冲区的指令可能后离开，后进入的可能先离开。

内存屏障的特点是，不管什么屏障，都能保证屏障前面的指令执行完才能执行屏障后面的指令，并且执行屏障后的指令时，前面的指令的结果已经生效。

lock前缀时一个特殊的信号，执行过程如下：
对总线和缓存上锁
强制所有lock信号之前的指令，都在此之前被执行，并同步相关缓存
执行lock后的命令
释放对总线和缓存上的锁
强制所有lock信号之后的命令，都在此之后被执行，并同步相关缓存

final用法和应用场景
局部变量肯定是线程安全的，因为在线程栈中，外部线程访问不到。
final变量初始化变量后会插入屏障，由JVM来保证。
