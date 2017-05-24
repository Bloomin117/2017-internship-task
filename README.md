# Autor: Krystian Wrona

Scheduler
 
In an event driven system efficiently keeping track of expiry timers is a common problem. This can apply to any system keeping track of outstanding requests or messages with an expiry time. Scheduler component is composed of two user-facing operations: start and stop. 
 
Start is called by clients specifying an expiration time and a callback. In return they get a timerId.
Stop takes a timerId and finds and stops (removes) the associated timer.
Additionally Scheduler needs to check for timers that have expired and invoke a callback that was provided by the user during timer registration.
 
Implement a Scheduler component without using ScheduledExecutorService or Timer/TimerTask. Solution should be thread safe.
 
For simplicity assume scheduling resolution is 1s.
