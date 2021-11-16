# CSCI4211_rdt_java
## Jeevan Prakash,Zhang CSCI4211,15/11/2021
## Java,A.java,B.java,simulator.class

### Compilation Section
#### Instructions for Compiling Stop and Wait
1. Ensure that you have the Java JDK
2. Go to to the stop_and_wait/src folder
3. Run `javac simulator.java`
#### Instructions for Compiling Go-Back-N
1. Ensure that you have the Java JDK
2. Go to to the go_back_n/src folder
2. Run `javac simulator.java`
3. Run `javac circular_buffer.java`

### Execution Section
#### Instructions for Executing Stop and Wait
1. Go to to the stop_and_wait/src folder
2. run `java simulator`
#### Instructions for Compiling Go-Back-N
1. Go to to the go_back_n/src folder
2. run `java simulator`

### Description Section
#### Stop and Wait
##### Description
> Stop and Wait protocol (in this case alternating bit protocol) sends data across the network from one side to the other and waits for an ack with the correct sequence nubmer before proceeding. A timer is set to ensure that if an ACK has not arrived for a while from the other side, the last packet will be resent to the other side. The sequence numbers in this implementation alternate between 0 and 1, hence the name alternating bit protocol. My A class waits for a payload from the 5th layer of the network in its `WAIT_LAYER5` state. Once A gets the payload, it will send it through the network with a sequence number of 0 or 1 depending on what the previous packet nubmer was, and it will update the state of A to `WAIT_ACK`. Class A will then store this packet that is just sent out, in case it needs to be resent to B. B will either receive the packet or it will get lost in the 3rd layer. If it is lost, a timeout will occur on the A side since no ACK will be received within `the estimated_rtt` of *30 seconds*. If a timeout occurs, the `lastpacket` is sent once again to the B class. If the B class receives the packet successfully it will send an ACK to A and send the payload to layer 5. If the ACK is lost in layer 3, a timeout will occur on A, and the packet will be sent once again to B. If the ACK is successfully received by A, A will update the state of A to `WAIT_LAYER5`, update the sequence number to 0 if the previous seq was 1 or vice versa, and reset the timer.
##### Added Data Structures/Fields
###### A class
- String state: String to store state of A (either `WAIT_ACK` or `WAIT_LAYER5`)
- int seq: int that stores the current sequence number that is being sent over to B
- int esimated_rtt: int that stores the estimated round trip time of a packet (30 seconds)
- packet lastpacket: packet object that represents the last packet that was sent
###### B class
- int seq: the next sequence number that is expected from A
##### Functions/Methods
###### A class
- initialization function of A: initialize the seq to 0, state to `WAIT_LAYER5`, and estimated_rtt to 30
- A_input(simulator sim, packet p):
    - @param sim is a simulator variable used for removing the timer, starting the timer, or sending data to the 3rd and 5th layers
    - @param p is an ACK packet that has been received from B
    - This method receives ACK packets from B
    - First, checksum is used to verify if the packet is uncorrupted
        - Return if not equal
    - Second, the acknum number is compared to the acknum of the last packet
        - Resend the packet in case of an incorrect acknum and restart the timer
        - In case of the correct acknum, remove the timer and change the state back to `WAIT_LAYER5`
    - no return value
- A_output(simulator sim, msg m):
    - @param sim is a simulator variable used for removing the timer, starting the timer, or sending data to the 3rd and 5th layers
    - @param m is a message payload that will be used to create a new packet to send
    - This method is called by the 5th layer
    - First, check to make sure that the state of A is not `WAIT_ACK` (A needs to be in `WAIT_LAYER5` state)
    - Second, construct a new packet
    - Third, restart the timer
    - Fourth, send the packet to B
    - Fifth, set the state to `WAIT_ACK` since A is now waiting for an ACK
    - no return value
- A_handle_timer(simulator sim):
    - @param sim is a simulator variable used for removing the timer, starting the timer, or sending data to the 3rd and 5th layers
    - This method handles the timeout events
    - First, restart the timer
    - Second, send the lastpacket to B again
    - Third, set the state to `WAIT_ACK`
    - no return value
###### B class
- initialization function of B: initialize the seq to 0
- B_input(simulator sim,packet pkt):
    - @param sim is a simulator variable used for removing the timer, starting the timer, or sending data to the 3rd and 5th layers
    - @param pkt is a packet variable which contains the payload to be sent to layer 5
    - This method receives the message payloads and sends ACK messages back
    - First, checksum is used to verify if the packet is uncorrupted
    - Second, if the packets sequence number does not match the B's current sequence number, an ACK is sent for that packet to A
    - Third, if the packets sequence number does match, the payload is sent to layer 5, an ACK is sent to A, and the next expected sequence number is flipped from 0 to 1 or vice versa
- Other methods are not required to be implemented
### Evaluation Section
#### Test Cases
1. Test Case 1
    - loss set to 0.2
    - corruption rate set to 0.2
    - Lambda set to 1000
    - nsimmax set to 20
    - This is the standard original simulation run with 0.2 packet loss probability and 0.2 corruption probability
    - All packets were sent correctly through this protocol through the use of the timeout functionality
    - The packet loss was handled by the timeout handler
    - The corruption was prevented through the checksum
    - All the logic has been explained in detail above

    ![test case 1](./test_case_outputs/test1.jpeg "test case 1")
2. Test Case 2
    - loss set to 0
    - corruption rate set to 0
    - Lambda set to 1000
    - nsimmax set to 20
    - This is a standard run with no packet loss and corruption of packets
    - All the logic has been explained in detail above

    ![test case 2](./test_case_outputs/test2.jpeg)
3. Test Case 3
    - loss set to 0
    - corruption rate set to 0
    - Lambda set to 1000
    - nsimmax set to 26
    - This is a standard run with no packet loss and corruption of packets with more packets sent through
    - All the logic has been explained in detail above

    ![test case 3](./test_case_outputs/test3.jpeg)
4. Test Case 4
    - loss set to 0.5
    - corruption rate set to 0
    - Lambda set to 1000
    - nsimmax set to 26
    - This is much higher loss probability at 0.5 with no corruption probability
    - The packet loss was handled by the timeout handler
    - All the logic has been explained in detail above

    ![test case 4](./test_case_outputs/test4.jpeg)
5. Test Case 5
    - loss set to 0.5
    - corruption rate set to 0.6
    - Lambda set to 1000
    - nsimmax set to 26
    - All packets were sent correctly through this protocol through the use of the timeout functionality
    - The packet loss was handled by the timeout handler
    - The corruption was prevented through the checksum
    - All the logic has been explained in detail above

    ![test case 5](./test_case_outputs/test5.jpeg)
6. Test Case 6
    - loss set to 0
    - corruption rate set to 0.9
    - Lambda set to 10000
    - nsimmax set to 26
    - The corruption was prevented through the checksum
    - All the logic has been explained in detail above

    ![test case 6](./test_case_outputs/test6.jpeg)
7. Test Case 7
    - loss set to 0.9
    - corruption rate set to 0
    - Lambda set to 10000
    - nsimmax set to 26
    - All packets were sent correctly through this protocol through the use of the timeout functionality
    - The packet loss was handled by the timeout handler
    - All the logic has been explained in detail above

    ![test case 7](./test_case_outputs/test7.jpeg)