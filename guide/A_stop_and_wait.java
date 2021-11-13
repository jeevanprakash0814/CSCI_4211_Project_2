public class A {
    String state;
    int seq;
    int estimated_rtt;
    packet lastpacket;
    public A(){
        /* stop and wait, the initialization of A
        // for stop and wait, the state can be "WAIT_LAYER5" or "WAIT_ACK"
        // "WAIT_LAYER5" is the state that A waits messages from application layer.
        // "WAIT_ACK" is the state that A waits acknowledgement
        // You can set the estimated_rtt to be 30, which is used as a parameter when you call start_timer
         */
    }
    public void A_input(simulator sim, packet p){
        /* stop and wait A_input
        // the sim is the simulator. It is provided to call its method such as to_layer_three, start_timer and stop timer
        // p is the packet from the B
        // first verify the checksum to make sure that packet is uncorrupted
        // then verify the acknowledgement number to see whether it is the expected one
        // if not, you may need to resend the packet.
        // if the acknowledgement is the expected one, you need to update the state of A from "WAIT_ACK" to "WAIT_LAYER5" again
         */
    }
    public void A_output(simulator sim, msg m){
        /* stop and wait A_output
        // the "sim" is the simulator. It is provided to call its method such as to_layer_three, start_timer and stop timer
        // msg m is the message. It should be used to construct the packet.
        // You can construct the packet using the "public packet(int seqnum,msg m)" in "packet.java".
        // save the packet so that it can be resent if needed.
        // Set the timer using "sim.envlist.start_timer", and the time should be set to estimated_rtt. Make sure that there is only one timer started in the event list.
        // In the end, you should change the state to "WAIT_ACK"
         */
    }
    public void A_handle_timer(simulator sim){
        /* stop and wait A_handle_timer
        // the sim is the simulator. It is provided to call its method such as to_layer_three, start_timer and stop timer
        // if it is triggered, it means the previous sent packet isn't delivered
        // so you need to resend the last packet
        // After sending the last packet, don't forget to set the timer again
         */
    }
