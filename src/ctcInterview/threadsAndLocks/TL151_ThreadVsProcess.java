package ctcInterview.threadsAndLocks;

/**
 * What's the different between a process and a thread
 * 
 * @author minhhoang
 *
 */
public class TL151_ThreadVsProcess {
  /*
   * Process can be see as a program in execution. Its space is independent from other processes.
   * Thus, no two process can access the same space. However, they can communicate with each other
   * via pipes, messages, etc.
   * 
   * Thread exists within a process and shares the same resources with that process. A process can
   * have multiple threads, but only one can have the CPU. Memory and heap spaces are shared 
   * between threads, but each has its own set of registers and stack.
   */
}
